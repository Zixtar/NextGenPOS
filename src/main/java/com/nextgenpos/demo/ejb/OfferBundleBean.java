package com.nextgenpos.demo.ejb;

import com.nextgenpos.demo.common.CarouselPhotoDto;
import com.nextgenpos.demo.common.OfferBundleDto;
import com.nextgenpos.demo.entities.CarouselPhoto;
import com.nextgenpos.demo.entities.OfferBundle;
import com.nextgenpos.demo.entities.OfferItem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jdk.jpackage.internal.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class OfferBundleBean {

    private static final Logger LOG = Logger.getLogger(OfferBundleBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public CarouselPhotoDto getCarouselPhotoByOfferBundleId(Long bundleId)
    {
        LOG.info("getCarouselPhotoByOfferBundleId");
        List<CarouselPhoto> photos=entityManager.createQuery("SELECT p FROM CarouselPhoto p WHERE p.offerBundle.id=:id", CarouselPhoto.class)
                .setParameter("id", bundleId)
                .getResultList();
        if(photos.isEmpty())
        {
            return null;
        }
        CarouselPhoto photo=photos.get(0); //get first element
        return new CarouselPhotoDto(photo.getId(), photo.getFileName(), photo.getFileType(), photo.getFileContent());
    }

    public void createOfferBundle(String name)
    {
        LOG.info("createOfferBundle");
        OfferBundle of=new OfferBundle();
        of.setName(name);
        entityManager.persist(of);
    }

    public List<OfferBundleDto> findAllOfferBundles()
    {
        LOG.info("findAllOfferBundles");
        List<OfferBundle> offerBundleList=entityManager.createQuery("SELECT o from OfferBundle o", OfferBundle.class)
                .getResultList();
        return copyOfferBundleToOfferBundleDto(offerBundleList);
    }

    public List<OfferBundleDto> copyOfferBundleToOfferBundleDto(List<OfferBundle>offerBundles)
    {
        LOG.info("copyOfferBundleToOfferBundleDto");
        List<OfferBundleDto> dtoList=new ArrayList<>();
        for (OfferBundle item:offerBundles
             ) {
            dtoList.add(new OfferBundleDto(item.getId(),item.getName(),item.getOfferItemList(),item.getCarouselOfferPhoto()));
        }
        return dtoList;
    }

    public OfferBundleDto findOfferBundleById(Long id)
    {
        LOG.info("findOfferBundleById");
        OfferBundle offerBundle=entityManager.createQuery("SELECT o FROM OfferBundle o WHERE o.id=:id", OfferBundle.class)
                .setParameter("id",id)
                .getSingleResult();
        return new OfferBundleDto(offerBundle.getId(),offerBundle.getName(),offerBundle.getOfferItemList(),offerBundle.getCarouselOfferPhoto());
    }

    public void addPhotoToOfferBundle(Long offerBundleId, String fileName, String fileType, byte[] fileContent)
    {
        LOG.info("addPhotoToOfferBundle");
        CarouselPhoto photo=new CarouselPhoto();
        photo.setFileName(fileName);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        OfferBundle of=entityManager.createQuery("SELECT o FROM OfferBundle o WHERE o.id=:id", OfferBundle.class)
                        .setParameter("id", offerBundleId)
                                .getSingleResult();
        photo.setOfferBundle(of);
        of.setCarouselOfferPhoto(photo);
        entityManager.persist(photo);

    }
}
