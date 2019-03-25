package com.example.demo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("ip")
@Data
@Component
public class IpProperties {

    private String host;

    private String[] links;
}
