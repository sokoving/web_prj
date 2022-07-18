package com.project.web_prj.board.domain;

import lombok.*;

import java.sql.Date;

@Setter @Getter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class Board {

    private Long boardNo;
    private String writer;
    private String title;
    private String content;
    private Long viewCnt;
    private Date regDate;


}
