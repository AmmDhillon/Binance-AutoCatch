package com.ammdhillon.autocatch.component;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {
 
    private RestTemplate restTemplate;

    public RestTemplate getObject() {
        return restTemplate;
    }

    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() {
        restTemplate = new RestTemplate();
    }
}