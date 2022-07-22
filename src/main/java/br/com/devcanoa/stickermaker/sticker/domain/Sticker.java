package br.com.devcanoa.stickermaker.sticker.domain;

import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

public class Sticker implements File {
    private static final String EXTENSION = ".png";
    private static final String CONTENT_TYPE = "image/" + EXTENSION;
    private final byte[] content;
    private final String fileName;
    private final String folderName;
    private final String bucketName;
    private final ObjectCannedACL acl;
    private URL publicUrl;

    public Sticker(byte[] content, String fileName, String folderName, String bucketName, ObjectCannedACL acl) {
        this.acl = acl;
        this.content = content;
        this.fileName = fileNameValidator(fileName);
        this.folderName = folderName.toLowerCase();
        this.bucketName = bucketName;
    }

    public String fileNameValidator(String fileName) {
        return fileName.replaceAll("[^A-Za-z]+", "").toLowerCase();
    }

    @Override
    public long getLength() {
        return content.length;
    }

    @Override
    public URL getPublicUrl() {
        return publicUrl;
    }

    @Override
    public String getFullPath() {
        return folderName + "/" + fileName + EXTENSION;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getExtension() {
        return EXTENSION;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public ObjectCannedACL getACL() {
        return acl;
    }

    @Override
    public InputStream getContent() {
        return new ByteArrayInputStream(content);
    }

    public void setPublicUrl(URL publicUrl) {
        this.publicUrl = publicUrl;
    }
}
