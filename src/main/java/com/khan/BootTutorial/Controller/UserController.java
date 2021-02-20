package com.khan.BootTutorial.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.khan.BootTutorial.Service.UserService;
import com.khan.BootTutorial.model.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value={"/", "/login"})
	public ModelAndView login(){
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
	    return modelAndView;
	}

	
	@GetMapping(value="/registration")
	public ModelAndView registration(){
	    ModelAndView modelAndView = new ModelAndView();
	    User user = new User();
	    modelAndView.addObject("user", user);
	    modelAndView.setViewName("registration");
	    return modelAndView;
	}
	
	@PostMapping(value = "/registration")
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	    ModelAndView modelAndView = new ModelAndView();
	    User userExists = userService.findUserByEmail(user.getEmail());
	    if (userExists != null) {
	        bindingResult
	                .rejectValue("email", "error.user",
	                        "There is already a user registered with the email provided");
	    }
	    if (bindingResult.hasErrors()) {
	        modelAndView.setViewName("registration");
	    } else {
	        userService.saveUser(user);
	        modelAndView.addObject("successMessage", "User has been registered successfully");
	        modelAndView.addObject("user", new User());
	        modelAndView.setViewName("registration");
	    }
	    return modelAndView;
	}
	
	@GetMapping(value="/admin/adminHome")
	public ModelAndView home(){
	    ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findUserByEmail(auth.getName());
	    modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
	    modelAndView.addObject("adminMessage","This Page is available to Users with Admin Role");
	    modelAndView.setViewName("admin/adminHome");
	    return modelAndView;
	}
	
	@GetMapping(value="/user/userHome")
	public ModelAndView user(){
	    ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findUserByEmail(auth.getName());
	    modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
	    modelAndView.addObject("userMessage","This Page is available to Users with User Role");
	    modelAndView.setViewName("user/userHome");
	    return modelAndView;
	}
	
}
