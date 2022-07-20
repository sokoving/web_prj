package com.project.web_prj.board.repository;

import com.project.web_prj.board.domain.Board;
import com.project.web_prj.common.paging.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("db에 게시글이 등록돼야 한다")
    void saveTest(){
        Board board = new Board();
        board.setWriter("mWriter");
        board.setTitle("mTitle");
        board.setContent("mContent");
        boolean flag = mapper.save(board);
        assertTrue(flag);
    }

    @Test
    @DisplayName("db에 게시글이 삭제돼야 한다")
    @Transactional
    @Rollback
    void removeTest(){
        Long boardNo = 310L;
        boolean flag = mapper.remove(boardNo);
        assertTrue(flag);
    }

    @Test
    @DisplayName("DB에서 특정 게시물을 가지고 와야 한다")
    void findOneTest(){
        Long boardNo = 309L;
        Board one = mapper.findOne(boardNo);
        System.out.println(one);
    }

    @Test
    @DisplayName("DB에서 전체 게시물을 가지고 와야 한다")
    void findAllTest(){
        List<Board> all = mapper.findAll(new Page(2, 20));
        for (Board b : all) {
            System.out.println(b);
        }
    }

}