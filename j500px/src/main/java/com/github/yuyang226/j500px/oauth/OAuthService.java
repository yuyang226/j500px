/**
 * 
 */
package com.github.yuyang226.j500px.oauth;

/**
 * Official documents: http://developer.500px.com/docs/oauth
 * @author yayu
 *
 */
public class OAuthService {
	private String consumerKey;
	private String consumerSecret;

	/**
	 * 
	 */
	public OAuthService(String consumerKey, String consumerSecret) {
		super();
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}
	
	public void requestToken(String callbackUrl) {
		
	}

}
