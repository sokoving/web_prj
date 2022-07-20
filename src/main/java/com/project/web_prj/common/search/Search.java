package com.project.web_prj.common.search;

import com.project.web_prj.common.paging.Page;
import lombok.*;

@Setter @Getter @ToString @NoArgsConstructor
public class Search extends Page {

    // 검색은 페이징이 필수적 > 의존관계(컴포지션)
    private String type; // 검색 조건
    private String keyword; // 검색 키워드

    public Search(int pageNum, int amount, String type, String keyword) {
        super(pageNum, amount);
        this.type = type;
        this.keyword = keyword;
    }
}
