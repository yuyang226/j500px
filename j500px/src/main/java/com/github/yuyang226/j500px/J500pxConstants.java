package com.github.yuyang226.j500px;

/**
 * @author yayu
 *
 */
public final class J500pxConstants {

	//url parts
	static final String PHOTOS_URL_PART = "photos";
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
	
	public static final String PATH_REST = ""; //FIXME
	public static final String DEFAULT_HOST = "api.500px.com";
	public static final String DEFAULT_HOST_FULL = "https://" + DEFAULT_HOST;
	public static final String URL_REST = DEFAULT_HOST_FULL + PATH_REST;
	
	
	public static final String PATH_OAUTH_REQUEST_TOKEN = "/v1/oauth/request_token";
    public static final String PATH_OAUTH_ACCESS_TOKEN = "/v1/oauth/access_token";
    public static final String PATH_OAUTH_AUTHORIZE = "/v1/oauth/authorize";
	public static final String OAUTH_URL_ACCESS_TOKEN = DEFAULT_HOST_FULL + PATH_OAUTH_ACCESS_TOKEN;
	public static final String OAUTH_URL_AUTHORIZE = DEFAULT_HOST_FULL + PATH_OAUTH_AUTHORIZE;
	public static final String OAUTH_URL_REQUEST_TOKEN = DEFAULT_HOST_FULL + PATH_OAUTH_REQUEST_TOKEN;

	public static final String UTF8 = "UTF-8";
	
	/*Photos Constants*/
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String IMG_URL = "image_url";
	public static final String IMAGEs = "images";
	public static final String URL = "url";
	public static final String USER = "user";
	public static final String COMMENTS_COUNT = "comments_count";
	public static final String FAV_COUNT = "favorites_count";
	public static final String TIMES_VIEWED = "times_viewed";
	public static final String LAT = "latitude";
	public static final String LNG = "longitude";
	
	private J500pxConstants() {
		super();
	}
}
