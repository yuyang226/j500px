/**
 * 
 */
package com.github.yuyang226.j500px.oauth;

import java.io.Serializable;

/**
 * @author Toby Yu(yuyang226@gmail.com)
 *
 */
public class OAuth implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private OAuthToken token;

    /**
     * 
     */
    public OAuth() {
        super();
    }

    /**
     * @return the token
     */
    public OAuthToken getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(OAuthToken token) {
        this.token = token;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OAuth [token=" + token + "]";
    }
    
}
