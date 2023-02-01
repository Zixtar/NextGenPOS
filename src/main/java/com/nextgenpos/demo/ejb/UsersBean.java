package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.entities.*;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());
    @Inject
    PasswordBean passwordBean;
    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers()
    {
        LOG.info("findAllUsers");
        try{
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        }catch (Exception ex)
        {
            throw new EJBException(ex);
        }
    }

    public UserDto findUserById(Long userId)
    {
        LOG.info("findUserById");
        User s=entityManager.find(User.class, userId);
        return new UserDto(s.getId(), s.getUsername(),s.getPassword(),
                s.getFirstName(),s.getLastName(),s.getTelNr(),s.getEmail(),
                s.getMbti(),s.getAddress().getCountry(),
                s.getAddress().getCity(),s.getAddress().getStreet(),
                s.getAddress().getNumber(),s.getAddress().getPostalCode(), s.getWishlistProducts());
    }

    public UserDto findUserByUsername(String username)
    {
        LOG.info("findUserByUsername");
        TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u where u.username=:username", User.class)
                .setParameter("username",username);
        if(typedQuery.getResultList().isEmpty()) return null;
        User s=typedQuery.getSingleResult();

        return new UserDto(s.getId(), s.getUsername(),s.getPassword(),
                s.getFirstName(),s.getLastName(),s.getTelNr(),s.getEmail(),
                s.getMbti(),s.getAddress().getCountry(),
                s.getAddress().getCity(),s.getAddress().getStreet(),
                s.getAddress().getNumber(),s.getAddress().getPostalCode(), s.getWishlistProducts());
    }

    public List<UserDto> copyUsersToDto(List<User> users){
        java.util.List<UserDto> userDtoList = new ArrayList<>();

        for (User s: users) {
            userDtoList.add(new UserDto(s.getId(), s.getUsername(),s.getPassword(),
                    s.getFirstName(),s.getLastName(),s.getTelNr(),s.getEmail(),
                    s.getMbti(),s.getAddress().getCountry(),
                    s.getAddress().getCity(),s.getAddress().getStreet(),
                    s.getAddress().getNumber(),s.getAddress().getPostalCode(), s.getWishlistProducts()));
        }
        return userDtoList;
    }

    public void createUser(String username, String email, String password,String country, String city,
                           String street, Integer number, Integer postalCode, String firstName,
                           String lastName, String telNr,String group, String mbti) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        Address address= new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setNumber(number);
        address.setPostalCode(postalCode);
        newUser.setAddress(address);
        entityManager.persist(address);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setTelNr(telNr);
        newUser.setMbti(mbti);
        entityManager.persist(newUser);
        address.setUser(newUser);
        assignGroupToUser(username, group);
    }

    public void updateUser(Long userId,String email,String country,String city,
                            String street,Integer number,Integer postalCode,
                            String firstName,String lastName,String telNr,String mbti){
        LOG.info("updateUser");
        User user = entityManager.find(User.class,userId);
        user.setEmail(email);
        Address address= user.getAddress();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setNumber(number);
        address.setPostalCode(postalCode);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setTelNr(telNr);
        user.setMbti(mbti);
    }
    private void assignGroupToUser(String username, String group) {
        LOG.info("assignGroupToUser");

        UserGroup userGroup = new UserGroup();
        userGroup.setUsername(username);
        userGroup.setUserGroup(group);
        entityManager.persist(userGroup);
    }

    public void addProductToUserWishList(Long idProduct, Long idUser)
    {
        LOG.info("addProductToUserWishlist");
        TypedQuery <User> typedQuery1= entityManager.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class)
                .setParameter("id", idUser);
        User user=typedQuery1.getSingleResult();
        TypedQuery<Product> typedQuery2=entityManager.createQuery("SELECT p FROM Product p WHERE p.id=:id", Product.class)
                .setParameter("id", idProduct);
        Product product=typedQuery2.getSingleResult();
        List<Product> userWishlist=user.getWishlistProducts();
        for (Product element:userWishlist
             ) {
            if(element==product)
                return;
        }
        userWishlist.add(product);
        user.setWishlistProducts(userWishlist);
    }

    public void deleteItemFromWishlist(Long idProduct, Long idUser)
    {
        User user=entityManager.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class)
                .setParameter("id",idUser)
                .getSingleResult();
        Product product=entityManager.createQuery("SELECT p FROM Product p WHERE p.id=:id", Product.class)
                .setParameter("id", idProduct)
                .getSingleResult();
        List<Product>userWishList=user.getWishlistProducts();
        userWishList.remove(product);
        user.setWishlistProducts(userWishList);
    }
}
