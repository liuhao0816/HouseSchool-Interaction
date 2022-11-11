package com.gxa.modules.pay.config;

import com.gxa.modules.pay.interceptor.AliPayInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 支付
 *
 * @author shelei
 */
@Configuration
public class AlipayConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AliPayInterceptor()).addPathPatterns("/pay/alipay/**");
    }
}