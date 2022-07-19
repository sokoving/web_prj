--실무에서는 쓰지 않기(링크로 직접 접근이 가능하며, 컬럼명이 알려지면 공격하기 쉬워짐)
drop SEQUENCE seq_tbl_board;
DROP TABLE tbl_board;

CREATE SEQUENCE seq_tbl_board;
CREATE TABLE tbl_board (
    board_no NUMBER(10),
    writer VARCHAR2(20) NOT NULL,
    title VARCHAR2(200) NOT NULL,
    content CLOB,
    view_cnt NUMBER(10) DEFAULT 0,
    reg_date DATE DEFAULT SYSDATE,
    CONSTRAINT pk_tbl_board PRIMARY KEY (board_no)
);

