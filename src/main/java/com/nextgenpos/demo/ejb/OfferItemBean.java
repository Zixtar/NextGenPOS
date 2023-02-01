package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.OfferItemDto;
import com.nextgenpos.demo.entities.OfferBundle;
import com.nextgenpos.demo.entities.OfferItem;
import com.nextgenpos.demo.entities.Product;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class OfferItemBean {

    private static final Logger LOG = Logger.getLogger(OfferItemBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<OfferItemDto> findAllOfferItems()
    {
        LOG.info("findAllOfferItems");
        try{
            TypedQuery<OfferItem> typedQuery=entityManager.createQuery("SELECT o FROM OfferItem o", OfferItem.class);
            List<OfferItem> offerItems=typedQuery.getResultList();
            return copyOfferItemsToDto(offerItems);

        }catch (Exception ex){
            throw new EJBException();
        }
    }

    public List<OfferItemDto> findAllOferItemsWithinDateInterval(Date beginningDate, Date endDate)
    {
        LOG.info("findAllOferItemsWithinDateInterval");
        List<OfferItem>offerItemList=entityManager.createQuery("SELECT o FROM OfferItem o WHERE o.beginningDate=:beginningDate AND o.endDate=:endDate", OfferItem.class)
                .setParameter("beginningDate",beginningDate)
                .setParameter("endDate", endDate)
                .getResultList();
        return copyOfferItemsToDto(offerItemList);
    }

    public Long getBundleId(Long offerItemId)
    {
        LOG.info("getBundleId");
        OfferItem of=entityManager.createQuery("SELECT o FROM OfferItem o WHERE o.id=:id", OfferItem.class)
                .setParameter("id", offerItemId)
                .getSingleResult();
        return of.getBundleId().getId();
    }
    public List<OfferItemDto> copyOfferItemsToDto(List<OfferItem> offerItems)
    {
        List<OfferItemDto> offerItemDtoList=new ArrayList<>();
        for(OfferItem offerItem:offerItems)
        {
            offerItemDtoList.add(new OfferItemDto(offerItem.getId(),offerItem.getProduct(),offerItem.getBundleId(),offerItem.getNewPrice(),offerItem.getBeginningDate(),offerItem.getEndDate()));
        }
        return offerItemDtoList;
    }

    public OfferItemDto copySingleOfferItemToDto(OfferItem offerItem)
    {
        return new OfferItemDto(offerItem.getId(),offerItem.getProduct(),offerItem.getBundleId(),offerItem.getNewPrice(),offerItem.getBeginningDate(),offerItem.getEndDate());
    }

    public OfferItemDto getOfferItemById(Long id)
    {
        LOG.info("getOfferItemById");
        TypedQuery<OfferItem>typedQuery=entityManager.createQuery("SELECT o FROM OfferItem o WHERE o.id=:id", OfferItem.class)
                .setParameter("id",id);
        OfferItem of=typedQuery.getSingleResult();
        OfferItemDto offerItemDto=new OfferItemDto(of.getId(), of.getProduct(), of.getBundleId(), of.getNewPrice(),of.getBeginningDate(), of.getEndDate());
        return offerItemDto;
    }

    public void createOfferItem(Long productId, Long bundleId, Float newPrice, String beginningDate, String endDate) throws ParseException {
        LOG.info("createOfferItem");
        OfferItem offerItem=new OfferItem();
        Product product=entityManager.createQuery("SELECT p FROM Product p WHERE p.id=:id", Product.class)
                .setParameter("id",productId)
                .getSingleResult();
        OfferBundle offerBundle=entityManager.createQuery("SELECT o FROM OfferBundle o WHERE o.id=:id",OfferBundle.class)
                .setParameter("id",bundleId)
                .getSingleResult();
        offerItem.setProduct(product);
        offerItem.setBundleId(offerBundle);
        offerItem.setNewPrice(newPrice);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date begdate = formatter1.parse(beginningDate);
        offerItem.setBeginningDate(begdate);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date enddate = formatter2.parse(endDate);
        offerItem.setBeginningDate(enddate);
        offerItem.setEndDate(enddate);

        entityManager.persist(offerItem);
    }
}
