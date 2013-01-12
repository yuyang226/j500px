package com.github.yuyang226.j500px;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author yayu
 *
 */
public final class J500pxConstants {

	//url parts
	
	static final String PHOTO_COMMENT_PART = "%s/photos/%s/comments";
	static final String PHOTO_DETAIL_PART = "%s/photos/%s";
	
	//query string keys
	static final String CONSUMER_KEY = "consumer_key";
	static final String COUNT_KEY = "rpp";
	static final String PAGE_KEY = "page";
	static final String FEATURE_KEY = "feature";
	static final String IMAGE_SIZE_KEY = "image_size";
	static final String USER_ID_KEY = "user_id";
	
	static final int DEF_PAGE_SIZE = 100;
	
	//json keys
	static final String PHOTOS = "photos";
	static final String COMMENTS = "comments";
	static final String PHOTO_TAG = "photo";
	
	public static final String DEFAULT_HOST = "api.500px.com";
	public static final String DEFAULT_HOST_FULL = "https://" + DEFAULT_HOST;
	public static final String PATH_REST = "/v1";
	public static final String URL_REST = DEFAULT_HOST_FULL + PATH_REST;
	
	/*OAuth*/
	public static final String PARAM_OAUTH_CONSUMER_KEY = "oauth_consumer_key";
	public static final String PATH_OAUTH_REQUEST_TOKEN = PATH_REST + "/oauth/request_token";
    public static final String PATH_OAUTH_ACCESS_TOKEN = PATH_REST + "/oauth/access_token";
    public static final String PATH_OAUTH_AUTHORIZE = PATH_REST + "/oauth/authorize";
	public static final String OAUTH_URL_ACCESS_TOKEN = DEFAULT_HOST_FULL + PATH_OAUTH_ACCESS_TOKEN;
	public static final String OAUTH_URL_AUTHORIZE = DEFAULT_HOST_FULL + PATH_OAUTH_AUTHORIZE;
	public static final String OAUTH_URL_REQUEST_TOKEN = DEFAULT_HOST_FULL + PATH_OAUTH_REQUEST_TOKEN;

	public static final String UTF8 = "UTF-8";
	
	public static final String PATH_PHOTOS = PATH_REST + "/photos";
	public static final String PATH_PHOTOS_SEARCH = PATH_PHOTOS + "/search";
	public static final String PATH_PHOTO_COMMENTS = PATH_PHOTOS + "/%d/comments";
	public static final String PATH_PHOTO_FAVORITE = PATH_PHOTOS + "/%d/favorite";
	public static final String PATH_PHOTO_FAVORITES = PATH_PHOTOS + "/%d/favorites";
	public static final String PATH_PHOTO_VOTE = PATH_PHOTOS + "/%d/vote";
	public static final String PATH_PHOTO_VOTES = PATH_PHOTOS + "/%d/votes";
	
	/*Users Constants*/
	public static final String PATH_USERS = PATH_REST + "/users";
	public static final String PATH_USERS_SHOW = PATH_USERS + "/show";
	public static final String PATH_USERS_FRIENDS = PATH_USERS + "/%d/friends";
	public static final ThreadLocal<DateFormat> W3C_DATE_FORMATS = new ThreadLocal<DateFormat>() {
	    protected synchronized DateFormat initialValue() {
	    	return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"){ 
	    		private static final long serialVersionUID = 1L;
	
	    		public Date parse(String source,ParsePosition pos) {    
	    	        return super.parse(source.replaceFirst(":(?=[0-9]{2}$)",""),pos);
	    	    }
	    	};
	    }
	};
	
	private J500pxConstants() {
		super();
	}
}
