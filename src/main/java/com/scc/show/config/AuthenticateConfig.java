package com.scc.show.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("showservice")
public class AuthenticateConfig {

   /*
    * https://github.com/spring-cloud/spring-cloud-gcp/tree/master/spring-cloud-gcp
    * -samples/spring-cloud-gcp-config-sample
    * https://cloud.google.com/sdk/gcloud/reference/beta/runtime-config/configs/
    * variables/unset
    * https://allegro.tech/2020/01/spring-boot-services-configuration-on-gcp.html
    * example.property: "I AM IN THE DEFAULT" ! gcloud config setproject
    * lof-ws-test ! gcloud beta runtime-config configs create showservice_default !
    * gcloud beta runtime-config configs variables set ^ !
    * --config-name=showservice_default ^ authenticationValue "31/12/2057" ! gcloud
    * beta runtime-config configs variables get-value authenticationValue
    * --config-name=showservice ! gcloud beta runtime-config configs variables list
    * --config-name=showservice_default ! curl -XPOST
    * http://localhost:8093/actuator/refresh ! curl
    * http://localhost:8093/actuator/env -i -X GET
    * 
    * gcloud beta runtime-config configs variables unset ^
    * --config-name=showservice ^ authentification.value_default gcloud beta
    * runtime-config configs delete showservice s
    */

   @Value("${authenticationValue}")
   private String authenticationValue;

   /*
    * private List<String> keys = new ArrayList<String>();
    * 
    * public List<String> getKeys() { return this.keys; }
    */

   public String getAuthenticationValue() {
      return this.authenticationValue;
   }

}