/**
 * 
 */
package com.github.yuyang226.j500px.oauth;

import com.github.yuyang226.j500px.http.Parameter;


/**
 * @author Toby Yu(yuyang226@gmail.com)
 *
 */
public class OAuthTokenParameter extends Parameter {

    /**
     * @param name
     * @param value
     */
    public OAuthTokenParameter(Object value) {
        super(OAuthInterface.KEY_OAUTH_TOKEN, value);
    }

}
