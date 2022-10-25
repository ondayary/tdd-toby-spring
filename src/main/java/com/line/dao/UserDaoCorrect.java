package com.line.dao;

import com.line.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDaoCorrect {

    public void add() throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();

        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        /*Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/likelion-db", "root", "password"
        );*/
        PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO users(id, name, password) VALUES(?,?,?)"
        );
        pstmt.setString(1, "1");
        pstmt.setString(2, "daon");
        pstmt.setString(3, "lee");

        // 상태값 알기
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();

        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);

        PreparedStatement pstmt = conn.prepareStatement(
                "SELECT id, name, password FROM users WHERE id = ?"
        );
        pstmt.setString(1, id); // id는 get(String id) 로 받은 id

        // ResultSet 은  executeQuery()를 했을때 ResultSet을 반환한다.
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"),
                rs.getString("name"),
                rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDaoCorrect userDao = new UserDaoCorrect();
//        userDao.add();
        User user = userDao.get("1");
        System.out.println(user.getName());
    }
}
