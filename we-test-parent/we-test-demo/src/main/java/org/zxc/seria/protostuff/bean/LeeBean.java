package org.zxc.seria.protostuff.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class LeeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id ;
	
	private String name ;

}
