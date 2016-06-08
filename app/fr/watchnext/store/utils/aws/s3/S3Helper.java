package fr.watchnext.store.utils.aws.s3;

import java.util.Optional;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Helper {

    public static final String AWS_S3_BUCKET = "aws.s3.bucket";
    public static final String AWS_ACCESS_KEY = "aws.access.key";
    public static final String AWS_SECRET_KEY = "images.watchnext.fr";

    private static Optional<AmazonS3> amazonS3 = Optional.empty();

    private static String s3Bucket;

    private static void start() {
        String accessKey = "ww";
        String secretKey = "xx";
        s3Bucket = AWS_SECRET_KEY;

        if ((accessKey != null) && (secretKey != null)) {
            AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
            amazonS3 = Optional.of(new AmazonS3Client(awsCredentials));
        }
    }
    
    public static AmazonS3 getAmazonS3() {
    	if (!amazonS3.isPresent())
    		start();
    	return amazonS3.get();
    }
    
    public static String getFiles() {
    	StringBuilder result = new StringBuilder();
    	
    	ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
    	.withBucketName(AWS_SECRET_KEY)
    	.withPrefix("sub-folder/")
    	.withDelimiter("/");
    	ObjectListing objectListing;

    	do {
    		objectListing = getAmazonS3().listObjects(listObjectsRequest);
    		for (S3ObjectSummary objectSummary : 
    			objectListing.getObjectSummaries()) {
    			result.append( " - " + objectSummary.getKey() + "  " +
    	                "(size = " + objectSummary.getSize() + 
    					")");
    		}
    		listObjectsRequest.setMarker(objectListing.getNextMarker());
    	} while (objectListing.isTruncated());
    	
    	return result.toString();
    	
    }

}