package com.yuansong.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sys-config")
@Component
public class SysConfig {

    private String configStrOne;
    private String configStrTwo;
    private Integer configIntOne;

    public String getConfigStrOne() {
        return configStrOne;
    }

    public void setConfigStrOne(String configStrOne) {
        this.configStrOne = configStrOne;
    }

    public String getConfigStrTwo() {
        return configStrTwo;
    }

    public void setConfigStrTwo(String configStrTwo) {
        this.configStrTwo = configStrTwo;
    }

    public Integer getConfigIntOne() {
        return configIntOne;
    }

    public void setConfigIntOne(Integer configIntOne) {
        this.configIntOne = configIntOne;
    }
}

