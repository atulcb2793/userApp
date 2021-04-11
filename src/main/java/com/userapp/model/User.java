package com.userapp.model;

import java.util.List;


public class User implements Comparable<User> {

    private int id;
    private String name;
    private String city;
    private List<Integer> friends;

    public User(int id, String name, String city, List<Integer> friends) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", friends=" + friends +
                '}';
    }

	@Override
	public int compareTo(User o) {
		return this.getCity().compareTo(o.getCity());
	}

}
