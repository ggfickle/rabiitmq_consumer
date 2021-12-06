package com.hf.rabbitmq_consumer.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName: QiniuyunUtilsTest
 * @author: xiehongfei
 * @description:
 **/
@SpringBootTest
class QiniuyunUtilsTest {

    @Autowired
    private QiniuyunUtils qiniuyunUtils;

    @Test
    public void test1() {
        qiniuyunUtils.uploadFile("xiaomi5");
//        "E:\小米5\最新相册\相机\1537011211392_weibo.jpg"

    }
}
