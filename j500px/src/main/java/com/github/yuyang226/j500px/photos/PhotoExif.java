package com.github.yuyang226.j500px.photos;

import java.io.Serializable;
import java.util.Date;

import com.github.yuyang226.j500px.users.Camera;
import com.github.yuyang226.j500px.users.Lens;

public class PhotoExif implements Serializable {

	private static final long serialVersionUID = 6337989534304708180L;
	
	private Camera camera;
	private Lens lens;
	private String aperture;
	private String focalLength;
	private String iso;
	private String shutterSpeed;
	private Date takenAt;
	/**
	 * 
	 */
	public PhotoExif() {
		super();
	}
	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}
	/**
	 * @param camera the camera to set
	 */
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	/**
	 * @return the lens
	 */
	public Lens getLens() {
		return lens;
	}
	/**
	 * @param lens the lens to set
	 */
	public void setLens(Lens lens) {
		this.lens = lens;
	}
	/**
	 * @return the aperture
	 */
	public String getAperture() {
		return aperture;
	}
	/**
	 * @param aperture the aperture to set
	 */
	public void setAperture(String aperture) {
		this.aperture = aperture;
	}
	/**
	 * @return the focalLength
	 */
	public String getFocalLength() {
		return focalLength;
	}
	/**
	 * @param focalLength the focalLength to set
	 */
	public void setFocalLength(String focalLength) {
		this.focalLength = focalLength;
	}
	/**
	 * @return the iso
	 */
	public String getIso() {
		return iso;
	}
	/**
	 * @param iso the iso to set
	 */
	public void setIso(String iso) {
		this.iso = iso;
	}
	/**
	 * @return the shutterSpeed
	 */
	public String getShutterSpeed() {
		return shutterSpeed;
	}
	/**
	 * @param shutterSpeed the shutterSpeed to set
	 */
	public void setShutterSpeed(String shutterSpeed) {
		this.shutterSpeed = shutterSpeed;
	}
	/**
	 * @return the takenAt
	 */
	public Date getTakenAt() {
		return takenAt;
	}
	/**
	 * @param takenAt the takenAt to set
	 */
	public void setTakenAt(Date takenAt) {
		this.takenAt = takenAt;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PhotoExif [camera=" + camera + ", lens=" + lens + ", takenAt="
				+ takenAt + "]";
	}

	
}
