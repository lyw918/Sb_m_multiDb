package com.lee.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lee.demo.mapper.cluster.PersonMapper;
import com.lee.demo.mapper.master.PeopleMapper;
import com.lee.demo.pojo.People;

@RestController
@RequestMapping("/api/")
public class TestController {
	@Autowired
	private PeopleMapper people;
	@Autowired
	private PersonMapper person;
	/***
	 * 测试API
	 * @return
	 */
	@RequestMapping("testapi")
	public String TestAPI() {
		System.out.println(people.getPeopleByID(1).getName());
		People p = new People();
		p.setName("li");
		person.insert(p);
		return "test api";
	}

}
