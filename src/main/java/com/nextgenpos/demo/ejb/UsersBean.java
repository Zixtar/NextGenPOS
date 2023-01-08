package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.UserDto;
import com.nextgenpos.demo.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
@Stateless
public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

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
}
