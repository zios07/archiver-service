package com.cirb.archive.config.security;

public class SecurityConstants {

	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 4 * 60 * 60 * 1000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String LOGIN_URL = "/api/authentication/authenticate";
  public static final String TOKEN_PARAM_NAME = "access_token";

	private SecurityConstants() {

	}

}
