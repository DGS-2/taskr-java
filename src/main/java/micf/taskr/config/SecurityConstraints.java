package micf.taskr.config;

public class SecurityConstraints {

    public static final String SIGN_UP_URLS = "/api/user/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET_KEY ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 36000000; //30 seconds
}