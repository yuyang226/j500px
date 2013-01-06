package com.github.yuyang226.j500px.users;

/**
 * @author yayu
 *
 */
public enum UserSex {
	MALE, FEMALE, REFUSED;
	
	public static UserSex valueOf(int value) {
		switch(value) {
		case 1:
			return MALE;
		case 2: 
			return FEMALE;
		case 0: 
		default:
				return REFUSED;
		}
	}
}