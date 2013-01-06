/**
 * 
 */
package com.github.yuyang226.j500px.photos;

/**
 * Numerical size of the image to link to, 1 being the smallest and 4 being the largest.
 * @author yayu
 *
 */
public enum ImageSize {
	UNKOWN(-1), SMALL(1), THUMNAIL(2), LARGE(3), LARGEST(4);

	private int size;

	private ImageSize(int size) {
		this.size = size;
	}
	
	public static ImageSize valueOf(int size) {
		for (ImageSize imageSize: ImageSize.values()) {
			if (imageSize.getSize() == size) {
				return imageSize;
			}
		}
		return UNKOWN;
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
}
