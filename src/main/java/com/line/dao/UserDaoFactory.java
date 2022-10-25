package com.line.dao;

public class UserDaoFactory {
    public UserDao awsUserDao() {
        return new UserDao(new AwsConnectionMaker());
    }
}
