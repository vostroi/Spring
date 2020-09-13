package com.vostroi.users;

import com.vostroi.Users02;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest(classes = Users02.class)
@RunWith(SpringRunner.class)
class UsersApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        System.out.println(System.currentTimeMillis());
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
    }
}
