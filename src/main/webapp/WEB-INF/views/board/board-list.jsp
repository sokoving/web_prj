<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <%@ include file="../include/static-head.jsp" %>

    <style>
        .board-list {
            width: 70%;
            margin: 0 auto;
        }

        .inner-board-list {
            border: 1px solid #000;

            transform: translateY(120px);

        }

        .board-list .articles {
            margin: 250px auto 100px;
            border-collapse: collapse;
            font-size: 1.5em;
            border-radius: 10px;
        }

        header {
            background: #222;
            border-bottom: 1px solid #2c2c2c;
        }

        /* pagination style */
        .bottom-section {
            margin-top: -50px;
            margin-bottom: 100px;
            display: flex;
        }

        .bottom-section nav {
            flex: 9;
            display: flex;
            justify-content: center;
        }

        .bottom-section .btn-write {
            flex: 1;
        }

        .pagination-custom a {
            color: #444 !important;
        }

        .pagination-custom li.active a,
        .pagination-custom li:hover a {
            background: #333 !important;
            color: #fff !important;
        }
    </style>
</head>

<body>

    <div class="wrap">

        <%@ include file="../include/header.jsp" %>

        <div class="board-list">
            <div class="inner-board-list">
            <!-- 게시글 줄 수 입력 버튼 -->
            <div class="btn-group" role="group" aria-label="Basic outlined example">
                <button type="button" class="btn btn-outline-primary">10줄</button>
                <button type="button" class="btn btn-outline-primary">20줄</button>
                <button type="button" class="btn btn-outline-primary">30줄</button>
            </div>
            <!-- 게시글 테이블 영역 -->
            <table class="table table-dark table-striped table-hover articles">
                <tr>
                    <th>번호</th>
                    <th>작성자</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성시간</th>
                </tr>

                <c:forEach var="b" items="${bList}">
                    <tr>
                        <td>${b.boardNo}</td>
                        <td>${b.writer}</td>
                        <td title="${b.title}">${b.shortTitle}</td>
                        <td>${b.viewCnt}</td>
                        <td>${b.prettierDate}</td>
                    </tr>
                </c:forEach>
            </table>

            <!-- 게시글 목록 하단 영역 -->
            <div class="bottom-section">

                <!-- 페이지 버튼 영역 -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-lg pagination-custom" id="page-btn-group">

                        <c:if test="${pm.prev}">
                            <li class="page-item"><a class="page-link"
                                    href="/board/list?pageNum=${pm.beginPage - 1}">prev</a></li>
                        </c:if>

                        <c:forEach var="n" begin="${pm.beginPage}" end="${pm.endPage}" step="1">
                            <li class="page-item">
                                <a class="page-link page-num" href="/board/list?pageNum=${n}">${n}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${pm.next}">
                            <li class="page-item"><a class="page-link"
                                    href="/board/list?pageNum=${pm.endPage + 1}">next</a></li>
                        </c:if>

                    </ul>
                </nav>

                <!-- 글쓰기 버튼 영역 -->
                <div class="btn-write">
                    <a class="btn btn-outline-danger btn-lg" href="/board/write">글쓰기</a>
                </div>
            </div>
        </div>
        </div>


        <%@ include file="../include/footer.jsp" %>

    </div>

    <script>
        // 현재 위치한 페이지 번호를 가진 li에 active 클래스 부여하기
        const $pageBtn = document.getElementById('page-btn-group');
        const pageNum = '${pm.page.pageNum}';

        for (let $li of [...$pageBtn.children]) {
            if ($li.textContent.trim() == pageNum) {
                $li.classList.add('active')
            }
        }


        // 새 글 등록 성공 메세지 띄우기
        const msg = '${msg}';
        console.log('msg: ', msg);
        if (msg === 'reg-success') {
            alert('게시물이 정상 등록되었습니다.');
        }
        //상세보기 요청 이벤트
        const $table = document.querySelector(".articles");
        $table.addEventListener('click', e => {


            if (!e.target.matches('.articles td')) return;
            console.log('tr 클릭됨! - ', e.target);
            let bn = e.target.parentElement.firstElementChild.textContent;
            console.log('글번호: ' + bn);
            location.href = '/board/content/' + bn;
        });
    </script>

</body>

</html>