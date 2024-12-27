package com.danal.springbatch.config;

import com.danal.springbatch.entity.Restaurant;
import com.danal.springbatch.model.RestaurantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;

@Slf4j
public class RestaurantSkipListener implements SkipListener<RestaurantDto, Restaurant> {
    @Override
    public void onSkipInProcess(RestaurantDto item, Throwable t) {
        log.info("[RestaurantSkipListener#SkipInProcess] item:{}, error:{}", item, t.getMessage());
    }
}
