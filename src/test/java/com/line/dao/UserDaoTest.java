package com.line.dao;

import com.line.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void addAndSelect() {
        UserDao userDao = new UserDaoFactory().awsUserDao();
        String id = "1";
        User user = new User(id, "test", "test");
       assertEquals("daon", user.getName());
       assertEquals("lee", user.getPassword());
    }
}