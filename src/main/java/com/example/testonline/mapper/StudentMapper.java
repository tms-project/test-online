package com.example.testonline.mapper;

import com.example.testonline.pojo.Student;
import org.apache.ibatis.annotations.*;

public interface StudentMapper {

    @Select("SELECT * FROM `tos_student` WHERE `sid` = #{sid}")
    Student getOneById(String sid);

    @Delete("DELETE FROM `tos_student` WHERE `sid` = '${sid}';")
    int delete(@Param("sid") String sid);

    @Insert("INSERT INTO `tos_student`(`sid`,`sname`,`sex`,`cardNumber`,`password`,`department`,`phone`) VALUES(#{sid},#{sname},#{sex},#{cardNumber},#{password},#{department},#{phone});")
    int insert(Student student);

    @Update("UPDATE `tos_student` SET `sname`=#{sname},`sex`=#{sex},`cardNumber`=#{cardNumber},`password`=#{password},`department`=#{department},`phone`=#{phone} WHERE sid = #{sid}")
    int update(Student student);

}
