package com.lee.demo.mapper.cluster;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.lee.demo.pojo.People;

@Mapper
public interface PersonMapper {
	@Insert("INSERT INTO person(name) VALUES(#{name})")
    void insert(People person);
}
