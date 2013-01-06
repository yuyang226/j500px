/**
 * 
 */
package com.github.yuyang226.j500px.photos;

/**
 * @author yayu
 *
 */
public enum GlobalFeatures {
	POPULAR, UPCOMING,  EDITORS, FRESH_TODAY, USER, FRESH_YESTERDAY, FRESH_WEEK;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
