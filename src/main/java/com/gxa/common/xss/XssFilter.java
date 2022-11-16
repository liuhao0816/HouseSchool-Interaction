package com.gxa.common.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * XSS过滤
 *
 * @author shelei
 * @since 1.0.0
 */
public class XssFilter implements Filter {

  List<String> ignoreXSSURIList = new ArrayList<>();

  //将富文本保存的编辑接口添加到忽略列表中
  public XssFilter(){
    ignoreXSSURIList.add("/sys/vote/add");

  }
  @Override
  public void init(FilterConfig config) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    if(!ignoreXSSURIList.contains(((HttpServletRequest) request).getRequestURI())){
      XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
              (HttpServletRequest) request);
      chain.doFilter(xssRequest, response);
    }else {
      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {

  }

}