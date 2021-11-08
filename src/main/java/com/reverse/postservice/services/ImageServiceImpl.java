package com.reverse.postservice.services;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.reverse.postservice.models.ImageLocation;
import com.reverse.postservice.models.Post;
import com.reverse.postservice.models.PostImage;
import com.reverse.postservice.models.dto.ImageDto;
import com.reverse.postservice.repositories.ImageDao;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private ImageDao imageDao;

    @Autowired
    @Generated
    public void setImageDao(ImageDao dao){
        this.imageDao = dao;
    }


    @Override
    @Generated
    public List<PostImage> addImagesToBucket(List<ImageDto> imageDtos, Long userId) {

        //Note: none of this should be hardcoded
        //      sadly, it is. Fix later
        String bucketName = "reverse-social-media";
        ImageLocation bucket = new ImageLocation();
        bucket.setBucketName(bucketName);
        bucket.setId(1);

        //**IMPORTANT**//
        //The environment credentials provider expects the AWS_ACCESS_KEY and AWS_SECRET_KEY \
        // environment variables to be set.
        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();

        List<PostImage> createdImages = new ArrayList<>();
        Long now = Instant.now().toEpochMilli();

        int count = 0;
        for(ImageDto imgDto : imageDtos){

            //Create the name of the image in the bucket
            String imageName = userId+"-"+now+"-"+count+".png";

            //Convert byteString to InputStream
            byte[] bytes = Base64.getDecoder().decode(imgDto.getBytes());
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);

            //Create and set metadata
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentEncoding("base64");
            meta.setContentType("image/png");
            meta.setContentLength(bytes.length);
            meta.setLastModified(DateTime.now().toDate());

            //Upload data to S3
            amazonS3.putObject(bucketName, imageName, in, meta);

            // Add image to the database
            PostImage image = new PostImage();
            image.setImageTitle(imgDto.getImageTitle());
            image.setImageName(imageName);
            image.setBucket(bucket);

            PostImage img = imageDao.save(image);
            createdImages.add(img);
        }

        return createdImages;

    }
}
