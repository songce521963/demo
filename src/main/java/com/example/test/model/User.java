package com.example.test.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String date;
	private String role;
	private String name;
	private String address;

}
