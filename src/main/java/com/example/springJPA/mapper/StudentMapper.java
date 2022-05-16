package com.example.springJPA.mapper;

import com.example.springJPA.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("Select * from student where name LIKE #{name}")
    List<Student> getStudentsContainStrInName(@Param("name") String name);

    @Select("SELECT * from student where university_class_id in" +
            "(SELECT id from university_class where year = #{year} and number = #{number})")
    List<Student> getStudentsInClass(@Param("year") int year, @Param("number") int number);
}
