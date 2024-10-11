package com.revature.user.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.revature.user.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewController {

	@Autowired
	RestTemplate restTemplate;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "home"; // Serve home.jsp for the home page
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Serve login.jsp for the login page
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration"; // Serve registration.jsp for the registration page
    }

    @PostMapping("/doLogin")
    public ModelAndView doLogin(@RequestParam String email, @RequestParam String password) {
        // Fetch users from the API
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:8080/api/users"; // The API URL for fetching users
        User[] users = restTemplate.getForObject(apiUrl, User[].class);

        if (users != null) {
            // Check if the user exists and verify the password
            List<User> userList = Arrays.asList(users);
            for (User user : userList) {
                if (user.getEmail().equals(email) && passwordEncoder.matches(password, user.getPassword())) {
                    // Redirect based on user role
                    User.Role role = user.getRoles().iterator().next(); // Get the first role

                    if (role.equals(User.Role.ADMIN)) {
                        return new ModelAndView("redirect:/adminPage");
                    } else if (role.equals(User.Role.SELLER)) {
                        return new ModelAndView("redirect:/sellerPage");
                    } else if (role.equals(User.Role.BUYER)) {
                        return new ModelAndView("redirect:/buyerPage");
                    }
                }
            }
        }

        // If login fails, redirect back to login page with an error message
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("error", "Invalid email or password.");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        // Invalidate the session to clear session data
        session.invalidate();
        
        // Redirect to the desired URL
        return new ModelAndView("redirect:http://localhost:8080/");
    }
    
    
    // Seller page
    @GetMapping("/sellerPage")
    public String sellerPage() {
        return "sellerPage"; // Serve sellerPage.jsp for seller
    }



}
