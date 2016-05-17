package com.github.apache.tomcat;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

/**
 * Created by zk_chs on 16/5/17.
 *
 * @author Zhangkai
 */
@Configuration
public class TomcatConfig {

    /**
     * 功能描述：配置tomcat<br/>
     * 创建时间：2016-5-17<br/>
     * 作者：ZhangKai
     *
     * @return the embedded servlet container factory
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer (){
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.setUriEncoding(Charset.forName("UTF-8"));
        return tomcat;
    }

}
