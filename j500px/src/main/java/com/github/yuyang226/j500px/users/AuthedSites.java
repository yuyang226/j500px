/**
 * 
 */
package com.github.yuyang226.j500px.users;

/**
 * @author yayu
 *
 */
public class AuthedSites {
	private boolean isFacebookAuthed = false;
	private boolean isTwitterAuthed = false;

	/**
	 * 
	 */
	public AuthedSites() {
		super();
	}
	
	/**
	 * @param isFacebookAuthed
	 * @param isTwitterAuthed
	 */
	public AuthedSites(boolean isFacebookAuthed, boolean isTwitterAuthed) {
		super();
		this.isFacebookAuthed = isFacebookAuthed;
		this.isTwitterAuthed = isTwitterAuthed;
	}

	/**
	 * @return the isFacebookAuthed
	 */
	public boolean isFacebookAuthed() {
		return isFacebookAuthed;
	}

	/**
	 * @param isFacebookAuthed the isFacebookAuthed to set
	 */
	public void setFacebookAuthed(boolean isFacebookAuthed) {
		this.isFacebookAuthed = isFacebookAuthed;
	}

	/**
	 * @return the isTwitterAuthed
	 */
	public boolean isTwitterAuthed() {
		return isTwitterAuthed;
	}

	/**
	 * @param isTwitterAuthed the isTwitterAuthed to set
	 */
	public void setTwitterAuthed(boolean isTwitterAuthed) {
		this.isTwitterAuthed = isTwitterAuthed;
	}
	
	

}
