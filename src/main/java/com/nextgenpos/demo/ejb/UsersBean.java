package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.entities.Address;
import com.nextgenpos.demo.entities.User;
import com.nextgenpos.demo.entities.UserGroup;
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
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u ", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        }catch (Exception ex)
        {
            throw new EJBException(ex);
        }
    }

    public List<UserDto> copyUsersToDto(List<User> users){
        java.util.List<UserDto> userDtoList = new ArrayList<>();

        for (User s: users) {
            userDtoList.add(new UserDto(s.getId(), s.getUsername(),s.getPassword(),
                    s.getFirstName(),s.getLastName(),s.getTelNr(),s.getEmail(),s.getState()));
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
    private void assignGroupToUser(String username, String group) {
        LOG.info("assignGroupToUser");

        UserGroup userGroup = new UserGroup();
        userGroup.setUsername(username);
        userGroup.setUserGroup(group);
        entityManager.persist(userGroup);
    }
}
