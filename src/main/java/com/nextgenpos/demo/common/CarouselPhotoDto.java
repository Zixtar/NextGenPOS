package com.nextgenpos.demo.common;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class CarouselPhotoDto {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;

    private String fileType;

    private byte[] fileContent;

    public CarouselPhotoDto(Long id, String fileName, String fileType, byte[] fileContent) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileContent = fileContent;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }
}
