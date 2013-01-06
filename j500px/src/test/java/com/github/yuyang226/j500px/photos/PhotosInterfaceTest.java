/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import com.github.yuyang226.j500px.J500pxException;
import com.github.yuyang226.j500px.oauth.AbstractJ500pxTest;
import com.github.yuyang226.j500px.oauth.RequestContext;

/**
 * @author yayu
 *
 */
public class PhotosInterfaceTest extends AbstractJ500pxTest {

	/**
	 * Test method for {@link com.github.yuyang226.j500px.photos.PhotosInterface#getPhotos(com.github.yuyang226.j500px.photos.GlobalFeatures, com.github.yuyang226.j500px.photos.SearchSort, int, int)}.
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws J500pxException 
	 */
	@Test
	public void testGetPhotos() throws J500pxException, IOException, JSONException {
		PhotoList photoList = p.getPhotosInterface().getPhotos(GlobalFeatures.EDITORS, null, PhotoCategory.BlackAndWhite, null, ImageSize.THUMNAIL,
				null, -1, -1);
		assertNotNull(photoList);
		assertFalse(photoList.isEmpty());
	}
	
	@Test
	public void testGetPhotosNoAuth() throws J500pxException, IOException, JSONException {
		RequestContext.getRequestContext().setOAuth(null);
		PhotoList photoList = p.getPhotosInterface().getPhotos(GlobalFeatures.EDITORS, null, null, null, null, null, -1, -1);
		assertNotNull(photoList);
		assertFalse(photoList.isEmpty());
	}
	
	@Test
	public void testGetUserPhotos() throws J500pxException, IOException, JSONException {
		PhotoList photoList = p.getPhotosInterface().getUserPhotos(null, "753809", null, null, null, null, ImageSize.LARGEST, -1, -1);
		assertNotNull(photoList);
		assertFalse(photoList.isEmpty());
	}
	
	@Test
	public void testGetUserFavouritesPhotos() throws J500pxException, IOException, JSONException {
		PhotoList photoList = p.getPhotosInterface().getUserPhotos(GlobalFeatures.USER_FAVORITES, "753809", null, null, null, null, ImageSize.LARGEST, -1, -1);
		assertNotNull(photoList);
		assertFalse(photoList.isEmpty());
	}
	
	@Test
	public void testGetUserFriendsPhotos() throws J500pxException, IOException, JSONException {
		PhotoList photoList = p.getPhotosInterface().getUserPhotos(GlobalFeatures.USER_FRIENDS, "753809", null, 
				SearchSort.RATING, null, null, ImageSize.LARGEST, -1, -1);
		assertNotNull(photoList);
		assertFalse(photoList.isEmpty());
	}
	
	@Test
	public void testGetPhotoDetail() throws J500pxException, IOException, JSONException {
		Photo photo = p.getPhotosInterface().getPhotoDetail("22161277", null, 5, -1);
		assertNotNull(photo);
	}

}
