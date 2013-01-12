/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import com.github.yuyang226.j500px.SearchResultList;

/**
 * @author yayu
 *
 */
public class CommentList extends SearchResultList<Comment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4037119449117344134L;
	
	private MediaType mediaType;

	/**
	 * 
	 */
	public CommentList() {
		super();
	}
	
	/**
	 * @return the mediaType
	 */
	public MediaType getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

}
