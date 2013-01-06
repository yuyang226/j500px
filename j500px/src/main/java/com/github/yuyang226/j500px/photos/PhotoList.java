/**
 * 
 */
package com.github.yuyang226.j500px.photos;

import com.github.yuyang226.j500px.SearchResultList;

/**
 * @author yayu
 *
 */
public class PhotoList extends SearchResultList<Photo> {

	private static final long serialVersionUID = -6657430420628646275L;
	
	private GlobalFeatures feature;

	/**
	 * 
	 */
	public PhotoList() {
		super();
	}

	/**
	 * @return the feature
	 */
	public GlobalFeatures getFeature() {
		return feature;
	}

	/**
	 * @param feature the feature to set
	 */
	public void setFeature(GlobalFeatures feature) {
		this.feature = feature;
	}
	

}
