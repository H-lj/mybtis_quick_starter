package com.self.mapper;

import com.self.project.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.IOException;
import java.util.List;

public interface IUserMapper {
    @Select("select * from user")
    public List<User> findAll(User user) throws IOException;
    @Insert("insert into user values(#{id},#{username})")
    public void add(User user) throws IOException;

    @Delete("delete from user where id = #{id}")
    public void remove(User user) throws IOException;

    @Update(" update user set username = #{username} where id = #{id}")
    public void update(User user) throws IOException;
    @Select("select * from user where id = #{id}")
    public User selectById(Integer id);
}
