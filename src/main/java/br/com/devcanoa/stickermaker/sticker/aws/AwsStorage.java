package br.com.devcanoa.stickermaker.sticker.aws;

import br.com.devcanoa.stickermaker.sticker.domain.File;
import br.com.devcanoa.stickermaker.sticker.util.StickerUtils;
import br.com.devcanoa.stickermaker.sticker.exception.AwsStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URL;

@Service
public class AwsStorage {

    private final S3Client s3Client;

    @Autowired
    public AwsStorage(@Qualifier("s3Client") S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public URL store(File file) {
        try {
            s3Client.putObject(buildRequest(file), buildBody(file));
        } catch (SdkException e) {
            throw new AwsStoreException("Erro ao armazenar arquivo no s3: " + e.getMessage());
        }
        return StickerUtils.generatePublicUrl(file);
    }

    private PutObjectRequest buildRequest(File file) {
        return PutObjectRequest.builder()
                .acl(file.getACL())
                .key(file.getFullPath())
                .bucket(file.getBucketName())
                .contentType(file.getContentType())
                .build();
    }

    private RequestBody buildBody(File file) {
        return RequestBody.fromInputStream(file.getContent(), file.getLength());
    }
}
