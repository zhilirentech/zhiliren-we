package org.zxc.seria;

import java.io.Serializable;

import lombok.Data;

@Data
public class People implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	private String name ;
	
	public People(String name){
		this.name = name ;
	}
}
