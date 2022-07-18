package com.project.web_prj.board.repository;

import com.project.web_prj.board.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryImplTest {

    @Autowired
//    @Qualifier("bri")
    BoardRepository repository;

    @Test
    @DisplayName("300개의 게시물을 삽입해야 한다.")
    void bulktInsert(){
        Board board;
        for (int i = 1; i <= 300; i++) {
           board=new Board();
           board.setTitle("제목"+i);
           board.setWriter("쓰니"+i);
           board.setContent("내용"+i);
           repository.save(board);
        }
    }

    @Test
    @DisplayName("전체 게시물을 조회하고 반환된 리스트의 사이즈는 300이어야 한다")
    void findAllTesst(){
        List<Board> all = repository.findAll();
        for (Board b : all) {
            System.out.println(b);
        }
        assertEquals(all.size(), 300);
    }

}