package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.ProductDto;
import com.nextgenpos.demo.common.SaleItemCreatorDto;
import com.nextgenpos.demo.entities.Product;
import com.nextgenpos.demo.entities.Sale;
import com.nextgenpos.demo.entities.SaleItem;
import com.nextgenpos.demo.entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.*;

@Stateful
@SessionScoped
public class SaleCreateBean implements Serializable {

    ArrayList<SaleItemCreatorDto> saleItemDtos;

    private Sale sale;

    @PostConstruct
    private void init(){

        saleItemDtos = new ArrayList<>();
        sale = new Sale();
    }

    @PersistenceContext
    EntityManager entityManager;


    public void InitializeSale(String cashierName)
    {
        sale = new Sale();

        TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u where u.username=:username", User.class)
                .setParameter("username",cashierName);
        User cashier = typedQuery.getSingleResult();

        sale.setCashier(cashier);
        sale.setState("pending");
        sale.setSaleItems(new ArrayList<>());
        sale.setDate(new Date());
    }

    public void AddItemToSale(SaleItemCreatorDto productDto)
    {
        saleItemDtos.add(productDto);
    }


    public Boolean iSFinalizeSale()
    {
        return sale.getState() == "finalized";
    }

    public String getSaleID()
    {
        return sale.getId();
    }

    public boolean iSEmptySale() {
        return sale.getState().isEmpty();
    }

    public List<SaleItemCreatorDto> getCurrentSaleProducts()
    {
        return saleItemDtos;
    }

    public SaleItemCreatorDto CopyProductDTOtoSaleDTO(ProductDto productDto)
    {
        SaleItemCreatorDto saleItemCreatorDto = new SaleItemCreatorDto(productDto.getId(),
                                                productDto.getName(),1,productDto.getPrice());

        return saleItemCreatorDto;
    }

    public void IncreaseQuantityForItem(Integer index)
    {
        saleItemDtos.get(index).setQuantity( saleItemDtos.get(index).getQuantity() + 1 );
    }

    public void DecreaseQuantityForItem(Integer index)
    {
        saleItemDtos.get(index).setQuantity( saleItemDtos.get(index).getQuantity() - 1 );
    }

    public void RemoveItem(Integer index)
    {
        saleItemDtos.remove(index);
    }

    public Integer getTotal() {
        Integer sum = 0;
        for(SaleItemCreatorDto item : saleItemDtos)
        {
            sum+= item.getPrice();
        }
        return sum;
    }

    public void FinalizeSale()
    {
        Collection<SaleItem> saleItems = new ArrayList<>();

        for (SaleItemCreatorDto saleItemDto: saleItemDtos ) {
            SaleItem saleItem = new SaleItem();
            saleItem.setPrice(saleItemDto.getPrice());
            saleItem.setQuantity(saleItemDto.getQuantity());
            saleItem.setProduct(entityManager.find(Product.class , saleItemDto.getId()));

            saleItems.add(saleItem);
            entityManager.persist(saleItem);
        }

        sale.setSaleItems(saleItems);
        sale.setState("finalized");
        sale.setDate(new Date());

        entityManager.persist(sale);
    }

    public void CancelSale(String username) {
        InitializeSale(username);
    }
}
