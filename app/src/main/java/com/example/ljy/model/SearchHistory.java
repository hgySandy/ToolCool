package com.example.ljy.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table (name="search")//创建表明为search的表
public class SearchHistory extends Model {

	//创建name的字段
	@Column (name="name")
	private String name;

	public SearchHistory() {
		super();
	}

	public SearchHistory(String name) {
		super();
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
