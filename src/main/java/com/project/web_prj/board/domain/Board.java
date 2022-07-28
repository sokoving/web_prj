package com.project.web_prj.board.domain;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Board {

    // 테이블 컬럼 필드
    private Long boardNo;
    private String writer;
    private String title;
    private String content;
    private Long viewCnt;
    private Timestamp regDate;

    // 커스텀 데이터 필드
    private String shortTitle; // 줄임 제목
    private String prettierDate; // 변경된 날짜 포맷 문자열
    private boolean newArticle; // 신규 게시물
    private int replyCount; // 댓글 수

    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getLong("board_no");
        this.title = rs.getString("title");
        this.writer = rs.getString("writer");
        this.content = rs.getString("content");
        this.viewCnt = rs.getLong("view_cnt");
        this.regDate = rs.getTimestamp("reg_date");
    }
}