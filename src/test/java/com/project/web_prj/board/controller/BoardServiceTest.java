package com.project.web_prj.board.controller;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.common.paging.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService service;

/*
    @Test
    @DisplayName("게시물 전체 조회 중간 처리 결과 리스트가 반환되어야 한다")
    void findAllServiceTest(){
        List<Board> boardList = service.findAllService();
        for (Board b : boardList) {
            System.out.println(b);
        }

        assertEquals(300, boardList.size());

    }
*/



}