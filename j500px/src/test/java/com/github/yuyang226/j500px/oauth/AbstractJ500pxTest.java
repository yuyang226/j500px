/**
 * 
 */
package com.github.yuyang226.j500px.oauth;

import org.junit.After;
import org.junit.Before;

import com.github.yuyang226.j500px.J500px;

/**
 * @author yayu
 *
 */
public abstract class AbstractJ500pxTest {
	public static final String CONSUMER_KEY = "xtO5q8ryOefZs8GaRiOKf1a3dWvBXlGHG19B5Euc";
	public static final String CONSUMER_SECRET = "WxzjJgOdu1jm2yLbHPhmplwsh7iYYUbEO2y2rHPc";
	
	public static final String OAUTH_TOKEN = "v6FN3e2vz5anvbgGYCKvueCs3ybDUp7vyxCWqNzt"; //"TeWDqC1sqk7daLhZ6FqUgIIq70Pb2Q31mgFHpM1v";
	public static final String OAUTH_TOKEN_SECRET = "f7kPVn5aIE4AGzKRbGopVmk7YDEAKSl85gfrh9MD"; //"2jDafMZhQIqCV7LuoFZ5KeFy41tQYPsmwsfu7sw5";
	
	protected J500px p;

	/**
	 * 
	 */
	public AbstractJ500pxTest() {
		super();
	}
	
	@Before
    public void setup() throws Exception {
        p = new J500px(CONSUMER_KEY, CONSUMER_SECRET);
        OAuth auth = new OAuth();
        auth.setToken(new OAuthToken(OAUTH_TOKEN, OAUTH_TOKEN_SECRET));
        RequestContext.getRequestContext().setOAuth(auth);
    }
    
    @After
    public void tearDown() throws Exception {
        RequestContext.getRequestContext().setOAuth(null);
        RequestContext.resetThreadLocals();
        p = null;
    }

}
