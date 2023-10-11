package com.xing.auth.service;

import com.xing.system.vo.LoginVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xing
 * @since 2023-08-17
 */
public interface IndexService {
    Map<String, Object> getLogin(LoginVO loginVo);

    Map<String, Object> info(HttpServletRequest request);
}
