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
	
	public PhotoList getUserPhotos(String userId, String userName, SearchSort sort, ImageSize imageSize, 
			int pageNum, int pageSize) throws J500pxException, IOException, JSONException {
		if (userId == null && userName == null) {
			throw new IllegalArgumentException("Either userId or userName must be set");
		}
		List<Parameter> extraParams = new ArrayList<Parameter>();
		if (userId != null) {
			extraParams.add(new Parameter("user_id", userId));
		} else {
			extraParams.add(new Parameter("username", userId));
		}
		return getPhotos(GlobalFeatures.USER, sort, imageSize, extraParams, pageNum, pageSize);
	}


	/**
	 * @param feature
	 * @param sort
	 * @param pageNum Return a specific page in the photo stream. Page numbering is 1-based.
	 * @param pageSize The number of results to return. Can not be over 100, default 20.
	 * @return
	 * @throws J500pxException
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public PhotoList getPhotos(GlobalFeatures feature, SearchSort sort, ImageSize imageSize, List<Parameter> extraParams, int pageNum, int pageSize)
			throws J500pxException, IOException, JSONException {
		List<Parameter> parameters = new ArrayList<Parameter>();
		boolean signed = OAuthUtils.hasSigned();
		parameters.add(new Parameter("feature", feature.toString()));
		
		if (signed) {
			OAuthUtils.addOAuthToken(parameters);
			parameters.add(new Parameter(OAuthInterface.PARAM_OAUTH_CONSUMER_KEY, apiKey));
		} else {
			parameters.add(new Parameter(OAuthInterface.PARAM_CONSUMER_KEY, apiKey));
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

		Response response = signed ? transportAPI.getJSON(sharedSecret, J500pxConstants.PATH_PHOTOS, parameters) : 
			transportAPI.get(J500pxConstants.PATH_PHOTOS, parameters);
		if (response.isError()) {
			throw new J500pxException(response.getErrorMessage());
		}
		return parsePhotoList(response.getData());
	}
	
	public static PhotoList parsePhotoList(JSONObject photoListObj) throws JSONException {
		PhotoList photoList = new PhotoList();
		String feature = photoListObj.getString("feature");
		photoList.setFeature(GlobalFeatures.valueOf(feature.toUpperCase(Locale.US)));
		photoList.setTotalItems(photoListObj.getInt("total_items"));
		photoList.setTotalPages(photoListObj.getInt("total_pages"));
		photoList.setCurrentPage(photoListObj.getInt("current_page"));
		
		JSONArray photosArrray = photoListObj.optJSONArray("photos");
        for (int i = 0; photosArrray != null && i < photosArrray.length(); i++) {
           JSONObject el = photosArrray.getJSONObject(i);
           photoList.add(parsePhoto(el));
        }
        photoList.setPerPage(photoList.size());
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
		
		if (photoObj.has("created_at")) {
			try {
				photo.setCreatedAt(J500pxConstants.W3C_DATE_FORMATS.get().parse(
						photoObj.getString("created_at")));
			} catch (ParseException e) {
				//ignore
			}
		}
		photo.setCategory(PhotoCategory.valueOf(photoObj.optInt("category", 0)));
		photo.setPrivacy(photoObj.optBoolean("privacy", false));
		photo.setVotesCount(photoObj.optInt("votes_count", -1));
		photo.setFavouritesCount(photoObj.optInt("favorites_count", -1));
		photo.setCommentsCount(photoObj.optInt("comments_count", -1));
		
		if (photoObj.has("user")) {
			photo.setAuthor(UsersInterface.parseUserObject(photoObj.getJSONObject("user")));
		}
		photo.setNsfw(photoObj.optBoolean("nsfw", false));
		photo.setWidth(photoObj.optInt("width", -1));
		photo.setHeight(photoObj.optInt("height", -1));
		
		photo.setImageUrl(photoObj.optString("image_url", null));
		
		JSONArray imagesArray = photoObj.optJSONArray("images");
        for (int i = 0; imagesArray != null && i < imagesArray.length(); i++) {
           JSONObject imObj = imagesArray.getJSONObject(i);
           ImageUrl imgUrl = new ImageUrl();
           imgUrl.setSize(ImageSize.valueOf(imObj.optInt("size", -1)));
           imgUrl.setImageUrl(imObj.optString("url", null));
           photo.getImageUrls().add(imgUrl);
        }
		
		return photo;
	}
	
}
