package com.jmp.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Ales on 28.05.2017.
 */
public class FileBucket {

    private MultipartFile file;
    private String description;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
