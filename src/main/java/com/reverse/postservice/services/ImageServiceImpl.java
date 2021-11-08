package com.reverse.postservice.services;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.reverse.postservice.models.User;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ImageServiceImpl implements ImageService{


    @Override
    public List<String> addImagesToBucket(List<String> base64Strings, Long userId) {

        String bucketName = "reverse-social-media";

        //The environment credentials provider expects the AWS_ACCESS_KEY and AWS_SECRET_KEY \
        // environment variables to be set.
        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();

        List<String> urls = new ArrayList<>();

        int count = 0;
        for(String imageString : base64Strings){

            //Create the name of the image in the bucket
            Long now = Instant.now().toEpochMilli();
            String imageName = userId+"-"+now+"-"+count;


            amazonS3.putObject("reverse-social-media", imageName, imageString);

            urls.add(amazonS3.getUrl("reverse-social-media", imageName).toString());
        }

        return urls;

    }
}
