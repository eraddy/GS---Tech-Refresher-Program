package com.main;

import com.designpattern.dto.Role;
import com.designpattern.dto.Session;
import com.designpattern.dto.User;
import com.designpattern.singleton.SessionManager;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DesignPatternTest {

    SessionManager sessionManager = SessionManager.getInstance();

	@GetMapping("/")
	public String home() {
		return "Spring is here!";
	}

    @GetMapping("/user")
    public ResponseEntity<Session> getSessionForUser(@RequestParam String  userName, @RequestParam String roleDescription, @RequestParam String accessPath)
    {
        User user = new User(userName, Role.getRole(roleDescription));
        return new ResponseEntity<>(sessionManager.createSession(user,accessPath), HttpStatus.OK);
    }

	public static void main(String[] args) {
		SpringApplication.run(DesignPatternTest.class, args);
	}
}
