package com.github.yuyang226.j500px;

import javax.xml.parsers.ParserConfigurationException;

import com.github.yuyang226.j500px.http.REST;
import com.github.yuyang226.j500px.http.Transport;
import com.github.yuyang226.j500px.oauth.OAuthInterface;

public class J500px {

	private String consumerKey;
	private String consumerSecret;
	
    private Transport transport;

    private OAuthInterface oAuthInterface;
    
	/**
	 * @param consumerKey
	 */
	public J500px(String consumerKey) {
		super();
		this.consumerKey = consumerKey;
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
