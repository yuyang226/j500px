/**
 * 
 */
package com.github.yuyang226.j500px.photos;

/**
 * @author yayu
 *
 */
public enum SearchSort {
		CREATED_AT, //Sort by time of upload, most recent first
		RATING, //Sort by rating, highest rated first
		TIMES_VIEWED, //Sort by view count, most viewed first
		VOTES_COUNT, //Sort by votes count, most voted first
		FAVORITES_COUNT, //Sort by favorites count, most favorited first
		COMMENTS_COUNT, //Sort by comments count, most commented first
		TAKEN_AT; //Sort by the original date of the image extracted from metadata, most recent first (might not be available for all images)
		
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}

}
