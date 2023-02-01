package com.nextgenpos.demo.entities;
import jakarta.persistence.*;

@Entity
public class CarouselPhoto {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;

    private String fileType;

    private byte[] fileContent;

    OfferBundle offerBundle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    @OneToOne
    public OfferBundle getOfferBundle(){ return  offerBundle;}

    public void setOfferBundle(OfferBundle offerBundle){this.offerBundle=offerBundle;}
}
