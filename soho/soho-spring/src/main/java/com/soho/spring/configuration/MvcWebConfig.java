package com.soho.spring.configuration;

import com.soho.spring.extend.FastJsonHttpUTF8MessageConverter;
import com.soho.spring.model.DeftConfigData;
import com.soho.spring.model.OSSData;
import com.soho.spring.mvc.filter.SafetyFilter;
import com.soho.spring.shiro.initialize.ShiroInitializeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.List;

/**
 * @author shadow
 */
@Configuration
public class MvcWebConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private DeftConfigData deftConfigData;
    @Autowired(required = false)
    private OSSData ossData;
    @Autowired(required = false)
    private ShiroInitializeService shiroInitializeService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(ossData.getMaxFileSize()); // 设置单个文件大小
        factory.setMaxRequestSize(ossData.getMaxRequestSize()); // 设置总上传数据总大小
        return factory.createMultipartConfig();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SafetyFilter());
        registration.setName("SafetyFilter"); // 设置拦截器名称
        registration.addInitParameter("jsoupPrefix", deftConfigData.getJsoupPrefix()); // 添加默认参数
        registration.addUrlPatterns("/*"); // 设置过滤路径，/*所有路径
        registration.setOrder(1); // 设置优先级
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<HandlerInterceptor> interceptors = shiroInitializeService.initInterceptor();
        for (HandlerInterceptor interceptor : interceptors) {
            InterceptorRegistration addInterceptor = registry.addInterceptor(interceptor);
            addInterceptor.excludePathPatterns("/static/**"); // 排除配置
            addInterceptor.addPathPatterns("/**"); // 拦截配置
        }
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FastJsonHttpUTF8MessageConverter()); // 设置HTTP UTF-8 JSON对象模型
    }

}