package org.example.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")  // 配置文件中的前缀，例如jwt.adminSecretKey
public class JwtProperties {

    private String adminSecretKey;
    private long adminTtl;

    // Getters and Setters
    public String getAdminSecretKey() {
        return adminSecretKey;
    }

    public void setAdminSecretKey(String adminSecretKey) {
        this.adminSecretKey = adminSecretKey;
    }

    public long getAdminTtl() {
        return adminTtl;
    }

    public void setAdminTtl(long adminTtl) {
        this.adminTtl = adminTtl;
    }
}
