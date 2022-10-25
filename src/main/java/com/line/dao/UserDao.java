package com.line.dao;

import com.line.domain.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Map;

public class UserDao {
    public UserDao(AwsConnectionMaker awsConnectionMaker) {

    }

//    private ConnectionMaker connectionMaker;
}
    public void add(User user) {
        Map<String, String> env = System.getenv();
        try {
            /*Connection conn = DriverManager.getConnection(
                    env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD")
            );*/
            Connection conn = makeConnection();

            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO users(id, name, password) VALUES(?,?,?)"
            );
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());

            // 상태값 알기
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        Map<String, String> env = System.getenv();

        try {
            /*Connection conn = DriverManager.getConnection(
                    env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD")
            );*/
            Connection conn = makeConnection();

            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT id, name, password FROM users WHERE id = ?"
            );

            // id는 get(String id) 로 받은 id
            pstmt.setString(1, id);

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // makeConnection() 분리
    private Connection makeConnection() throws SQLException {
        Map<String, String> env = System.getenv();

        Connection conn = DriverManager.getConnection(
                env.get("DB_HOST"), env.get("DB_USER"), env.get("DB_PASSWORD")
        );
        return conn;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
//        userDao.add();
        User user = userDao.get("1");
        System.out.println(user.getName());
    }


}
