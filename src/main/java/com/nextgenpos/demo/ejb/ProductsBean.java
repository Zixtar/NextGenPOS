package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.entities.Category;
import com.nextgenpos.demo.entities.Product;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class ProductsBean {
    private static final Logger LOG = Logger.getLogger(ProductsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;
    public List<ProductDto> findAllProducts()
    {
        LOG.info("findAllProducts");
        try{
            TypedQuery<Product> typedQuery = entityManager.createQuery("SELECT c FROM Product c ", Product.class);
            List<Product> products = typedQuery.getResultList();
            return copyProductsToDto(products);
        }catch (Exception ex)
        {
            throw new EJBException(ex);
        }
    }

    public List<List<ProductDto>>getProductsOfCategories(List<CategoryDto> categories) {
        List<ProductDto> productsList = null;
        List<List<ProductDto>> listsOfProductsList = new ArrayList<>();
        try {
            for(CategoryDto category: categories ){
            Category c = entityManager.find(Category.class, category.getId());
            productsList = this.copyProductsToDto(c.getProducts());
            listsOfProductsList.add(productsList);
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        return listsOfProductsList;
    }
    public List<ProductDto> copyProductsToDto(List<Product> products){
        java.util.List<ProductDto> productDtoList = new ArrayList<>();

        for (Product s: products) {
            List<String> nameOfCategories = new ArrayList<>();
            List<Category> categoriesOfThisProduct = s.getCategories() ;
            for(Category c: categoriesOfThisProduct){
                nameOfCategories.add(c.getName());
            }
            productDtoList.add(new ProductDto(s.getId(), s.getName(), s.getStock(), s.getDescription(), s.getPrice(), nameOfCategories));
        }
        return productDtoList;
    }

}
