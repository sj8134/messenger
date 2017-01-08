package org.sahil.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.sahil.messenger.model.Comment;
import org.sahil.messenger.model.Message;
import org.sahil.messenger.model.Profile;

public class DatabaseClass {

	private static Map<Long , Message> messages = new HashMap<Long, Message>();
	private static Map<String, Profile> profiles = new HashMap<String, Profile>();
	private static Map<Long, Comment> comments = new HashMap<Long, Comment>();
	
	public static Map<Long, Message> getMessages(){
		return messages;
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
	
	public static Map<Long, Comment> getComments(){
		return comments;
	}
	
}
