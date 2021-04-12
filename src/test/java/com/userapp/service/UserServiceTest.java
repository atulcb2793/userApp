package com.userapp.service;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.userapp.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@MockBean
	private UserService userService;
	
	List<User> mockUserList = new ArrayList<User>(Arrays.asList(
            new User(1,"user1","Dublin",Arrays.asList(3)),
            new User(2,"user2","Dublin",Arrays.asList(3,4)),
            new User(3,"user3","London",Arrays.asList(1,2)),
            new User(4,"user4","Belfast",Arrays.asList(2))
    ));
	
	
	@Test
	public void testGetUser() {

		Mockito.when(userService.getUsers()).thenReturn(mockUserList);
		List<User> result = userService.getUsers();
		assertThat(result.get(1).getName()).isEqualTo("user2");
		assertThat(result.get(1).getCity()).isEqualTo("Dublin");
		assertThat(result.get(1).getFriends().size()).isEqualTo(2);
		assertThat(result).isEqualTo(mockUserList);
		assertThat(result.size()).isEqualTo(4);
	}
	

	@Test
	public void testGetUserFriends() throws Exception {
		List <User> mockRes = new ArrayList<>();
		mockRes.add(mockUserList.get(2));
		Mockito.when(userService.getUserFriends("1")).thenReturn(mockRes);
		List<User> result = userService.getUserFriends("1");
		assertThat(result.get(0).getName()).isEqualTo("user3");
		assertThat(result.get(0).getCity()).isEqualTo("London");
		assertThat(result.get(0).getFriends().size()).isEqualTo(2);
		assertThat(result.size()).isEqualTo(1);
		
	}
	
	@Test
	public void testGetUserFriendSuggetions() throws Exception{
		List <User> mockRes = new ArrayList<>();
		mockRes.add(mockUserList.get(1));
		Mockito.when(userService.getUserFriendSuggestions("1")).thenReturn(mockRes);
		List<User> result = userService.getUserFriendSuggestions("1");
		assertThat(result.get(0).getName()).isEqualTo("user2");
		assertThat(result.get(0).getCity()).isEqualTo("Dublin");
		assertThat(result.get(0).getFriends().size()).isEqualTo(2);
		assertThat(result.size()).isEqualTo(1);
	
	}
	
	
}
