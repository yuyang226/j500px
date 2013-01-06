package com.github.yuyang226.j500px.photos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.yuyang226.j500px.users.User;

/**
 * @author yayu
 * @see http://developer.500px.com/docs/formats#photo_formats
 *
 */
public class Photo implements Serializable {

	private static final long serialVersionUID = 548829495518564119L;
	
	private int id;
	private String name;
	private String description;
	private PhotoCategory category;
	private String imageUrl;
	private String largeImageUrl;
	private User author;
	private PhotoExif exif;
	private String location;
	private double latitude;
	private double longitude;
	
	private int votesCount;
	private int favouritesCount;
	private int commentsCount;
	private int viewsCount;
	private double rating;
	private double highestRating;
	private Date highestRatingDate;
	
	private boolean privacy;
	private boolean nsfw;
	private Date createdAt;
	
	private int width;
	private int height;
	
	private List<ImageUrl> imageUrls = new ArrayList<ImageUrl>();
	private List<Comment> comments = new ArrayList<Comment>();
	
	private boolean forSale;
	private int salesCount;
	private Date forSaleDate;
	
	
	/**
	 * Status of the photo in the system, integer. An active photo always has the status of 1.
	 */
	private int status;
	
	/*The following data will also be returned for an authenticated request:*/
	/**
	 * Whether the current user has voted for this photo
	 */
	private boolean voted;
	/**
	 * Whether the current user currently has this photo in favorites
	 */
	private boolean favorited;
	
	/**
	 * 
	 */
	public Photo() {
		super();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the category
	 */
	public PhotoCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(PhotoCategory category) {
		this.category = category;
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
	public PhotoExif getExif() {
		return exif;
	}

	/**
	 * @param exif the exif to set
	 */
	public void setExif(PhotoExif exif) {
		this.exif = exif;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the votesCount
	 */
	public int getVotesCount() {
		return votesCount;
	}

	/**
	 * @param votesCount the votesCount to set
	 */
	public void setVotesCount(int votesCount) {
		this.votesCount = votesCount;
	}

	/**
	 * @return the favouritesCount
	 */
	public int getFavouritesCount() {
		return favouritesCount;
	}

	/**
	 * @param favouritesCount the favouritesCount to set
	 */
	public void setFavouritesCount(int favouritesCount) {
		this.favouritesCount = favouritesCount;
	}

	/**
	 * @return the commentsCount
	 */
	public int getCommentsCount() {
		return commentsCount;
	}

	/**
	 * @param commentsCount the commentsCount to set
	 */
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	/**
	 * @return the viewsCount
	 */
	public int getViewsCount() {
		return viewsCount;
	}

	/**
	 * @param viewsCount the viewsCount to set
	 */
	public void setViewsCount(int viewsCount) {
		this.viewsCount = viewsCount;
	}

	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * @return the highestRating
	 */
	public double getHighestRating() {
		return highestRating;
	}

	/**
	 * @param highestRating the highestRating to set
	 */
	public void setHighestRating(double highestRating) {
		this.highestRating = highestRating;
	}

	/**
	 * @return the highestRatingDate
	 */
	public Date getHighestRatingDate() {
		return highestRatingDate;
	}

	/**
	 * @param highestRatingDate the highestRatingDate to set
	 */
	public void setHighestRatingDate(Date highestRatingDate) {
		this.highestRatingDate = highestRatingDate;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the voted
	 */
	public boolean isVoted() {
		return voted;
	}

	/**
	 * @param voted the voted to set
	 */
	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	/**
	 * @return the favorited
	 */
	public boolean isFavorited() {
		return favorited;
	}

	/**
	 * @param favorited the favorited to set
	 */
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}
	
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * @return the privacy
	 */
	public boolean isPrivacy() {
		return privacy;
	}

	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}
	
	/**
	 * @return the nsfw
	 */
	public boolean isNsfw() {
		return nsfw;
	}

	/**
	 * @param nsfw the nsfw to set
	 */
	public void setNsfw(boolean nsfw) {
		this.nsfw = nsfw;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return the imageUrls
	 */
	public List<ImageUrl> getImageUrls() {
		return imageUrls;
	}

	/**
	 * @param imageUrls the imageUrls to set
	 */
	public void setImageUrls(List<ImageUrl> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the forSale
	 */
	public boolean isForSale() {
		return forSale;
	}

	/**
	 * @param forSale the forSale to set
	 */
	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	/**
	 * @return the salesCount
	 */
	public int getSalesCount() {
		return salesCount;
	}

	/**
	 * @param salesCount the salesCount to set
	 */
	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	/**
	 * @return the forSaleDate
	 */
	public Date getForSaleDate() {
		return forSaleDate;
	}

	/**
	 * @param forSaleDate the forSaleDate to set
	 */
	public void setForSaleDate(Date forSaleDate) {
		this.forSaleDate = forSaleDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
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
