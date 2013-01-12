/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import java.text.ParseException;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.yuyang226.j500px.J500pxConstants;
import com.github.yuyang226.j500px.users.Camera;
import com.github.yuyang226.j500px.users.Lens;
import com.github.yuyang226.j500px.users.UsersInterface;

/**
 * @author yayu
 *
 */
public final class PhotoUtils {

	/**
	 * 
	 */
	private PhotoUtils() {
		super();
	}

	/**
	 * @param photoListObj
	 * @return
	 * @throws JSONException
	 */
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
		photo.setPurchased(photoObj.optBoolean("purchased", false));
	
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
		photo.setStoreDownload(photoObj.optBoolean("store_download", false));
		photo.setStorePrint(photoObj.optBoolean("store_print", false));
		
		if (photoObj.has("tags")) {
			JSONArray tagsArray = photoObj.optJSONArray("tags");
			for (int i = 0; tagsArray != null && i < tagsArray.length(); i++) {
				photo.getTags().add(tagsArray.getString(i));
			}
		}
		return photo;
	}
	
	

}
