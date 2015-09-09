package org.jboss.tools.examples.data;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UserPreco implements Serializable{

	public String talc(){
		System.out.println("sdf");
		return "main";
	}
	
}
