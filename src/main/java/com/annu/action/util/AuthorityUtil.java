package com.annu.action.util;

import com.annu.action.exception.DefaultException;
import com.annu.action.vo.BusinessState;
import com.annu.action.vo.UserVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorityUtil {

    public static final String TOKEN = "token";

    public static UserVo parseToken(String token) {
        try {
            //todo parse token
            return UserVo.builder()
                    .Id("33432")
                    .name("张三")
                    .telephone("13400987656")
                    .build();
        } catch (Exception ex) {
            log.error("解析token异常，token: {}, exception msg: {}, exception stack: ", token, ex.getMessage(), ex);
            throw new DefaultException(BusinessState.UNAUTHORIZED);
        }
    }

}
