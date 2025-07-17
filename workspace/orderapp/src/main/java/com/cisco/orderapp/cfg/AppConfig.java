package com.cisco.orderapp.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class AppConfig {
    @Autowired
    private CacheManager cacheManager;

//    @Scheduled(fixedRate = 1000)
    @Scheduled(cron = "0 0/30 * * * *")
    public void clearCache() {
        System.out.println("clear cache!!!");
        cacheManager.getCacheNames().forEach(cacheName -> {
            cacheManager.getCache(cacheName).clear();
        });
    }
}
