package com.canesblack.spring_project1.service;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.mapper.MenuRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuRestService {

    @Autowired
    private MenuRestMapper menuRestMapper;

    public List<Menu>getLists() {
        return menuRestMapper.getLists();
    }

    public void boardInsert(Menu menu) {
        menuRestMapper.boardInsert(menu);
    }

    public Menu boardContent(int idx) {
        return menuRestMapper.boardConent(idx);
    }

    public void boardDelete(int idx) {
        menuRestMapper.boardDelete(idx);
    }

    public void boardUpdate(Menu menu) {
        menuRestMapper.boardUpdate(menu);
    }

    public void boardCount(int idx) {
        menuRestMapper.boardCount(idx);
    }
}
