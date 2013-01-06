/**
 * 
 */
package com.github.yuyang226.j500px.oauth;

import com.github.yuyang226.j500px.J500pxException;

/**
 * @author Toby Yu(yuyang226@gmail.com)
 *
 */
public class OAuthException extends J500pxException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public OAuthException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public OAuthException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public OAuthException(String message, Throwable cause) {
        super(message, cause);
    }

}
