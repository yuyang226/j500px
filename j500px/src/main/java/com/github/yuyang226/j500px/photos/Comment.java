/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import java.io.Serializable;
import java.util.Date;

import com.github.yuyang226.j500px.users.User;

/**
 * @author yayu
 *
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 3075068546812683985L;
	
	private int id;
	private int userId;
	private String comment;
	private Date createdAt;
	/**
	 *  Is always the ID of the author of the photo.
	 */
	private int toWhomUserId;
	private User author;
	/**
	 * 
	 */
	public Comment() {
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
	 * @return the toWhomUserId
	 */
	public int getToWhomUserId() {
		return toWhomUserId;
	}
	/**
	 * @param toWhomUserId the toWhomUserId to set
	 */
	public void setToWhomUserId(int toWhomUserId) {
		this.toWhomUserId = toWhomUserId;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", comment="
				+ comment + "]";
	}
	
}
