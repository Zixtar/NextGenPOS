package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.CategoryDto;
import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.common.ProductPhotoDto;
import com.nextgenpos.demo.entities.Category;
import com.nextgenpos.demo.entities.Product;
import com.nextgenpos.demo.entities.ProductPhoto;
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

    public List<ProductDto> getProductsOfCategories(Long categoryId) {
        List<ProductDto> productsList = null;
        try {
            Category category = entityManager.find(Category.class, categoryId);
            productsList = this.copyProductsToDto(category.getProducts());

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        return productsList;
    }
    public List<ProductDto> copyProductsToDto(List<Product> products){
        java.util.List<ProductDto> productDtoList = new ArrayList<>();

        for (Product s: products) {
            List<String> nameOfCategories = new ArrayList<>();
            List<Category> categoriesOfThisProduct = s.getCategories() ;
            for(Category c: categoriesOfThisProduct){
                nameOfCategories.add(c.getName());
            }
            productDtoList.add(new ProductDto(s.getId(), s.getName(), s.getStock(), s.getDescription(), s.getPrice(), nameOfCategories, s.getOfferItems()));
        }
        return productDtoList;
    }
    public void createProduct(String name, Integer stock, String description, Integer price, String[] categoryIds){
        LOG.info("createProduct");
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        product.setDescription(description);
        product.setPrice(price);

        for(String categoryId: categoryIds){
            Long id= Long.parseLong(categoryId);
            Category category = entityManager.find(Category.class,id);
            category.getProducts().add(product);
            product.getCategories().add(category);
        }
        entityManager.persist(product);
    }
    public void addPhotosToProduct(Long productId, String filename, String fileType, byte[] fileContent) {
        LOG.info("addPhotosToProduct");
        ProductPhoto photo = new ProductPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        Product product = entityManager.find(Product.class, productId);
        product.getPhotos().add(photo);
        //product.setPhotos(photo);
        photo.setProduct(product);
        entityManager.persist(photo);
    }
    public List<Long> findPhotosIdByProductId(Long productId) {
        List<ProductPhoto> photos = entityManager
                .createQuery("SELECT p FROM ProductPhoto p where p.product.id = :id", ProductPhoto.class)
                .setParameter("id", productId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        List<ProductPhotoDto> productPhotoDtos = new ArrayList<>();
        for (ProductPhoto photo:photos){
            productPhotoDtos.add(new ProductPhotoDto(photo.getId(), photo.getFilename(), photo.getFileType(),
                    photo.getFileContent()));
        }
        List<Long> photosIds = new ArrayList<>();
        for(ProductPhotoDto productPhotoDto:productPhotoDtos){
            photosIds.add(productPhotoDto.getId());
        }
       // return productPhotoDtos;
        return photosIds;
    }

    public ProductDto findById(Long productId) {
        Product product = entityManager.find(Product.class,productId);
        List<String> categoriesname = new ArrayList<>();
        List<Category> categories = product.getCategories();
        for(Category category:categories){
            categoriesname.add(category.getName());
        }

        ProductDto productDetail = new ProductDto(product.getId(),product.getName(),product.getStock(),product.getDescription(),
                product.getPrice(), categoriesname,product.getOfferItems());
        return productDetail;
    }

    public ProductPhotoDto findProductPhotoById(Long photoId) {
        ProductPhoto car = entityManager.find(ProductPhoto.class,photoId);
        ProductPhotoDto photoDetails = new ProductPhotoDto(car.getId(), car.getFilename(), car.getFileType(),
                car.getFileContent());
        return photoDetails;
    }

    public void updateProduct(String name, Integer stock, String description, Integer price, String[] categoryIds, Long productId) {
        LOG.info("updateProduct");
        Product product = entityManager.find(Product.class,productId);
        product.setName(name);
        product.setStock(stock);
        product.setDescription(description);
        product.setPrice(price);
        List<Category> oldCategories = product.getCategories();
        for(Category oldCategory: oldCategories){
            oldCategory.getProducts().remove(product);
        }
        product.getCategories().clear();
        for(String categoryId: categoryIds){
            Long id= Long.parseLong(categoryId);
            Category category = entityManager.find(Category.class,id);
            category.getProducts().add(product);
            product.getCategories().add(category);
        }

    }

    public void deleteProductsByIds(List<Long> productIds) {
        LOG.info("deleteProductsByIds");

        for( Long productId : productIds){
            Product product = entityManager.find(Product.class, productId);
            List<Category> categories = product.getCategories();
            for(Category category:categories){
                category.getProducts().remove(product);
            }
            entityManager.remove(product);
        }
    }

    public ProductPhotoDto findFirstProductPhotoById(Long productId)
    {
        List<ProductPhoto> photos=entityManager.createQuery("SELECT p FROM ProductPhoto p WHERE p.product.id=:id", ProductPhoto.class)
                .setParameter("id", productId)
                .getResultList();
        if(photos.isEmpty())
            return null;
        ProductPhoto photo=photos.get(0);
        return new ProductPhotoDto(photo.getId(),photo.getFilename(),photo.getFileType(),photo.getFileContent());
    }

}

