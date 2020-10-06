package com.scc.show.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("showservice")
public class AuthenticateConfig {

   @Value("${showservice.authenticationKey}")
   private String key;

   @Value("${showservice.authenticationValue}")
   private String value;

   public String getKey() {
      return this.key;
   }

   public String getValue() {
      return this.value;
   }

}