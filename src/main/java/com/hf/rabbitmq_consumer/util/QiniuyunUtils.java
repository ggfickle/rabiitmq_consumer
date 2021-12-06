package com.hf.rabbitmq_consumer.util;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName: QiniuyunUtils
 * @author: xiehongfei
 * @description:
 **/

@Component
@Slf4j
public class QiniuyunUtils {

    @Value("${qi_niu_yun.access_key}")
    private String accessKey;

    @Value("${qi_niu_yun.secret_key}")
    private String secretKey;

    @Value("${qi_niu_yun.bucket}")
    private String bucket;

    /**
    * @Author xiehongfei
    * @Description: 七牛云上传文件
    * @Param
    * @Return void
    **/
    public void uploadFile(String fileName) {
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);

//        byte[] uploadBytes = "hello qiniu cloud".getBytes(StandardCharsets.UTF_8);
        File file = new File("E:\\小米5\\最新相册\\相机\\1537011211392_weibo.jpg");
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file, UUID.randomUUID().toString().replace("-","").concat("/").concat(fileName), upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSONObject.parseObject(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            log.error(ex.getLocalizedMessage());
        }

    }
}
