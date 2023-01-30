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

    public List<ProductDto>getProductsOfCategories(Long categoryId) {
        List<ProductDto> productsList = null;
        List<List<ProductDto>> listsOfProductsList = new ArrayList<>();
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
            productDtoList.add(new ProductDto(s.getId(), s.getName(), s.getStock(), s.getDescription(), s.getPrice(), nameOfCategories));
        }
        return productDtoList;
    }
    public void createProduct(String name, Integer stock, String description, Integer price, Long categoryId){
        LOG.info("createProduct");
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        product.setDescription(description);
        product.setPrice(price);
        Category category = entityManager.find(Category.class, categoryId);
        category.getProducts().add(product);
        product.getCategories().add(category);
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
                product.getPrice(), categoriesname);
        return productDetail;
    }

    public ProductPhotoDto findProductPhotoById(Long photoId) {
        ProductPhoto car = entityManager.find(ProductPhoto.class,photoId);
        ProductPhotoDto photoDetails = new ProductPhotoDto(car.getId(), car.getFilename(), car.getFileType(),
                car.getFileContent());
        return photoDetails;
    }
}
