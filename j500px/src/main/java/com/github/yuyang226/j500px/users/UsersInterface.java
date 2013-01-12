/**
 * 
 */
package com.github.yuyang226.j500px.users;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.yuyang226.j500px.J500pxConstants;
import com.github.yuyang226.j500px.J500pxException;
import com.github.yuyang226.j500px.http.Parameter;
import com.github.yuyang226.j500px.http.Response;
import com.github.yuyang226.j500px.http.Transport;
import com.github.yuyang226.j500px.oauth.OAuthInterface;
import com.github.yuyang226.j500px.oauth.OAuthUtils;

/**
 * @author yayu
 *
 */
public class UsersInterface {
	private String apiKey;
    private String sharedSecret;
    private Transport transportAPI;
    
    static final ThreadLocal<DateFormat> SHORT_DATE_FORMATS = new ThreadLocal<DateFormat>() {
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        }
    };
    
    /**
	 * 
	 */
	public UsersInterface(String apiKey, String sharedSecret,
            Transport transportAPI) {
		super();
		this.apiKey = apiKey;
		this.sharedSecret = sharedSecret;
		this.transportAPI = transportAPI;
	}
	
	/**
	 * OAuth required
	 * @return the profile information for the current user.
	 * @throws J500pxException
	 * @throws IOException
	 * @throws JSONException
	 */
	public User getUserProfile() throws J500pxException, IOException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter(J500pxConstants.PARAM_OAUTH_CONSUMER_KEY,
                apiKey));
        OAuthUtils.addOAuthToken(parameters);
        Response response = transportAPI.getJSON(sharedSecret, J500pxConstants.PATH_USERS, parameters);
        if (response.isError()) {
            throw new J500pxException(response.getErrorMessage());
        }

        JSONObject userElement = response.getData().getJSONObject("user");
		return UserUtils.parseUserObject(userElement);
	}
	
	/**
	 * 
	 * Returns the profile information for a specified user.
	 * 
	 * (required) One of the unique user identifiers.
	 * 
	 * @param userId
	 * @param userName
	 * @param email the eMail address of the user to search for
	 * @return the profile information for a specific user.
	 * @throws J500pxException
	 * @throws IOException
	 * @throws JSONException
	 */
	public User getUserProfile(int userId, String userName, String email) throws J500pxException, IOException, JSONException {
		if (userId < 0 && userName == null && email == null) {
			throw new IllegalArgumentException("You must provide either userId, userName or email");
		}
        List<Parameter> parameters = new ArrayList<Parameter>();
        if (userId > 0) {
        	parameters.add(new Parameter("id", userId));
        } else if (userName != null) {
        	parameters.add(new Parameter("username", userName));
        } else if (email != null) {
        	parameters.add(new Parameter("email", email));
        }
        
        boolean signed = OAuthUtils.hasSigned();

		if (signed) {
			OAuthUtils.addOAuthToken(parameters);
			parameters.add(new Parameter(
					OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		} else {
			parameters.add(new Parameter(OAuthInterface.PARAM_CONSUMER_KEY,
					apiKey));
		}
		
		Response response = signed ? transportAPI.getJSON(sharedSecret,
				J500pxConstants.PATH_USERS_SHOW, parameters) : transportAPI.get(
				J500pxConstants.PATH_USERS_SHOW, parameters);
        if (response.isError()) {
            throw new J500pxException(response.getErrorMessage());
        }

        JSONObject userElement = response.getData().getJSONObject("user");
		return UserUtils.parseUserObject(userElement);
	}
	
	/**
	 * @param userId Required user ID
	 * @param pageNum the specified page of the resource. Page numbering is 1-based.
	 * @param pageSize Results Per Page, default 20, max 100.
	 * @return
	 * @throws J500pxException
	 * @throws IOException
	 * @throws JSONException
	 */
	public UserList getUserFriends(int userId, int pageNum, int pageSize) throws J500pxException, IOException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        boolean signed = OAuthUtils.hasSigned();

		if (signed) {
			OAuthUtils.addOAuthToken(parameters);
			parameters.add(new Parameter(
					OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		} else {
			parameters.add(new Parameter(OAuthInterface.PARAM_CONSUMER_KEY,
					apiKey));
		}
		if (pageNum > 0) {
			parameters.add(new Parameter("page", pageNum));
		}
		if (pageSize > 0) {
			parameters.add(new Parameter("rpp", pageSize));
		}
		
		final String path = String.format(Locale.US, J500pxConstants.PATH_USERS_FRIENDS, userId);
		
		Response response = signed ? transportAPI.getJSON(sharedSecret,
				path, parameters) : transportAPI.get(path, parameters);
        if (response.isError()) {
            throw new J500pxException(response.getErrorMessage());
        }
        
        UserList userList = new UserList();
        userList.setTotalPages(response.getData().getInt("friends_pages"));
        userList.setCurrentPage(response.getData().getInt("page"));
        userList.setTotalItems(response.getData().getInt("friends_count"));
        JSONArray friendsObj = response.getData().optJSONArray("friends");
        for (int i = 0; friendsObj != null && i < friendsObj.length(); i++) {
        	userList.add(UserUtils.parseUserObject(friendsObj.getJSONObject(i)));
        }
        userList.setPerPage(userList.size());
        return userList;
	}
	
	/**
	 * Returns a list of users who follow the specified user.
	 * 
	 * @param userId (required) — ID of the user.
	 * @param pageNum Return the specified page of the resource. Page numbering is 1-based.
	 * @return
	 * @throws J500pxException
	 * @throws IOException
	 * @throws JSONException
	 */
	public UserList getUserFollowers(int userId, int pageNum) throws J500pxException, IOException, JSONException {
        List<Parameter> parameters = new ArrayList<Parameter>();
        boolean signed = OAuthUtils.hasSigned();

		if (signed) {
			OAuthUtils.addOAuthToken(parameters);
			parameters.add(new Parameter(
					OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		} else {
			parameters.add(new Parameter(OAuthInterface.PARAM_CONSUMER_KEY,
					apiKey));
		}
		if (pageNum > 0) {
			parameters.add(new Parameter("page", pageNum));
		}
		
		final String path = String.format(Locale.US, J500pxConstants.PATH_USERS_FOLLOWERS, userId);
		
		Response response = signed ? transportAPI.getJSON(sharedSecret,
				path, parameters) : transportAPI.get(path, parameters);
        if (response.isError()) {
            throw new J500pxException(response.getErrorMessage());
        }
        
        UserList userList = new UserList();
        userList.setTotalPages(response.getData().getInt("followers_pages"));
        userList.setCurrentPage(response.getData().getInt("page"));
        userList.setTotalItems(response.getData().getInt("followers_count"));
        JSONArray friendsObj = response.getData().optJSONArray("followers");
        for (int i = 0; friendsObj != null && i < friendsObj.length(); i++) {
        	userList.add(UserUtils.parseUserObject(friendsObj.getJSONObject(i)));
        }
        userList.setPerPage(userList.size());
        return userList;
	}
	
	/**
	 * Return listing of ten (up to one hundred) users from search results for a specified search term.
	 * 
	 * @param term
	 * @return
	 * @throws J500pxException
	 * @throws IOException
	 * @throws JSONException
	 */
	public UserList searchUsers(String term) throws J500pxException, IOException, JSONException {
		if (term == null) {
			throw new IllegalArgumentException("term is required");
		}
        List<Parameter> parameters = new ArrayList<Parameter>();
        boolean signed = OAuthUtils.hasSigned();

		if (signed) {
			OAuthUtils.addOAuthToken(parameters);
			parameters.add(new Parameter(
					OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		} else {
			parameters.add(new Parameter(OAuthInterface.PARAM_CONSUMER_KEY,
					apiKey));
		}
		parameters.add(new Parameter("term", term));
		
		Response response = signed ? transportAPI.getJSON(sharedSecret,
				J500pxConstants.PATH_USERS_SEARCH, parameters) : 
					transportAPI.get(J500pxConstants.PATH_USERS_SEARCH, parameters);
        if (response.isError()) {
            throw new J500pxException(response.getErrorMessage());
        }
        
        UserList userList = new UserList();
        userList.setTotalPages(response.getData().getInt("total_pages"));
        userList.setCurrentPage(response.getData().getInt("current_page"));
        userList.setTotalItems(response.getData().getInt("total_items"));
        JSONArray friendsObj = response.getData().optJSONArray("users");
        for (int i = 0; friendsObj != null && i < friendsObj.length(); i++) {
        	userList.add(UserUtils.parseUserObject(friendsObj.getJSONObject(i)));
        }
        userList.setPerPage(userList.size());
        return userList;
	}

}
