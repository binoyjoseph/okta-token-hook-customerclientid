package com.okta.examples.apiamhooks.config;

import com.okta.examples.apiamhooks.filter.HttpsEnforcer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class HttpsConfig {

    @Bean
    public Filter httpsEnforcerFilter(){
        return new HttpsEnforcer();
    }
}