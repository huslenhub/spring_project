package com.canesblack.spring_project1.mapper;

import com.canesblack.spring_project1.entity.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuRestMapper {
//마지작 글이 먼저 보여주게 하기 DESC
    @Select("SELECT idx,memID,content,indate,count FROM backend_spring_project.menu ORDER BY idx DESC ")
    public List<Menu> getLists();
    @Insert("INSERT INTO backend_spring_project.menu(memID,title,content,writer,indate) VALUES (#{memID},#{title},#{content},#{indate},#{writer})")
    public void boardInsert(Menu menu);
    @Select("SELECT idx,memID,content,indate,count FROM backend_spring_project.menu WHERE idx=#{idx}")
    public Menu boardConent(int idx);
    @Delete("DELETE FROM backend_spring_project.menu WHERE idx=#{idx}")
    public Menu boardDelete(int idx);
    @Update("UPDATE backend_spring_project.menu SET title=#{title},content=#{content},writer=#{writer} WHERE idx=#{idx}")
    public void boardUpdate(Menu menu);
    @Update("UPDATE backend_spring_project.menu SET count=count+1 WHERE idx=#{idx}")
    public void boardCount(int idx);
}
