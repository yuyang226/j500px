package com.github.yuyang226.j500px.model;

public class User {

	public static final String ID = "id";
	public static final String USER_NAME = "username";
	public static final String BUDDY_ICON_URL = "userpic_url";
	
	private String id;
	private String userName;
	private String buddyIconUrl;
	/**
	 * 
	 */
	public User() {
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the buddyIconUrl
	 */
	public String getBuddyIconUrl() {
		return buddyIconUrl;
	}
	/**
	 * @param buddyIconUrl the buddyIconUrl to set
	 */
	public void setBuddyIconUrl(String buddyIconUrl) {
		this.buddyIconUrl = buddyIconUrl;
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
		User other = (User) obj;
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
		return "User [id=" + id + ", userName=" + userName + "]";
	}
	
}
