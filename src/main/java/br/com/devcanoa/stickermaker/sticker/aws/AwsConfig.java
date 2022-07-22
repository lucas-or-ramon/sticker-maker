package br.com.devcanoa.stickermaker.sticker.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AwsConfig {

    @Bean("s3Client")
    public S3Client s3ClientProducer() {
        return S3Client.builder().credentialsProvider(EnvironmentVariableCredentialsProvider.create()).region(Region.US_EAST_1).build();
    }
}
