package com.github.yuyang226.j500px.oauth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.yuyang226.j500px.J500pxConstants;
import com.github.yuyang226.j500px.J500pxException;
import com.github.yuyang226.j500px.http.Parameter;
import com.github.yuyang226.j500px.http.REST;
import com.github.yuyang226.j500px.http.Transport;
import com.github.yuyang226.j500px.utils.UrlUtilities;

/**
 * Authentication interface for the new 500PX OAuth support: 
 * http://developer.500px.com/docs/oauth
 *
 * @author Toby Yu
 */
public class OAuthInterface {

    public static final String KEY_OAUTH_CALLBACK_CONFIRMED = "oauth_callback_confirmed";
    public static final String KEY_OAUTH_TOKEN = "oauth_token";
    public static final String KEY_OAUTH_TOKEN_SECRET = "oauth_token_secret";
    public static final String KEY_OAUTH_VERIFIER = "oauth_verifier";

    public static final String PARAM_OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    public static final String PARAM_CONSUMER_KEY = "consumer_key";
    public static final String PARAM_OAUTH_TOKEN = "oauth_token";

    private String consumerKey;
    private String consumerSecret;
    private REST oauthTransport;
    private static final Logger logger = LoggerFactory.getLogger(OAuthInterface.class);

    /**
     * Construct the AuthInterface.
     *
     * @param apiKey The API key
     * @param transport The Transport interface
     */
    public OAuthInterface(
            String apiKey,
            String sharedSecret,
            Transport transport
    ) {
        this.consumerKey = apiKey;
        this.consumerSecret = sharedSecret;
        this.oauthTransport = (REST)transport;
    }

    /**
     * Get a request token.
     *
     * @return the frob
     * @throws IOException
     * @throws J500pxException
     */
    public OAuthToken getRequestToken(String callbackUrl) throws IOException, J500pxException {
        if (callbackUrl == null)
            callbackUrl = "oob";
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("oauth_callback", callbackUrl));
        parameters.add(new Parameter(OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, consumerKey));
        OAuthUtils.addBasicOAuthParams(parameters);
        String signature = OAuthUtils.getSignature(
                OAuthUtils.REQUEST_METHOD_GET, 
                J500pxConstants.OAUTH_URL_REQUEST_TOKEN, 
                parameters,
                consumerSecret, null);
        // This method call must be signed.
        parameters.add(new Parameter("oauth_signature", signature));

        logger.info("Getting Request Token with parameters: {}", parameters);
        Map<String, String> response = this.oauthTransport.getMapData(
                true, J500pxConstants.PATH_OAUTH_REQUEST_TOKEN, parameters);
        if (response.isEmpty()) {
            throw new J500pxException("Empty Response");
        }

        if (response.containsKey(KEY_OAUTH_CALLBACK_CONFIRMED) == false
                || Boolean.valueOf(response.get(KEY_OAUTH_CALLBACK_CONFIRMED)) == false) {
            throw new J500pxException("Invalid response: " + response);
        }
        String token = response.get(KEY_OAUTH_TOKEN);
        String token_secret = response.get(KEY_OAUTH_TOKEN_SECRET);
        logger.info("Response: {}", response);
        OAuth oauth = new OAuth();
        oauth.setToken(new OAuthToken(token, token_secret));
        return oauth.getToken();
    }

    /**
     * @param requestToken
     * @param tokenSecret
     * @param oauthVerifier
     * @throws IOException
     * @throws J500pxException
     */
    public OAuth getAccessToken(String token, String tokenSecret, String oauthVerifier) 
    throws IOException, J500pxException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter(OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, consumerKey));
        parameters.add(new OAuthTokenParameter(token));
        parameters.add(new Parameter(KEY_OAUTH_VERIFIER, oauthVerifier));
        OAuthUtils.addBasicOAuthParams(parameters);
        //OAuthUtils.signPost(sharedSecret, URL_ACCESS_TOKEN, parameters);
        
        String signature = OAuthUtils.getSignature(
                OAuthUtils.REQUEST_METHOD_POST, 
                J500pxConstants.OAUTH_URL_ACCESS_TOKEN, 
                parameters,
                consumerSecret, tokenSecret);
        // This method call must be signed.
        parameters.add(new Parameter("oauth_signature", signature));
        
        //OAuthUtils.addOAuthParams(this.sharedSecret, URL_ACCESS_TOKEN, parameters);

        Map<String, String> response = this.oauthTransport.getMapData(false, J500pxConstants.PATH_OAUTH_ACCESS_TOKEN, parameters);
        if (response.isEmpty()) {
            throw new J500pxException("Empty Response");
        }
        logger.info("Response: {}", response);
        OAuth result = new OAuth();
        result.setToken(new OAuthToken(
                response.get("oauth_token"), response.get("oauth_token_secret")));
        RequestContext.getRequestContext().setOAuth(result);
        return result;
    }


    /**
     * Build the authentication URL using the given permission and frob.
     *
     * The hostname used here is www.flickr.com. It differs from the api-default
     * api.flickr.com.
     * 
     * @param permission The Permission
     * @param frob The frob returned from getFrob()
     * @return The URL
     * @throws MalformedURLException
     */
    public URL buildAuthenticationUrl(OAuthToken oauthToken) throws MalformedURLException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter(PARAM_OAUTH_TOKEN, oauthToken.getOauthToken()));

        int port = oauthTransport.getPort();

        return UrlUtilities.buildUrl(J500pxConstants.DEFAULT_HOST, port, J500pxConstants.PATH_OAUTH_AUTHORIZE, parameters);
    }

}
