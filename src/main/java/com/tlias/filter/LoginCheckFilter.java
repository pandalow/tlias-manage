package com.tlias.filter;

import com.alibaba.fastjson.JSONObject;
import com.tlias.pojo.Result;
import com.tlias.util.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


        String uri = req.getRequestURI();
        log.info(uri);

        if (uri.contains("login")) {
            chain.doFilter(req, resp);
            return;
        }

        String token = req.getHeader("token");

        if (StringUtils.hasLength(token)) {
            log.info("null tokenn");
            Result rs = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(rs);
            resp.getWriter().write(notLogin);
        }

        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            Result rs = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(rs);
            resp.getWriter().write(notLogin);
            return;
        }

        log.info("leagal");
        chain.doFilter(req, resp);
    }


}
