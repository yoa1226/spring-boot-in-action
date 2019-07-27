package com.annu.action.util;

import com.annu.action.exception.DefaultException;
import com.annu.action.vo.BusinessState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ValidatorUtil {

    public static void requireNonEmpty(Object obj, String nullMsg) {
        if (Objects.isNull(obj)) {
            handle(nullMsg);
        }

        if (obj instanceof String && StringUtils.isEmpty(obj)) {
            handle(nullMsg);
        }

        if (obj instanceof Collection && CollectionUtils.isEmpty((Collection<?>)obj)) {
            handle(nullMsg);
        }
    }

    private static void handle(String nullMsg) {
        log.error("空指针异常，nullMsg: {}", nullMsg);
        throw new DefaultException(BusinessState.NPE, nullMsg);
    }


}
