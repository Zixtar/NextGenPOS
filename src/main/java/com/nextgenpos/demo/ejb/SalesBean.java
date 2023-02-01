package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.entities.Sale;
import com.nextgenpos.demo.entities.SaleItem;
import com.nextgenpos.demo.entities.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collection;
import java.util.Date;

@Stateless
public class SalesBean {


    @PersistenceContext
    EntityManager entityManager;

    @Inject
    ProductsBean productsBean;

    public void CreateCompleteSale(User cashier, Date date, String State, Collection<SaleItem> saleItems)
    {
     Sale sale = new Sale();
     sale.setCashier(cashier);
     sale.setState("pending");
     sale.setDate(date);
     sale.setSaleItems(saleItems);
     entityManager.persist(sale);
    }
}
