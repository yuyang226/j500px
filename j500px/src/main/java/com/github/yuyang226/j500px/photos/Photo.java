package com.github.yuyang226.j500px.photos;

import com.github.yuyang226.j500px.model.PxExif;
import com.github.yuyang226.j500px.model.User;

/**
 * @author yayu
 *
 */
public class Photo {

	private String id;
	private String name;
	private String imageUrl;
	private String largeImageUrl;
	private User author;
	private PxExif exif;
	private String latitude;
	private String longitude;
	
	private int favorites;
	private int comments;
	private int views;
	/**
	 * 
	 */
	public Photo() {
		super();
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the largeImageUrl
	 */
	public String getLargeImageUrl() {
		return largeImageUrl;
	}
	/**
	 * @param largeImageUrl the largeImageUrl to set
	 */
	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}
	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}
	/**
	 * @return the exif
	 */
	public PxExif getExif() {
		return exif;
	}
	/**
	 * @param exif the exif to set
	 */
	public void setExif(PxExif exif) {
		this.exif = exif;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the favorites
	 */
	public int getFavorites() {
		return favorites;
	}
	/**
	 * @param favorites the favorites to set
	 */
	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}
	/**
	 * @return the comments
	 */
	public int getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(int comments) {
		this.comments = comments;
	}
	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}
	/**
	 * @param views the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Photo [id=" + id + ", name=" + name + "]";
	}
	

}
