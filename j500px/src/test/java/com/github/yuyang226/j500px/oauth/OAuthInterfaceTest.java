/**
 * 
 */
package com.github.yuyang226.j500px.oauth;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Ignore;
import org.junit.Test;

import com.github.yuyang226.j500px.J500pxException;

/**
 * @author yayu
 *
 */
public class OAuthInterfaceTest extends AbstractJ500pxTest{
	
	/**
	 * Test method for {@link com.github.yuyang226.j500px.oauth.OAuthInterface#getRequestToken(java.lang.String)}.
	 * @throws J500pxException 
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void testGetRequestToken() throws IOException, J500pxException, ParserConfigurationException {
		OAuthToken token = p.getOAuthInterface().getRequestToken("http://localhost/hello");
		assertNotNull(token);
	}

	/**
	 * Test method for {@link com.github.yuyang226.j500px.oauth.OAuthInterface#getAccessToken(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws ParserConfigurationException 
	 * @throws J500pxException 
	 * @throws IOException 
	 */
	@Test
	@Ignore
	public void testGetAccessToken() throws ParserConfigurationException, IOException, J500pxException {
		OAuth token = p.getOAuthInterface().getAccessToken("ZPEJg3mBGI0VnTvit7zpjFVYg6hioff0mGGGrim1", 
				"ZDZUzBf2DvMkQB3Jkq0fsXD9CBsEotGqwAbCkEkn", "LeVPOuJcP5NjIwGEOgWW");
		assertNotNull(token);
	}

	/**
	 * Test method for {@link com.github.yuyang226.j500px.oauth.OAuthInterface#buildAuthenticationUrl(com.github.yuyang226.j500px.oauth.OAuthToken)}.
	 * @throws ParserConfigurationException 
	 * @throws MalformedURLException 
	 */
	@Test
	public void testBuildAuthenticationUrl() throws ParserConfigurationException, MalformedURLException {
		OAuthToken oauthToken = new OAuthToken("ZPEJg3mBGI0VnTvit7zpjFVYg6hioff0mGGGrim1", 
				"ZDZUzBf2DvMkQB3Jkq0fsXD9CBsEotGqwAbCkEkn");
		URL url = p.getOAuthInterface().buildAuthenticationUrl(oauthToken);
		assertNotNull(url);
		System.out.println(url);
	}

}
