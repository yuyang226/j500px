package com.github.yuyang226.j500px.model;

public class PxExif {

	public static final String CAMERA = "camera";
	public static final String LENS = "lens";
	public static final String APERTURE = "aperture";
	public static final String FOCAL_LENGTH = "focal_length";
	public static final String ISO = "iso";
	public static final String SHUTTER_SPEED = "shutter_speed";
	public static final String TAKEN_AT = "taken_at";

	public String camera;
	public String lens;
	public String aperture;
	public String focalLength;
	public String iso;
	public String shutterSpeed;
	public String takenAt;

	/**
	 * Returns <code>true</code> if there is exif information,
	 * <code>false</code> if there is no info.
	 * 
	 * @return
	 */
	public boolean isValid() {
		boolean valid = false;
		valid = valid | camera != null;
		valid = valid | lens != null;
		valid = valid | aperture != null;
		valid = valid | focalLength != null;
		valid = valid | iso != null;
		valid = valid | shutterSpeed != null;
		valid = valid | takenAt != null;
		return valid;
	}
}
