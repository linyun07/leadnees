package com.linyun.minio.test;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinIOTest {

    /**
     * 把 list.html文件上传到minio中，并且在浏览器中访问
     */
    public static void main(String[] args)  {
        try {
            FileInputStream in = new FileInputStream("D:\\Tmp\\css\\index.css");
            //1.获取minio的连接信息 创建一个minio的客户端
            MinioClient client = MinioClient.builder()
                    .credentials("minio", "minio123").endpoint("http://47.120.37.50:9000").build();

            //2.上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("plugins/css/index.css")
                    .contentType("text/css")
                    .bucket("leadnews")
                    .stream(in, in.available(), -1)
                    .build();
            client.putObject(putObjectArgs);

            System.out.println("http://47.120.37.50:9000/leadnews/02-list.html");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}