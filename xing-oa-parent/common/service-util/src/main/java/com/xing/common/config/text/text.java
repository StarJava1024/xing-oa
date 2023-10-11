package com.xing.common.config.text;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: Wang Xing
 * @Date: 16:18 2023/9/14
 */
@Slf4j
public class text {

    public static void main(String[] args) {
        String content = "{\"code\":200,\"message\":\"成功\",\"data\":{\"records\":[],\"total\":10,\"size\":5,\"current\":1,\"orders\":[],\"optimizeCountSql\":true,\"hitCount\":false,\"countId\":null,\"maxLimit\":null,\"searchCount\":true,\"pages\":2}}";
        AES aes = new AES(Mode.CTS, Padding.PKCS5Padding, "0CoJUm6Qyw8W8jud".getBytes(), "0102030405060708".getBytes());

        AES aes1 = new AES("CBC", "PKCS7Padding",
                // 密钥，可以自定义
                "HFYOUO".getBytes(),
                // iv加盐，按照实际需求添加
                "DYgjCEIMVrj2W9xN".getBytes());

        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

        // 构建
        // AES aes = SecureUtil.aes(key);
        log.info("构建：{}", aes);
        // 加密
        byte[] encrypt = aes.encrypt(content);
        // log.info("加密：{}", encrypt);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);
        // log.info("解密：{}", decrypt);
        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        log.info("加密：{}", encryptHex);
        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        log.info("解密：{}", decryptStr);
        System.out.println("OK");
    }

}
