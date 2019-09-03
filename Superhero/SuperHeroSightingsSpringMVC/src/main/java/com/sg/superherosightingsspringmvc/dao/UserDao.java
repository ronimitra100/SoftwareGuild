package com.sg.superherosightingsspringmvc.dao;
import com.sg.superherosightingsspringmvc.model.User;
import java.util.List;

public interface UserDao {

 public User addUser(User newUser);

 public void deleteUser(String username);
 
 public List<User> getAllUsers();

}