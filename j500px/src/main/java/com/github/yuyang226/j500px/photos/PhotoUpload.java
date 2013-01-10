/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import java.io.Serializable;

/**
 * @author yayu
 *
 */
public class PhotoUpload implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1282139387418935286L;
	
	private Photo photo;
	private String uploadKey;

	/**
	 * 
	 */
	public PhotoUpload() {
		super();
	}

	/**
	 * @return the photo
	 */
	public Photo getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	/**
	 * @return the uploadKey
	 */
	public String getUploadKey() {
		return uploadKey;
	}

	/**
	 * @param uploadKey the uploadKey to set
	 */
	public void setUploadKey(String uploadKey) {
		this.uploadKey = uploadKey;
	}
	
}
