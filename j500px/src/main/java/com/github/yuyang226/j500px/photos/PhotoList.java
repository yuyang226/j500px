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
	private PhotoFilter filters;

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

	/**
	 * @return the filter
	 */
	public PhotoFilter getFilters() {
		return filters;
	}

	/**
	 * @param filter the filter to set
	 */
	public void setFilters(PhotoFilter filter) {
		this.filters = filter;
	}
	
	

}
