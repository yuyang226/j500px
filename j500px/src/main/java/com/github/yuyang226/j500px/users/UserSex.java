package com.github.yuyang226.j500px.users;


/**
 * @author yayu
 *
 */
public enum UserSex {
	MALE(1), FEMALE(2), REFUSED(0);
	
	private int sex = 0;
	
	private UserSex(int sex) {
		this.sex = sex;
	}
	
	public int getSex() {
		return this.sex;
	}
	
	public static UserSex valueOf(int value) {
		for (UserSex sex: UserSex.values()) {
			if (sex.getSex() == value) {
				return sex;
			}
		}
		return REFUSED;
				
	}
}