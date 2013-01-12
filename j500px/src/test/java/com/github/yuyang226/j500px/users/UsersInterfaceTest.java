/**
 * 
 */
package com.github.yuyang226.j500px.users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

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
	
	@Test(expected=IOException.class)
	public void testGetUserProfileStringNotFound() throws J500pxException, IOException, JSONException {
		User user = p.getUsersInterface().getUserProfile(-1, "abc", "yuyang226@gmail.com");
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserProfileInt() throws J500pxException, IOException, JSONException {
		User user = p.getUsersInterface().getUserProfile(1726909, null, null);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserProfileString() throws J500pxException, IOException, JSONException {
		User user = p.getUsersInterface().getUserProfile(-1, "yuyang226", null);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserProfileStringEmail() throws J500pxException, IOException, JSONException {
		User user = p.getUsersInterface().getUserProfile(-1, null, "yuyang226@gmail.com");
		assertNotNull(user);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetUserProfileStringIllegal() throws J500pxException, IOException, JSONException {
		User user = p.getUsersInterface().getUserProfile(-1, null, null);
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserFriends() throws J500pxException, IOException, JSONException {
		UserList userList = p.getUsersInterface().getUserFriends(1726909, -1, -1);
		assertNotNull(userList);
		assertFalse(userList.isEmpty());
	}
	
	@Test
	public void testGetUserFollowers() throws J500pxException, IOException, JSONException {
		UserList userList = p.getUsersInterface().getUserFollowers(1726909, -1);
		assertNotNull(userList);
		assertFalse(userList.isEmpty());
	}
	
}
