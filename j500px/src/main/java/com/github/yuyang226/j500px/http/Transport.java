package com.github.yuyang226.j500px.http;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.github.yuyang226.j500px.J500pxConstants;
import com.github.yuyang226.j500px.J500pxException;
import com.github.yuyang226.j500px.oauth.OAuthTokenParameter;
import com.github.yuyang226.j500px.oauth.OAuthUtils;

/**
 * The abstract Transport class provides a common interface for transporting requests to the 500px servers. 
 * This would ensure further enhancements for something beyond REST.
 *
 * @author yayu
 */
public abstract class Transport {

    public static final String REST = "REST";

    private String transportType;
    protected Class<?> responseClass;
    private String path;
    private String host;
    private int port = 80;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transport) {
        this.transportType = transport;
    }

    /**
     * Invoke an HTTP GET request on a remote host.  You must close the InputStream after you are done with.
     *
     * @param path The request path
     * @param parameters The parameters (collection of Parameter objects)
     * @return The Response
     * @throws IOException
     * @throws JSONException
     */
    public abstract Response get(String path, List<Parameter> parameters) throws IOException, JSONException;

    /**
     * Invoke an HTTP POST request on a remote host.
     *
     * @param path The request path
     * @param parameters The parameters (collection of Parameter objects)
     * @return The Response object
     * @throws IOException
     * @throws JSONException
     */
    protected abstract Response post(String path, List<Parameter> parameters) throws IOException, JSONException;
    
    public Response getJSON(String apiSharedSecret, String path,
            List<Parameter> parameters) throws IOException, JSONException, J500pxException {
        signIfOAuth(apiSharedSecret, path, parameters, false);
        return get(path, parameters);
    }
    
    private void signIfOAuth(String apiSharedSecret, String path,
            List<Parameter> parameters, boolean isPost) throws J500pxException {
    	boolean isOAuth = false;
        for (int i = parameters.size() - 1; i >= 0; i--) {
            if (parameters.get(i) instanceof OAuthTokenParameter) {
                isOAuth = true;
                break;
            }
        }
        if (isOAuth) {
        	if (isPost) {
        		OAuthUtils.addOAuthParams(apiSharedSecret, J500pxConstants.DEFAULT_HOST_FULL + path, parameters);
        	} else {
        		OAuthUtils.addOAuthParamsGet(apiSharedSecret, J500pxConstants.DEFAULT_HOST_FULL + path, parameters);
        	}
        }
    }
    
    public Response postJSON(String apiSharedSecret, String path,
            List<Parameter> parameters) throws IOException, JSONException, J500pxException {
    	signIfOAuth(apiSharedSecret, path, parameters, true);
        return post(path, parameters);
    }

    /**
     * @return Returns the path.
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path The path to set.
     */
    public void setPath(String path) {
        this.path = path;
    }

    public Class<?> getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(Class<?> responseClass) {
        if (responseClass == null) {
            throw new IllegalArgumentException("The response Class cannot be null");
        }
        this.responseClass = responseClass;
    }

}
