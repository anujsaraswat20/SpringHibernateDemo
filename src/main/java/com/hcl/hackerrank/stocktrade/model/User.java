package com.hcl.hackerrank.stocktrade.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
//@Entity
public class User {
//	@Id
	@Column(name="user_id")
	private Long id;
	private String name;

	public User() {
	}

	public User(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
