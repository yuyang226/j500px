package com.github.yuyang226.j500px.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yayu
 * @see http://developer.500px.com/docs/formats#user_formats
 */
public class User implements Serializable {

	private static final long serialVersionUID = -1583852964702095613L;
	
	public static final String ID = "id";
	public static final String USER_NAME = "username";
	public static final String BUDDY_ICON_URL = "userpic_url";
	
	private int id = -1;
	private String userName;
	private String firstName;
	private String lastName;
	private String fullName;
	private String userPicUrl;
	/**
	 * Sex of the user, string. Values: 1 and 2 for male and female respectively, 0 if user refused to specify their sex.
	 */
	private UserSex sex;
	private String city;
	private String state;
	private String country;
	private Date registrationDate;
	private String about;
	private Date birthday;
	/**
	 * Whether the user is a premium user, integer. Non-zero values identify premium users.
	 */
	private int upgradeStatus;
	private Date upgradeStatusExpiry;
	private String domain;
	private String locale;
	private boolean showNude;
	private int friendsCount;
	private int photosCount;
	private int inFavoritesCount;
	private int followersCount;
	private int affection;
	private String email;
	/**
	 * Whether the user has the store option enabled
	 */
	private boolean fotomotoOn;
	private AuthedSites authedSites;
	
	private int uploadLimit;
	private Date uploadLimitExpiry;
	private List<UserContact> contacts = new ArrayList<UserContact>();
	private List<Equipment> equipments = new ArrayList<Equipment>();
	
	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the userPicUrl
	 */
	public String getUserPicUrl() {
		return userPicUrl;
	}

	/**
	 * @param userPicUrl the userPicUrl to set
	 */
	public void setUserPicUrl(String userPicUrl) {
		this.userPicUrl = userPicUrl;
	}

	/**
	 * @return the sex
	 */
	public UserSex getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(UserSex sex) {
		this.sex = sex;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the about
	 */
	public String getAbout() {
		return about;
	}

	/**
	 * @param about the about to set
	 */
	public void setAbout(String about) {
		this.about = about;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the upgradeStatus
	 */
	public int getUpgradeStatus() {
		return upgradeStatus;
	}
	
	public boolean isPremiumUser() {
		return upgradeStatus > 0;
	}

	/**
	 * @param upgradeStatus the upgradeStatus to set
	 */
	public void setUpgradeStatus(int upgradeStatus) {
		this.upgradeStatus = upgradeStatus;
	}
	
	/**
	 * @return the upgradeStatusExpiry
	 */
	public Date getUpgradeStatusExpiry() {
		return upgradeStatusExpiry;
	}

	/**
	 * @param upgradeStatusExpiry the upgradeStatusExpiry to set
	 */
	public void setUpgradeStatusExpiry(Date upgradeStatusExpiry) {
		this.upgradeStatusExpiry = upgradeStatusExpiry;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the showNude
	 */
	public boolean isShowNude() {
		return showNude;
	}

	/**
	 * @param showNude the showNude to set
	 */
	public void setShowNude(boolean showNude) {
		this.showNude = showNude;
	}

	/**
	 * @return the friendsCount
	 */
	public int getFriendsCount() {
		return friendsCount;
	}

	/**
	 * @param friendsCount the friendsCount to set
	 */
	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}

	/**
	 * @return the photosCount
	 */
	public int getPhotosCount() {
		return photosCount;
	}

	/**
	 * @param photosCount the photosCount to set
	 */
	public void setPhotosCount(int photosCount) {
		this.photosCount = photosCount;
	}

	/**
	 * @return the inFavoritesCount
	 */
	public int getInFavoritesCount() {
		return inFavoritesCount;
	}

	/**
	 * @param inFavoritesCount the inFavoritesCount to set
	 */
	public void setInFavoritesCount(int inFavoritesCount) {
		this.inFavoritesCount = inFavoritesCount;
	}
	
	/**
	 * @return the followersCount
	 */
	public int getFollowersCount() {
		return followersCount;
	}

	/**
	 * @param followersCount the followersCount to set
	 */
	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	/**
	 * @return the affection
	 */
	public int getAffection() {
		return affection;
	}

	/**
	 * @param affection the affection to set
	 */
	public void setAffection(int affection) {
		this.affection = affection;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the authedSites
	 */
	public AuthedSites getAuthedSites() {
		return authedSites;
	}

	/**
	 * @param authedSites the authedSites to set
	 */
	public void setAuthedSites(AuthedSites authedSites) {
		this.authedSites = authedSites;
	}
	
	/**
	 * @return the fotomotoOn
	 */
	public boolean isFotomotoOn() {
		return fotomotoOn;
	}

	/**
	 * @param fotomotoOn the fotomotoOn to set
	 */
	public void setFotomotoOn(boolean fotomotoOn) {
		this.fotomotoOn = fotomotoOn;
	}

	/**
	 * @return the uploadLimit
	 */
	public int getUploadLimit() {
		return uploadLimit;
	}

	/**
	 * @param uploadLimit the uploadLimit to set
	 */
	public void setUploadLimit(int uploadLimit) {
		this.uploadLimit = uploadLimit;
	}

	/**
	 * @return the uploadLimitExpiry
	 */
	public Date getUploadLimitExpiry() {
		return uploadLimitExpiry;
	}

	/**
	 * @param uploadLimitExpiry the uploadLimitExpiry to set
	 */
	public void setUploadLimitExpiry(Date uploadLimitExpiry) {
		this.uploadLimitExpiry = uploadLimitExpiry;
	}

	/**
	 * @return the contacts
	 */
	public List<UserContact> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(List<UserContact> contacts) {
		this.contacts = contacts;
	}
	
	/**
	 * @return the equipments
	 */
	public List<Equipment> getEquipments() {
		return equipments;
	}

	/**
	 * @param equipments the equipments to set
	 */
	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + "]";
	}
	
}
