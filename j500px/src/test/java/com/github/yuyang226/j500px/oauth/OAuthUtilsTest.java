/**
 * 
 */
package com.github.yuyang226.j500px.oauth;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.yuyang226.j500px.J500pxConstants;
import com.github.yuyang226.j500px.J500pxException;
import com.github.yuyang226.j500px.http.Parameter;

/**
 * @author yayu
 *
 */
public class OAuthUtilsTest extends AbstractJ500pxTest{

	/**
	 * @throws J500pxException 
	 * 
	 */
	@Test
	public void testSignPost() throws J500pxException {
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new Parameter(J500pxConstants.PARAM_OAUTH_CONSUMER_KEY,
                p.getConsumerKey()));
        OAuthUtils.addOAuthToken(parameters);
        {
//    		OAuthUtils.addBasicOAuthParams(parameters);
            parameters.add(new Parameter("oauth_nonce", "QF5ywF"));
            parameters.add(new Parameter("oauth_timestamp", "1357441775"));
            OAuthUtils.addOAuthSignatureMethod(parameters);
            OAuthUtils.addOAuthVersion(parameters);
        }

		OAuthUtils.signGet(AbstractJ500pxTest.CONSUMER_SECRET, 
				J500pxConstants.DEFAULT_HOST_FULL + J500pxConstants.PATH_USERS, parameters);
		String data = parameters.get(parameters.size() - 1).getValue().toString();
		System.out.println(data);
	}

}
