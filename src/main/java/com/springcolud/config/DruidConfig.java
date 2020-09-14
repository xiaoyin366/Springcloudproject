package com.springcolud.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Value("${druid.userName}")
    private  String druidUsername;

    @Value("${druid.pwd}")
    private  String druidPwd;

    @Value("${druid.ipAllow}")
    private String ipAllow;

    @Value("${druid.ipDeny}")
    private String ipDeny;

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置Druid的监控
    //1.配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", druidUsername);
        initParams.put("loginPassword", druidPwd);
        initParams.put("allow", ipAllow);            //ip白名单，默认全部
        //initParams.put("deny", ipDeny);   //ip黑名单
        bean.setInitParameters(initParams);
        return bean;
    }

    //2.配置一个监控filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initparams = new HashMap<>();
        initparams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initparams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
