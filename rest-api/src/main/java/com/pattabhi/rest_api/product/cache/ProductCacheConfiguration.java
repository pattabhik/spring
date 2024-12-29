package com.pattabhi.rest_api.product.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCacheConfiguration {
    @Bean
    public Config productCacheConfig(){
        return new Config().setInstanceName("product-cache")
                .addMapConfig(new MapConfig().setName("products").setTimeToLiveSeconds(3000));
    }
}
