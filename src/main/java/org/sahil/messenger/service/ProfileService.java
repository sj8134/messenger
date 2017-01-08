package org.sahil.messenger.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sahil.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	public ProfileService(){
		profiles.put("Sahil", new Profile(1L, "Sahil", "Sahil", "Jasrotia", new Date()));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getMessage(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName() == null) return null;
		
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfie(String profileName){
		return profiles.remove(profileName);
	}

}
