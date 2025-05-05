package integrationProjectGHM.GitHubMiner.service;

import integrationProjectGHM.GitHubMiner.model.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    @DisplayName("Get all users")
    void getAllIssues() {
        ResponseEntity<User[]> response = userService.getAllUsers();
        List<User> users = List.of(response.getBody());
        assertFalse(users.isEmpty(), "User list should not be empty");
        //System.out.println(users);
        System.out.println("Total users: " + users.size());
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @DisplayName("Get User by name")
    void getUserByName() {
        ResponseEntity<User> response = userService.getUserByName("octocat");
        User user = response.getBody();
        assertNotNull(user, "User should not be null");
        System.out.println(user);
    }
}