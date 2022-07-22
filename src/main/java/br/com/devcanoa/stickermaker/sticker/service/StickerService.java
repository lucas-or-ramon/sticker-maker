package br.com.devcanoa.stickermaker.sticker.service;

import br.com.devcanoa.stickermaker.sticker.aws.AwsStorage;
import br.com.devcanoa.stickermaker.sticker.domain.Image;
import br.com.devcanoa.stickermaker.sticker.domain.Message;
import br.com.devcanoa.stickermaker.sticker.domain.Sticker;
import br.com.devcanoa.stickermaker.sticker.util.StickerGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

@Service
public class StickerService {

    private final AwsStorage awsStorage;

    @Autowired
    public StickerService(AwsStorage awsStorage) {
        this.awsStorage = awsStorage;
    }

    public Sticker getSticker(Message message, Image image) {
        var sticker = createNewSticker(message, image);

        sticker.setPublicUrl(awsStorage.store(sticker));

        return sticker;
    }

    private Sticker createNewSticker(Message message, Image image) {
        var acl = ObjectCannedACL.PUBLIC_READ;
        var content = new StickerGenerator(message, image).generate();
        var fileName = image.title();
        var folderName = image.getClass().getSimpleName();
        var bucketName = System.getenv("bucketName");

        return new Sticker(content, fileName, folderName, bucketName, acl);
    }
}
