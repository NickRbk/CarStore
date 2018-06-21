package cursor.rybak.store.security;

public interface SecurityConstants {
	String SECRET = "SecretKeyToGenJWTs";
	long EXPIRATION_TIME = 864_000_000; // 10 days
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
	String LOGOUT_URL = "/logout";
	String SIGN_UP_URL = "/sellers/sign-up";
	String[] PUBLIC_URL = {"/store", "/store/**"};
}
