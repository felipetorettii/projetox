package com.esucri.projetox.adapters.web;

import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@ConfigurationProperties(prefix = "http")
@Generated
public class PathEndpoints implements Serializable {
  public static final String ENDPOINT_USER = "/users";
  public static final String ENDPOINT_PROMOTER = "/promoters";
  public static final String ENDPOINT_LOGIN = "/logins";
  public static final String ENDPOINT_EVENT = "/events";
}
