package com.tlias.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tlias.pojo.Result;
import com.tlias.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    //运行前
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {


        String uri = req.getRequestURI();
        log.info(uri);

        if (uri.contains("login")) {
            return true;
        }

        String token = req.getHeader("token");

        if (StringUtils.hasLength(token)) {
            log.info("null tokenn");
            Result rs = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(rs);
            resp.getWriter().write(notLogin);
            return false;
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            Result rs = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(rs);
            resp.getWriter().write(notLogin);
            return false;
        }

        log.info("leagl");
        return true;
    }

    //运行后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //渲染完毕后,最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
