package com.mixin.answer.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class JeeRiggerConfig implements WebMvcConfigurer {

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new SingleRequestBodyResolver());
//    }


    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
    /**
     * 定义json转换格式
     *
     * @return
     */
//    @Bean
//    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
//        // 1、需要先定义一个 convert 转换消息的对象;
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.WriteNullNumberAsZero,
//                SerializerFeature.DisableCircularReferenceDetect
//        );
//        //处理中文乱码问题(不然出现中文乱码)
//        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastConverter.setSupportedMediaTypes(fastMediaTypes);
//
//        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        //3、在convert中添加配置信息.
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        return fastConverter;
//    }



}
