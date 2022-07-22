package br.com.devcanoa.stickermaker.sticker.domain;

import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

import java.io.InputStream;
import java.net.URL;

public interface File {
    long getLength();
    URL getPublicUrl();
    String getFullPath();
    String getFileName();
    String getExtension();
    String getBucketName();
    String getContentType();
    InputStream getContent();
    ObjectCannedACL getACL();
}
