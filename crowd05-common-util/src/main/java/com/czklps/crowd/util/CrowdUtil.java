package com.czklps.crowd.util;

import com.czklps.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;


public class CrowdUtil {

    /**
     * 判断请求类型是 ajax 还是 普通请求
     *
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        String contentType = request.getContentType();
        String header = request.getHeader("X-Requested-With");
        return contentType != null && contentType.contains("application/json") || header != null && header.equals("XMLHttpRequest");
    }

    /**
     * 将字符串转换成 MD5 的格式
     *
     * @param source
     * @return
     */
    public static String md5(String source) {
        if (source == null || source.length() == 0) {
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        String algorithm = "md5";
        try {
            MessageDigest instance = MessageDigest.getInstance(algorithm);

            byte[] input = source.getBytes(StandardCharsets.UTF_8);

            byte[] digest = instance.digest(input);

            int signum = 1;

            BigInteger bigInteger = new BigInteger(signum, digest);

            int radix = 16;

            return bigInteger.toString(radix).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
