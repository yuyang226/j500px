package com.github.yuyang226.j500px;

import javax.xml.parsers.ParserConfigurationException;

import com.github.yuyang226.j500px.http.REST;
import com.github.yuyang226.j500px.http.Transport;
import com.github.yuyang226.j500px.oauth.OAuthInterface;
import com.github.yuyang226.j500px.photos.PhotosInterface;
import com.github.yuyang226.j500px.users.UsersInterface;

public class J500px {

	private String consumerKey;
	private String consumerSecret;
	
    private Transport transport;

    private OAuthInterface oAuthInterface;
    private UsersInterface usersInterface;
    private PhotosInterface photosInterface;
    
	/**
	 * @param consumerKey
	 * @throws ParserConfigurationException 
	 */
	public J500px(String consumerKey) throws ParserConfigurationException {
		super();
		this.consumerKey = consumerKey;
		this.transport = new REST(J500pxConstants.DEFAULT_HOST);
	}
	
	/**
	 * @param consumerKey
	 * @param consumerSecret
	 * @param transport
	 */
	public J500px(String consumerKey, String consumerSecret, Transport transport) {
		super();
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.transport = transport;
	}

	public J500px(String consumerKey, String consumerSecret) throws ParserConfigurationException {
		this(consumerKey, consumerSecret, new REST(J500pxConstants.DEFAULT_HOST));
	}
	
	public OAuthInterface getOAuthInterface() {
		if (oAuthInterface == null) {
			oAuthInterface = new OAuthInterface(consumerKey, consumerSecret, 
					transport);
		}
		return oAuthInterface;
	}

	/**
	 * @return the usersInterface
	 */
	public UsersInterface getUsersInterface() {
		if (usersInterface == null) {
			usersInterface = new UsersInterface(consumerKey, consumerSecret, 
					transport);
		}
		return usersInterface;
	}
	
	/**
	 * @return the photosInterface
	 */
	public PhotosInterface getPhotosInterface() {
		if (photosInterface == null) {
			photosInterface = new PhotosInterface(consumerKey, consumerSecret, 
					transport);
		}
		return photosInterface;
	}

	/**
	 * @return the consumerKey
	 */
	public String getConsumerKey() {
		return consumerKey;
	}

	/**
	 * @param consumerKey the consumerKey to set
	 */
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	/**
	 * @return the consumerSecret
	 */
	public String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * @param consumerSecret the consumerSecret to set
	 */
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	/**
	 * @return the transport
	 */
	public Transport getTransport() {
		return transport;
	}

	/**
	 * @param transport the transport to set
	 */
	public void setTransport(Transport transport) {
		if (transport == null) {
            throw new IllegalArgumentException("Transport must not be null");
        }
        this.transport = transport;
	}
	

}
