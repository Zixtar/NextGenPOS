package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.entities.Category;
import com.nextgenpos.demo.entities.Product;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class CategoriesBean {
    private static final Logger LOG = Logger.getLogger(CategoriesBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    public List<CategoryDto> findAllCategories()
    {
        LOG.info("findAllCategories");
        try{
            TypedQuery<Category> typedQuery = entityManager.createQuery("SELECT c FROM Category c", Category.class);
            List<Category> categories = typedQuery.getResultList();
            return copyCategoriesToDto(categories);
        }catch (Exception ex)
        {
            throw new EJBException(ex);
        }
    }

    private List<CategoryDto> copyCategoriesToDto(List<Category> categories) {
        java.util.List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category s: categories) {
            List<String> nameOfProducts = new ArrayList<>();
            List<Product> productsOfThisCategory = s.getProducts() ;
            for(Product p: productsOfThisCategory){
                nameOfProducts.add(p.getName());
            }
            categoryDtoList.add(new CategoryDto(s.getId(), s.getName(), nameOfProducts));
        }
        return categoryDtoList;
    }


}
