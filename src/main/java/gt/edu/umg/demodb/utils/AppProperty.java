package gt.edu.umg.demodb.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "settings")
public class AppProperty {

	private String jwtSecret;
	private int jwtExpiration;
	private int showException;
	private String seedEncrypt;
	private String fromMail;
	private String urlSite;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public int getJwtExpiration() {
		return jwtExpiration;
	}

	public void setJwtExpiration(int jwtExpiration) {
		this.jwtExpiration = jwtExpiration;
	}

	public int getShowException() {
		return showException;
	}

	public void setShowException(int showException) {
		this.showException = showException;
	}

	public String getSeedEncrypt() {
		return seedEncrypt;
	}

	public void setSeedEncrypt(String seedEncrypt) {
		this.seedEncrypt = seedEncrypt;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getUrlSite() {
		return urlSite;
	}

	public void setUrlSite(String urlSite) {
		this.urlSite = urlSite;
	}

}
