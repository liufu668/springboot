package com.study.jwt.util;

import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.study.jwt.dto.PayloadDto;

import java.text.ParseException;
import java.util.Date;

/**
 * 工具类,用于提供生成和验证JWT的方法
 */
public class JwtUtil {
    //默认密钥
    public static final String DEFAULT_SECRET="mySecret";

    /**
     * 使用HMAC SHA-256签名算法
     * @param payloadStr 有效载荷
     * @param secret 密钥
     * @return JWS串
     */
    public static String generateTokenByHMAC(String payloadStr,String secret) throws JOSEException {
        //创建JWS头,设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        //将载荷信息封装到Payload中
        Payload payload = new Payload(payloadStr);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建HMAC签名器
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    /**
     * 验证签名,提取有效载荷,以PayloadDto对象形式返回
     * @param token JWS串
     * @param secret 密钥
     * @return PayloadDto对象
     */
    public static PayloadDto verifyTokenByHMAC(String token,String secret) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        //创建HMAC验证器
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new JOSEException("token签名不合法!");
        }
        String payload = jwsObject.getPayload().toString();
        PayloadDto payloadDto = JSONUtil.toBean(payload, PayloadDto.class);
        if (payloadDto.getExp() < new Date().getTime()){
            throw new JOSEException("token已过期!");
        }
        return payloadDto;
    }
}
