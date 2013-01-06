/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yayu
 *
 */
public class PhotoFilter implements Serializable {

	private static final long serialVersionUID = -2238917030646077154L;
	
	private boolean category;
	private List<String> friendsIds = new ArrayList<String>();
	private String userId;
	private boolean exclude;

	/**
	 * 
	 */
	public PhotoFilter() {
		super();
	}

	/**
	 * @return the category
	 */
	public boolean isCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(boolean category) {
		this.category = category;
	}

	/**
	 * @return the friendsIds
	 */
	public List<String> getFriendsIds() {
		return friendsIds;
	}

	/**
	 * @param friendsIds the friendsIds to set
	 */
	public void setFriendsIds(List<String> friendsIds) {
		this.friendsIds = friendsIds;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the exclude
	 */
	public boolean isExclude() {
		return exclude;
	}

	/**
	 * @param exclude the exclude to set
	 */
	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}
	

}
