package com.project.web_prj.common.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @AllArgsConstructor
// 페이지 정보 클래스
public class Page {
    private int pageNum; // 페이지 번호
    private int amount; // 한 페이지당 배치할 게시물 수


    public Page() {
    }


    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
