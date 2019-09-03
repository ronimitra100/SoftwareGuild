package com.sg.contactlistspringmvc.dao;
import com.sg.contactlistspringmvc.model.User;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoDbImpl implements UserDao{
  public static final String SQL_INSERT_USER= "insert into users (username, password, enabled) values (?,?,1)";
  public static final String SQL_INSERT_AUTHORITY= "insert into authorities (username, authority) values (?,?)";
  public static final String SQL_DELETE_USER="delete from users where username=?";
  public static final String SQL_DELETE_AUTHORITIES = "delete from authorities where username=?";
  public static final String SQL_GET_ALL_USERS ="select * from users";
  
  private JdbcTemplate jdbcTemplate;
  
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
      this.jdbcTemplate = jdbcTemplate;
  }

    @Override
    public User addUser(User newUser) {
        jdbcTemplate.update(SQL_INSERT_USER,newUser.getUsername(),newUser.getPassword());
        newUser.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        ArrayList<String> authorities = newUser.getAuthorities();
        for (String authority : authorities){
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, newUser.getUsername(), authority);
        }
        return newUser;
    }

    @Override
    public void deleteUser(String username) {
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        jdbcTemplate.update(SQL_DELETE_USER,username);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_GET_ALL_USERS, new UserMapper());
    }
     
    private static final class UserMapper implements RowMapper<User>{
        
        public User mapRow(ResultSet rs, int rowNum) throws SQLException{
            User user = new User();
            user.setId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            
            return user;
        }
    }
  

}
