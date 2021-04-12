package com.userapp.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.userapp.model.User;
import com.userapp.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(value= UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	
	List<User> mockUserList = new ArrayList<User>(Arrays.asList(
            new User(1,"user1","Dublin",Arrays.asList(3)),
            new User(2,"user2","Dublin",Arrays.asList(3,4)),
            new User(3,"user3","London",Arrays.asList(1,2)),
            new User(4,"user4","Belfast",Arrays.asList(2))
    ));
	

	
	//1
	@Test
	public void testGetUsers() throws Exception {
		Mockito.when(userService.getUsers()).thenReturn(mockUserList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is("user1")))
		.andExpect(jsonPath("$[1].name", is("user2")))
		.andExpect(jsonPath("$.size()").value(4));

        verify(userService, times(1)).getUsers();
		 
	}
	
	@Test
	public void testGetUserFriends() throws Exception {
		
		List <User> mockRes = new ArrayList<>();
		mockRes.add(mockUserList.get(2));
		Mockito.when(userService.getUserFriends("1")).thenReturn(mockRes);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/friends/1").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is("user3")))
		.andExpect(jsonPath("$[0].city", is("London")))
		.andExpect(jsonPath("$[0].friends.size()").value(2))
		.andExpect(jsonPath("$.size()").value(1));
        verify(userService, times(1)).getUserFriends("1");
		
	}
	
	@Test
	public void testGetUserFriendSuggetions() throws Exception{
		List <User> mockRes = new ArrayList<>();
		mockRes.add(mockUserList.get(1));
		Mockito.when(userService.getUserFriendSuggestions("1")).thenReturn(mockRes);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/suggestions/1").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is("user2")))
		.andExpect(jsonPath("$[0].city", is("Dublin")))
		.andExpect(jsonPath("$[0].friends.size()").value(2))
		.andExpect(jsonPath("$.size()").value(1));
        verify(userService, times(1)).getUserFriendSuggestions("1");
	}
	

}
