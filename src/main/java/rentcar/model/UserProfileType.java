package rentcar.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	USER("USER"),
	SUPERUSER("SUPERUSER"),
	ADMIN("ADMIN"),
	TEMP("TEMP");
	
	String userProfileType;
	
	UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
