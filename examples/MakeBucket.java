/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2015 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MakeBucket {
  /** MinioClient.makeBucket() example. */
  public static void main(String[] args)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      /* play.min.io for test and development. */
      MinioClient minioClient =
          new MinioClient(
              "https://play.min.io",
              "Q3AM3UQ867SPQQA43P2F",
              "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");

      /* Amazon S3: */
      // MinioClient minioClient = new MinioClient("https://s3.amazonaws.com", "YOUR-ACCESSKEYID",
      //                                           "YOUR-SECRETACCESSKEY");

      // Create bucket 'my-bucketname' if it doesn`t exist.
      if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket("my-bucketname").build())) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket("my-bucketname").build());
        System.out.println("my-bucketname is created successfully");
      }

      // Create bucket 'my-bucketname-in-eu' in 'eu-west-1' region if it doesn't exist.
      if (!minioClient.bucketExists(
          BucketExistsArgs.builder().bucket("my-bucketname-in-eu").build())) {
        minioClient.makeBucket(
            MakeBucketArgs.builder().bucket("my-bucketname-in-eu").region("eu-west-1").build());
        System.out.println("my-bucketname-in-eu is created successfully");
      }

      // Create bucket 'my-bucketname-in-eu-with-object-lock' in 'eu-west-1' with object lock
      // functionality enabled.
      if (!minioClient.bucketExists(
          BucketExistsArgs.builder().bucket("my-bucketname-in-eu-with-object-lock").build())) {
        minioClient.makeBucket(
            MakeBucketArgs.builder()
                .bucket("my-bucketname-in-eu-with-object-lock")
                .region("eu-west-1")
                .objectLock(true)
                .build());
        System.out.println("my-bucketname-in-eu-with-object-lock is created successfully");
      }
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
    }
  }
}
