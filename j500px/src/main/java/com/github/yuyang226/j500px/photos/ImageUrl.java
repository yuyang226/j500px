/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import java.io.Serializable;

/**
 * @author yayu
 *
 */
public class ImageUrl implements Serializable {

	private static final long serialVersionUID = -5418586087392945483L;
	
	private ImageSize size;
	private String imageUrl;

	/**
	 * 
	 */
	public ImageUrl() {
		super();
	}

	/**
	 * @return the size
	 */
	public ImageSize getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(ImageSize size) {
		this.size = size;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	

}
