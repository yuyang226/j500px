/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import java.io.IOException;
import java.text.ParseException;
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
import com.github.yuyang226.j500px.users.Camera;
import com.github.yuyang226.j500px.users.Lens;
import com.github.yuyang226.j500px.users.UsersInterface;

/**
 * @author yayu
 * 
 */
public class PhotosInterface {

	private String apiKey;
	private String sharedSecret;
	private Transport transportAPI;

	/**
	 * 
	 */
	public PhotosInterface(String apiKey, String sharedSecret,
			Transport transportAPI) {
		super();
		this.apiKey = apiKey;
		this.sharedSecret = sharedSecret;
		this.transportAPI = transportAPI;
	}

	/**
	 * @param photoId
	 * @param imageSize
	 * @param showComments
	 *            Return comments to the photo in the response. Comments are
	 *            returned in order of creation, 20 entries per page.
	 * @param commentsPage
	 *            Return the specified page from the comments listing. Page
	 *            numbers are 1-based.
	 * @return
	 * @throws J500pxException
	 * @throws JSONException
	 * @throws IOException
	 */
	public Photo getPhotoDetail(String photoId, ImageSize imageSize,
			boolean showComments, int commentsPage) throws J500pxException,
			IOException, JSONException {
		if (photoId == null) {
			throw new IllegalArgumentException("photo id must not be null");
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

		if (imageSize != null) {
			parameters.add(new Parameter("image_size", imageSize.getSize()));
		}

		if (showComments) {
			parameters.add(new Parameter("comments", 1));
		}

		if (commentsPage > 0) {
			parameters.add(new Parameter("comments_page", commentsPage));
		}

		StringBuilder photoPath = new StringBuilder(J500pxConstants.PATH_PHOTOS);
		photoPath.append("/");
		photoPath.append(photoId);
		Response response = signed ? transportAPI.getJSON(sharedSecret,
				photoPath.toString(), parameters) : transportAPI.get(
				photoPath.toString(), parameters);
		if (response.isError()) {
			throw new J500pxException(response.getErrorMessage());
		}
		System.out.println(response.getData());
		Photo photo = parsePhoto(response.getData().getJSONObject("photo"));
		JSONArray commentsObj = response.getData().optJSONArray("comments");
		for (int i = 0; commentsObj != null && i < commentsObj.length(); i++) {
			photo.getComments().add(parseComment(commentsObj.getJSONObject(i)));
		}
		return photo;
	}

	public Comment parseComment(JSONObject commentObj) throws JSONException {
		Comment comment = new Comment();
		comment.setId(commentObj.getInt("id"));
		comment.setComment(commentObj.getString("body"));
		comment.setToWhomUserId(commentObj.optInt("to_whom_user_id", -1));
		if (commentObj.has("user")) {
			comment.setAuthor(UsersInterface.parseUserObject(commentObj
					.getJSONObject("user")));
		}
		comment.setUserId(commentObj.getInt("user_id"));
		if (commentObj.has("created_at")) {
			try {
				comment.setCreatedAt(J500pxConstants.W3C_DATE_FORMATS.get()
						.parse(commentObj.getString("created_at")));
			} catch (ParseException e) {
				// ignore
			}
		}

		return comment;
	}

	public PhotoList getUserPhotos(GlobalFeatures userFeature, String userId,
			String userName, SearchSort sort, PhotoCategory categoryOnly,
			PhotoCategory categoryExclude, ImageSize imageSize, int pageNum,
			int pageSize) throws J500pxException, IOException, JSONException {
		if (userId == null && userName == null) {
			throw new IllegalArgumentException(
					"Either userId or userName must be set");
		}
		if (userFeature != null && userFeature != GlobalFeatures.USER
				&& userFeature != GlobalFeatures.USER_FRIENDS
				&& userFeature != GlobalFeatures.USER_FAVORITES) {
			throw new IllegalArgumentException(
					"Must be one of the user features");
		}
		List<Parameter> extraParams = new ArrayList<Parameter>();
		if (userId != null) {
			extraParams.add(new Parameter("user_id", userId));
		} else {
			extraParams.add(new Parameter("username", userId));
		}

		return getPhotos(userFeature != null ? userFeature
				: GlobalFeatures.USER, sort, categoryOnly, categoryExclude,
				imageSize, extraParams, pageNum, pageSize);
	}

	/**
	 * @param feature
	 * @param sort
	 * @param pageNum
	 *            Return a specific page in the photo stream. Page numbering is
	 *            1-based.
	 * @param pageSize
	 *            The number of results to return. Can not be over 100, default
	 *            20.
	 * @return
	 * @throws J500pxException
	 * @throws JSONException
	 * @throws IOException
	 */
	public PhotoList getPhotos(GlobalFeatures feature, SearchSort sort,
			PhotoCategory categoryOnly, PhotoCategory categoryExclude,
			ImageSize imageSize, List<Parameter> extraParams, int pageNum,
			int pageSize) throws J500pxException, IOException, JSONException {
		List<Parameter> parameters = new ArrayList<Parameter>();
		boolean signed = OAuthUtils.hasSigned();
		parameters.add(new Parameter("feature", feature.toString()));

		if (signed) {
			OAuthUtils.addOAuthToken(parameters);
			parameters.add(new Parameter(
					OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		} else {
			parameters.add(new Parameter(OAuthInterface.PARAM_CONSUMER_KEY,
					apiKey));
		}

		if (categoryOnly != null) {
			parameters
					.add(new Parameter("only", categoryOnly.getCategoryName()));
		}

		if (categoryExclude != null) {
			parameters.add(new Parameter("exclude", categoryExclude
					.getCategoryName()));
		}

		if (extraParams != null) {
			parameters.addAll(extraParams);
		}

		if (sort != null) {
			parameters.add(new Parameter("sort", sort.toString()));
		}

		if (imageSize != null) {
			parameters.add(new Parameter("image_size", imageSize.getSize()));
		}

		if (pageNum > 0) {
			parameters.add(new Parameter("page", pageNum));
		}

		if (pageSize > 0) {
			parameters.add(new Parameter("rpp", pageSize));
		}

		Response response = signed ? transportAPI.getJSON(sharedSecret,
				J500pxConstants.PATH_PHOTOS, parameters) : transportAPI.get(
				J500pxConstants.PATH_PHOTOS, parameters);
		if (response.isError()) {
			throw new J500pxException(response.getErrorMessage());
		}
		return parsePhotoList(response.getData());
	}
	
	/**
	 * @param term  A keyword to search for
	 * @param tag A complete tag string to search for
	 * @param pageNum Return a specific page. Page numbering is 1-based.
	 * @param pageSize The number of results to return. Can not be over 100, default 20.
	 * @return
	 * @throws J500pxException
	 * @throws IOException
	 * @throws JSONException
	 * @see http://developer.500px.com/docs/photos-search
	 */
	public PhotoList searchPhotos(String term, String tag,
			int pageNum, int pageSize) throws J500pxException, IOException, JSONException {
		if (term == null && tag == null) {
			throw new IllegalArgumentException("Both term and tag are null");
		}
		List<Parameter> parameters = new ArrayList<Parameter>();
		boolean signed = OAuthUtils.hasSigned();

		if (signed) {
			parameters.add(new Parameter(
					OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
			OAuthUtils.addOAuthToken(parameters);
		} else {
			parameters.add(new Parameter(OAuthInterface.PARAM_CONSUMER_KEY,
					apiKey));
		}

		if (term != null) {
			parameters
					.add(new Parameter("term", term));
		}

		if (tag != null) {
			parameters.add(new Parameter("tag", tag));
		}

		if (pageNum > 0) {
			parameters.add(new Parameter("page", pageNum));
		}

		if (pageSize > 0) {
			parameters.add(new Parameter("rpp", pageSize));
		}

		Response response = signed ? transportAPI.getJSON(sharedSecret,
				J500pxConstants.PATH_PHOTOS_SEARCH, parameters) : transportAPI.get(
				J500pxConstants.PATH_PHOTOS_SEARCH, parameters);
		if (response.isError()) {
			throw new J500pxException(response.getErrorMessage());
		}
		return parsePhotoList(response.getData());
	}
	
	/**
	 * Create a new photo on behalf of the user, and receive an upload token in exchange.
	 * 
	 * OAuth required.
	 * 
	 * @param name — Title of the photo
	 * @param description — Description for the photo
	 * @param category — The category of the photo
	 * @param exif - Technical details of the photo
	 * @param latitude — Latitude, in decimal format
	 * @param longitude — Longitude, in decimal format
	 * @param privacy  — Whether to hide the photo from the user profile on the website. Otherwise, the photo is only available for use in Collections/Portfolio.
	 * @throws J500pxException
	 * @throws IOException
	 * @throws JSONException
	 */
	public PhotoUpload createNewPhoto(String name, String description, PhotoCategory category, PhotoExif exif, 
			Double latitude, Double longitude, Boolean privacy) 
			throws J500pxException, IOException, JSONException{
		if (name == null) {
			throw new IllegalArgumentException("Name must not be null");
		}
		boolean signed = OAuthUtils.hasSigned();
		if (!signed) {
			throw new J500pxException("must sign in first.");
		}
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new Parameter(
				OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		OAuthUtils.addOAuthToken(parameters);
		parameters.add(new Parameter("name", name));
		if (description != null) {
			parameters.add(new Parameter("description", description));
		}
		parameters.add(new Parameter("category", 
				category != null ? category.getCategoryId() : PhotoCategory.Uncategorized));
		if (exif != null) {
			if (exif.getShutterSpeed() != null) {
				parameters.add(new Parameter("shutter_speed", exif.getShutterSpeed()));
			}
			if (exif.getFocalLength() != null) {
				parameters.add(new Parameter("focal_length", exif.getFocalLength()));
			}
			if (exif.getAperture() != null) {
				parameters.add(new Parameter("aperture", exif.getAperture()));
			}
			if (exif.getIso() != null) {
				parameters.add(new Parameter("iso", exif.getIso()));
			}
			if (exif.getCamera() != null) {
				parameters.add(new Parameter("camera", exif.getCamera()));
			}
			if (exif.getLens() != null) {
				parameters.add(new Parameter("lens", exif.getLens()));
			}
		}
		
		if (latitude != null) {
			parameters.add(new Parameter("latitude", latitude));
		}
		
		if (longitude != null) {
			parameters.add(new Parameter("longitude", longitude));
		}
		
		Response response = transportAPI.postJSON(sharedSecret,
				J500pxConstants.PATH_PHOTOS, parameters);
		if (response.isError()) {
			throw new J500pxException(response.getErrorMessage());
		}
		
		PhotoUpload upload = new PhotoUpload();
		upload.setUploadKey(response.getData().getString("upload_key"));
		upload.setPhoto(parsePhoto(response.getData().getJSONObject("photo")));
		return upload;
	}
	
	/**
	 * @param photoId
	 * @param vote false for 'dislike' or true for 'like'.
	 * @throws J500pxException
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public Photo votePhoto(int photoId, boolean vote) throws J500pxException, IOException, JSONException {
		boolean signed = OAuthUtils.hasSigned();
		if (!signed) {
			throw new J500pxException("must sign in first.");
		}

		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new Parameter(
				OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		OAuthUtils.addOAuthToken(parameters);
		parameters.add(new Parameter("vote", vote ? "1" : "0"));
		
		final String path = String
				.format(Locale.US, J500pxConstants.PATH_PHOTO_VOTE, photoId);
		Response response = transportAPI.postJSON(sharedSecret, path,
				parameters);
		if (response.isError()) {
			throw new J500pxException(response.getErrorMessage());
		}
		return parsePhoto(response.getData().getJSONObject("photo"));
	}
	
	public void likePhoto(String photoId, boolean fav) throws J500pxException {
		boolean signed = OAuthUtils.hasSigned();
		if (!signed) {
			throw new J500pxException("must sign in first.");
		}

		String path = String
				.format(Locale.US, J500pxConstants.PATH_PHOTO_FAV, photoId);
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("id", photoId));
		OAuthUtils.addOAuthToken(params);
		try {
			Response response = null;
			if( fav ) {
				transportAPI.postJSON(sharedSecret, path,
					params);
			} else {
				//TODO delete not ready yet.
			}
			if( response == null ) {
				throw new J500pxException("no response"); //should  not happen.
			}
			if ( response.isError()) {
				System.err.println(response.getErrorMessage());
				throw new J500pxException(response.getErrorMessage());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new J500pxException(e);
		}
	}

	/**
	 * Comments a given photo identified by <code>photoId</code>. This needs
	 * auth.
	 * 
	 * @param photoId
	 * @param comment
	 * @throws J500pxException
	 */
	public void commentPhoto(String photoId, String comment)
			throws J500pxException {
		boolean signed = OAuthUtils.hasSigned();
		if (!signed) {
			throw new J500pxException("must sign in first.");
		}

		String path = String
				.format(J500pxConstants.PATH_PHOTO_COMMENT, photoId);
		List<Parameter> params = new ArrayList<Parameter>();
		params.add( new Parameter("id", photoId));
		params.add(new Parameter("body", comment));
		OAuthUtils.addOAuthToken(params);
		try {
			Response response = transportAPI.postJSON(sharedSecret, path,
					params);
			if (response.isError()) {
				System.err.println(response.getErrorMessage());
				throw new J500pxException(response.getErrorMessage());
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new J500pxException(e);
		}

	}

	public static PhotoList parsePhotoList(JSONObject photoListObj)
			throws JSONException {
		PhotoList photoList = new PhotoList();
		String feature = photoListObj.optString("feature", null);
		if (feature != null) {
			photoList.setFeature(GlobalFeatures.valueOf(feature
					.toUpperCase(Locale.US)));
		}
		photoList.setTotalItems(photoListObj.getInt("total_items"));
		photoList.setTotalPages(photoListObj.getInt("total_pages"));
		photoList.setCurrentPage(photoListObj.getInt("current_page"));

		JSONArray photosArrray = photoListObj.optJSONArray("photos");
		for (int i = 0; photosArrray != null && i < photosArrray.length(); i++) {
			JSONObject el = photosArrray.getJSONObject(i);
			photoList.add(parsePhoto(el));
		}
		photoList.setPerPage(photoList.size());

		if (photoListObj.has("filters")) {
			JSONObject filtersObj = photoListObj.getJSONObject("filters");
			PhotoFilter filters = new PhotoFilter();
			filters.setUserId(filtersObj.optString("user_id", null));
			filters.setCategory(filtersObj.optBoolean("category", false));
			filters.setExclude(filtersObj.optBoolean("exclude", false));
			if (filtersObj.has("friends_ids")) {
				JSONArray friendsObj = filtersObj.getJSONArray("friends_ids");
				for (int i = 0; i < friendsObj.length(); i++) {
					filters.getFriendsIds().add(friendsObj.getString(i));
				}
			}
			photoList.setFilters(filters);
		}
		return photoList;
	}

	/**
	 * 
	 * @param photoObj
	 * @return
	 * @throws JSONException
	 */
	public static Photo parsePhoto(JSONObject photoObj) throws JSONException {
		Photo photo = new Photo();
		photo.setId(photoObj.getInt("id"));
		photo.setName(photoObj.getString("name"));
		photo.setDescription(photoObj.optString("description", null));
		photo.setViewsCount(photoObj.optInt("times_viewed", -1));
		photo.setRating(photoObj.optDouble("rating", 0.0));
		photo.setHighestRating(photoObj.optDouble("highest_rating", 0.0));
		if (photoObj.has("highest_rating_date")
				&& photoObj.getString("highest_rating_date") != "null") {
			try {
				photo.setHighestRatingDate(J500pxConstants.W3C_DATE_FORMATS
						.get().parse(photoObj.getString("highest_rating_date")));
			} catch (ParseException e) {
				// ignore
			}
		}
		photo.setStatus(photoObj.optInt("status", -1));

		if (photoObj.has("created_at")
				&& photoObj.getString("created_at") != "null") {
			try {
				photo.setCreatedAt(J500pxConstants.W3C_DATE_FORMATS.get()
						.parse(photoObj.getString("created_at")));
			} catch (ParseException e) {
				// ignore
			}
		}

		photo.setCategory(PhotoCategory.valueOf(photoObj.optInt("category", 0)));
		photo.setPrivacy(photoObj.optBoolean("privacy", false));
		photo.setVotesCount(photoObj.optInt("votes_count", -1));
		photo.setFavouritesCount(photoObj.optInt("favorites_count", -1));
		photo.setCommentsCount(photoObj.optInt("comments_count", -1));

		// locations
		photo.setLocation(photoObj.optString("location", null));
		if (photoObj.has("latitude")
				&& photoObj.getString("latitude") != "null") {
			photo.setLatitude(photoObj.getDouble("latitude"));
		}
		if (photoObj.has("longitude")
				&& photoObj.getString("longitude") != "null") {
			photo.setLongitude(photoObj.getDouble("longitude"));
		}

		if (photoObj.has("user")) {
			photo.setAuthor(UsersInterface.parseUserObject(photoObj
					.getJSONObject("user")));
		}
		photo.setNsfw(photoObj.optBoolean("nsfw", false));
		photo.setWidth(photoObj.optInt("width", -1));
		photo.setHeight(photoObj.optInt("height", -1));

		photo.setImageUrl(photoObj.optString("image_url", null));
		photo.setVoted(photoObj.optBoolean("voted", false));
		photo.setFavorited(photoObj.optBoolean("favorited", false));

		JSONArray imagesArray = photoObj.optJSONArray("images");
		for (int i = 0; imagesArray != null && i < imagesArray.length(); i++) {
			JSONObject imObj = imagesArray.getJSONObject(i);
			ImageUrl imgUrl = new ImageUrl();
			imgUrl.setSize(ImageSize.valueOf(imObj.optInt("size", -1)));
			imgUrl.setImageUrl(imObj.optString("url", null));
			photo.getImageUrls().add(imgUrl);
		}

		PhotoExif exif = new PhotoExif();
		if (photoObj.has("camera")) {
			exif.setCamera(new Camera(photoObj.getString("camera")));
		}
		if (photoObj.has("lens")) {
			exif.setLens(new Lens(photoObj.getString("lens")));
		}
		exif.setFocalLength(photoObj.optString("focal_length", null));
		exif.setIso(photoObj.optString("iso", null));
		exif.setShutterSpeed(photoObj.optString("shutter_speed", null));
		exif.setAperture(photoObj.optString("aperture", null));
		if (photoObj.has("taken_at")
				&& photoObj.getString("taken_at") != "null") {
			try {
				exif.setTakenAt(J500pxConstants.W3C_DATE_FORMATS.get().parse(
						photoObj.getString("taken_at")));
			} catch (ParseException e) {
				// ignore
			}
		}
		photo.setExif(exif);

		// sales
		photo.setForSale(photoObj.optBoolean("for_sale", false));
		photo.setSalesCount(photoObj.optInt("sales_count", -1));
		if (photoObj.has("for_sale_date")
				&& photoObj.getString("for_sale_date") != "null") {
			try {
				photo.setForSaleDate(J500pxConstants.W3C_DATE_FORMATS.get()
						.parse(photoObj.getString("for_sale_date")));
			} catch (ParseException e) {
				// ignore
			}
		}
		return photo;
	}

}
