package com.github.yuyang226.j500px.http;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Flickr Response object.
 *
 * @author yayu
 */
public class RESTResponse implements Response {

    private String stat;
    
    private JSONObject jsonObj;
    private String rawResponse;
    private String errorMessage;
    
    /**
     * @param rawResponse
     * @throws JSONException 
     */
    public RESTResponse(String rawResponse) throws JSONException {
        super();
        this.rawResponse = rawResponse;
        parse(this.rawResponse);
    }

    /* (non-Javadoc)
     * @see com.googlecode.flickrjandroid.Response#parse(java.lang.String)
     */
    @Override
    public void parse(String rawMessage) throws JSONException {
        this.rawResponse = rawMessage;
        this.jsonObj = new JSONObject(rawMessage);
        stat = this.jsonObj.optString("status");
        if ("ok".equals(stat)) {
            
        } else if ("fail".equals(stat)) {
            this.errorMessage = this.jsonObj.getString("error");
        }
    }

    public String getStat() {
        return stat;
    }

    @Override
    public JSONObject getData() {
        return this.jsonObj;
    }

    public boolean isError() {
        return errorMessage != null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /* (non-Javadoc)
     * @see com.googlecode.flickrjandroid.Response#getRawResponse()
     */
    @Override
    public String getRawResponse() {
        return rawResponse;
    }

}
