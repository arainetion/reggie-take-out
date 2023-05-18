package com.hy.reggietakeout.filter;

import com.alibaba.fastjson.JSON;
import com.hy.reggietakeout.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author arainetion
 * @version 1.0
 * @date 2023/5/18 15:53
 * @description 检查用户是否已经登录
 */
@Slf4j
@WebFilter(filterName = "LoginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",request.getRequestURI());

        //定义不需要处理的请求路径
        String[] uris = new String[]{
          "/employee/login",
          "/employee/logout",
          "/backend/**",
          "/front/**"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(uris, requestURI);

        //3、如果不需要处理，则直接放行
        if (check) {
            log.info("本次请求：{}不需要处理",request.getRequestURI());
            filterChain.doFilter(request,response);
            return;
        }

        //4、判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }

        //5、如果未登录则返回登录结果,通过输出流方式向客户端页面响应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配，检查本次请求是否需要被放行
     * @param uris
     * @param requestURI
     * @return
     */
    public boolean check(String[] uris, String requestURI){

        for (String uri : uris) {
            boolean match = PATH_MATCHER.match(uri, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
