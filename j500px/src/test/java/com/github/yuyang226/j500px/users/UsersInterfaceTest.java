/**
 * 
 */
package com.github.yuyang226.j500px.users;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.junit.Test;

import com.github.yuyang226.j500px.J500pxException;
import com.github.yuyang226.j500px.oauth.AbstractJ500pxTest;

/**
 * @author yayu
 *
 */
public class UsersInterfaceTest extends AbstractJ500pxTest{

	/**
	 * Test method for {@link com.github.yuyang226.j500px.users.UsersInterface#getUserProfile()}.
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws J500pxException 
	 */
	@Test
	public void testGetUserProfile() throws J500pxException, IOException, JSONException {
		User user = p.getUsersInterface().getUserProfile();
		assertNotNull(user);
	}
	
}
