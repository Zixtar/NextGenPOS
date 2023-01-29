package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.OfferItemDto;
import com.nextgenpos.demo.entities.OfferItem;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jdk.jpackage.internal.Log;

import java.util.ArrayList;
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

    public List<OfferItemDto> copyOfferItemsToDto(List<OfferItem> offerItems)
    {
        List<OfferItemDto> offerItemDtoList=new ArrayList<>();
        for(OfferItem offerItem:offerItems)
        {
            offerItemDtoList.add(new OfferItemDto(offerItem.getId(),offerItem.getProduct(),offerItem.getBundleId(),offerItem.getNewPrice(),offerItem.getBeginningDate(),offerItem.getEndDate()));
        }
        return offerItemDtoList;
    }
}
