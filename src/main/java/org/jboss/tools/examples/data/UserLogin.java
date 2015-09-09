package org.jboss.tools.examples.data;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.tools.examples.model.User;

@Named
@SessionScoped
public class UserLogin implements Serializable {

	@Inject
	private UserRepository userRepository;

	@Produces
	private User user;

	
	
	private boolean logIn = false;
	private String password;
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {

		return user;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String validatePass() {

		try {
			if (userRepository.findByPassWord(this.password) != null && userRepository.findByName(name) != null) {

				System.out.println("je tu taky");
				this.user = userRepository.findByPassWord(this.password);
				logIn = true;
			} else {
				System.out.println("nepodarilo sa prihlasenie");
			}

		} catch (Exception e) {
			System.out.println("nepodarilo sa prihlasenie");
		}
		return "main";

	}

	public String logOut() {

		if (user != null || logIn) {
			 //user = null;
			logIn = false;
			return "main.jsf";
		} else {
			return "login.jsf";
		}

	}
	
	public String logOut2() {
		System.out.println("tu som dpsiel opdhlasujem");
		if (user != null ) {
			user = null;
			System.out.println("tu som dpsiel opdhlasujem");
			return "main.jsf";
		} else {
			return "login.jsf";
		}

	}

	public String logInLink() {

		if (user != null) {
			return "main.jsf";
		} else {
			return "login.jsf";
		}
	}

	public String logOutMessage() {

		if (user != null || logIn) {
			return user.getName();
		} else {
			return "Please Log In";
		}
	}

	public String getLogName() {

		if (this.user != null || logIn) {

			return this.user.getName();

		} else {
			return "Log in";
		}

	}

	

}
