package com.project.web_prj.common.search;

import com.project.web_prj.common.paging.Page;
import lombok.*;

@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor
public class Search {

    // 검색은 페이징이 필수적 > 의존관계(컴포지션)
    private Page page;
    private String type; // 검색 조건
    private String keyword; // 검색 키워드
}
