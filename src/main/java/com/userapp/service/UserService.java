package com.userapp.service;

import com.userapp.model.User;


import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private List<User> userList = new ArrayList<User>(Arrays.asList(
            new User(1,"Alice","Dublin",Arrays.asList(3)),
            new User(2,"Bob","Dublin",Arrays.asList(3,4,5)),
            new User(3,"Charlie","London",Arrays.asList(1,2,5)),
            new User(4,"Davina","Belfast",Arrays.asList(2,5)),
            new User(5,"John","Galway",Arrays.asList(2,3,4))
    ));

    public List<User> getUsers(){
        return userList;
    }

    public List<User> getUserFriends(String id){
            List<User> resultSet = new ArrayList<>();
            User user = findUserbasedOnId(id);
            if(user != null){
                for(Integer i : user.getFriends()){
                    User friend = findUserbasedOnId(Integer.toString(i));
                    if(friend != null){
                        resultSet.add(friend);
                    }
                }
            }
            return resultSet;
    }

    private User findUserbasedOnId(String id){
        if(id == null || id.isEmpty()) {
            return null;
        }
        for(User u : userList){
            if(u.getId() == Integer.parseInt(id)){
                return u;
            }
        }
        return null;
    }
    
    private List<User> getSuggestedFriendsDetails(String id){
    	List<User> friendList =  getUserFriends(id);
    	
		Set<Integer> suggestions = new HashSet<>();
		Set<Integer> nonMutual = new HashSet<>();
		
		List<User> resultSet = new ArrayList<>();
		
		nonMutual.add(Integer.parseInt(id));
		 if(friendList != null){
             for(User u : friendList){
            	 nonMutual.add(u.getId());
            	 for(Integer i : u.getFriends()) {
            		 if(!nonMutual.contains(i)) {
            			 suggestions.add(i);
            		}
            	}
             }
             suggestions.removeAll(nonMutual);
             System.out.println("suggestions "+ suggestions);
             System.out.println("nonMutual "+ nonMutual);
            // get details  
     		for(Integer i : suggestions) {
     			User u = findUserbasedOnId(Integer.toString(i));
     			resultSet.add(u);
     		}
     		System.out.println("final "+ resultSet);
     		// sort by location
     	    Collections.sort(resultSet);
         }
		
	    return resultSet;
    }

    public List<User> getUserFriendSuggestions(String id){
        return getSuggestedFriendsDetails(id);
    }

}
