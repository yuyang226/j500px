package com.github.yuyang226.j500px.http;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author yayu
 */
public interface Response {

    void parse(String rawMessage) throws JSONException;

    boolean isError();

    String getErrorMessage();
    
    JSONObject getData();
    
    String getRawResponse();
    
}
