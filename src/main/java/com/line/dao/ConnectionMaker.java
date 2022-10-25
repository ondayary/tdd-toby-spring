package com.line.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

    // interface 도입
    public Connection makeConnection() throws SQLException;
}
