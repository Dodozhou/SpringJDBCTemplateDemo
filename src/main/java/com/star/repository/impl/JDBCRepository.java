package com.star.repository.impl;

import com.star.domain.User;
import com.star.repository.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hp on 2017/3/8.
 */
@Repository
public class JDBCRepository implements UserRepository {
    //该接口定义了JDBCTemplate所实现的操作
    private JdbcOperations jdbcOperations;

    private JDBCRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations=jdbcOperations;
    }

    public void addUser(User user) {
        String query="insert into users values(null,?,?,'USER',1)";
        jdbcOperations.update(query,user.getUsername(),user.getPassword()
        );
    }

    public void deleteUserByUserName(String userName) {
        String sql="delete from users where username=?";
        jdbcOperations.update(sql,userName);
    }

    public User findUserByUserName(String userName) {
        final User user=new User();
        String sql="select * from users where username=?";
        jdbcOperations.queryForObject(sql, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                user.setUsername(resultSet.getString("username"));
                user.setROLE_USER(resultSet.getString("ROLE_USER"));
                return user;
            }
        });
        return user;
    }

    public void updateUser(User user) {
        String sql="update users set password=?,ROLE_USER=? where username=?";
        jdbcOperations.update(sql,user.getPassword(),user.getROLE_USER(),user.getUsername());
    }
}
