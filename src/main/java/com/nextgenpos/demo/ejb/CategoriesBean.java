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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
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
    public void createCategory(String name){
        LOG.info("createCategory");
        Category category = new Category();
        category.setName(name);
        entityManager.persist(category);
    }

    public void deleteCategoriesByIds(List<Long> categoryIds) {
        LOG.info("deleteCategoriesByIds");

        for( Long categoryId : categoryIds){
            Category category = entityManager.find(Category.class, categoryId);
            entityManager.remove(category);

        }
    }

    public List<CategoryDto> findCategoriesByName(List<String> categoriesName) {
        List<CategoryDto> catDto = new ArrayList<>();
        for(String categoryName:categoriesName){
            TypedQuery<Category> typedQuery = entityManager.createQuery("SELECT c FROM Category c where c.name=:catName", Category.class)
                    .setParameter("catName",categoryName);
            Category category=typedQuery.getSingleResult();
            catDto.add(new CategoryDto(category.getId(),category.getName(),new ArrayList<>()));
        }
        return catDto;
    }
}
