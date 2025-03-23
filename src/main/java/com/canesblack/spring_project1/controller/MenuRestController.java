package com.canesblack.spring_project1.controller;


import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.service.MenuRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MenuRestController {

    @Autowired
    private MenuRestService menuRestService;

    @GetMapping("/menu/all")
    public ResponseEntity<List<Menu>>getAllMenus() {
        List<Menu>menus = menuRestService.getLists();
        if(menus!= null || menus.isEmpty()) {
            return ResponseEntity.ok(menus);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
//menu post 생성
    @PostMapping("/menu/add")
    public ResponseEntity<String> addMenu(@RequestBody Menu menu) {
        //작성날짜 자동 적용
        if(menu.getIndate() == null || menu.getIndate().isEmpty()) {
            menu.setIndate(LocalDate.now().toString());
        }
        menu.setCount(0);
        menuRestService.boardInsert(menu);
        return ResponseEntity.ok("Menu added successfully 게시글 잘 작성 완료");
    }

    //메뉴 한개에 게시판 수정 8080/menu/update/idx
    @PutMapping("/menu/update/{idx}")
    public void updateMenu(@RequestBody Menu menu, @PathVariable("idx") int idx) {
        menu.setIdx(idx);//idx 가진 글 수정
        menuRestService.boardUpdate(menu);
    }
    //menu post delete
    @DeleteMapping("/menu/delete/{idx}")
    public void deleteMenu(@PathVariable("idx") int idx) {
        menuRestService.boardDelete(idx);
    }

    //menu post read
    @GetMapping("menu/{idx}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("idx") int idx) {
        Menu menu = menuRestService.boardContent(idx);
        if(menu != null) {
            return ResponseEntity.ok(menu);//200 state code ,back to front
        }else{
            return ResponseEntity.notFound().build();//400 error not found
        }
    }

    //조회수 증가
    @PutMapping("/menu/increment/{idx}")
    public void incrementCount(@PathVariable("idx") int idx) {
        menuRestService.boardCount(idx);
    }

}
