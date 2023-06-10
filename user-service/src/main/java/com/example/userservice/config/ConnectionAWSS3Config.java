package com.example.userservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionAWSS3Config {
    @Value("${amazon.awsId}")
    private String AWS_ID;
    @Value("${amazon.awsKey}")
    private String AWS_KEY;
    @Value("${amazon.serviceEndPoint}")
    private String SERVICE_END_POINT;
    @Value("${amazon.signingRegion}")
    private String SIGNING_REGION;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(AWS_ID, AWS_KEY);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(
                                SERVICE_END_POINT, SIGNING_REGION
                        )
                )
                .build();
    }

}
