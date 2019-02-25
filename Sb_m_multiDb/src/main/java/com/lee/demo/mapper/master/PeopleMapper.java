package com.lee.demo.mapper.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lee.demo.pojo.People;
/***
 * master数据库映射
 * @author LYW
 *
 */
@Mapper
public interface PeopleMapper {
	@Select("SELECT name FROM people WHERE id = #{id}")
    @Results({
        @Result(property = "Name", column = "name")
    })
	People getPeopleByID(int id);
}
