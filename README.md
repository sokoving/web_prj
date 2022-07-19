# post 요청은 폼에서
- post 요청은 url에 파라미터 못 씀
- 사용자가 입력하지 않는 파라미터를 컨트롤러에 보내려면 input hidden 사용
- 패스 방식은 url에 쓸 수 있음

# 메서드 오버로딩 규칙 위반
-메서드 이름은 같게, 매개변수 타입과 수는 다르게

# gradle 설정을 인텔리제이로 바꾸면 자바가 17버전으로 바뀌어서 서버 실행 안 됨...

# db에서 받은 데이터를 포매팅해 보여주기
- 도메인에 커스텀 데이터 필드를 만들어서 세팅하고 값 받아오기
``` class Board
    private String shortTitle; // 줄임 제목
    private String prettierDate; // 변경된 날짜 포맷 문자열
```



# 수정 완료하고 다시 페이지로 리다이렉트하기
> return flag? "redirect:/board/content/"+board.getBoardNo() : "redirect:/";
## 리다이렉트할 때 정보 보내기
- RedirectAttributes ra
- ra.addFlashAttribute("msg", "reg-success");
``` board-list.jsp
        const msg = '${msg}'
        console.log('msg: ', msg);
        if (msg === 'reg-success') alert('게시물이 정상 등록되었습니다.')
```
## 포워딩 할 때 정보 보내기
 - Model model
 - mode.addAttribute("msg", "reg-success");




# @Transactional
- 메서드를 트랜젝션 단위로 묶어 실행에 실패하면 이전 코드 롤백

# 조회수 무제한 올라가는 걸 막는 방법
- 한 브라우저당 시간당 한 번만 올라가게 한다
## 프로토콜의 무상태성
- http와 서버는 어떤 상태인 걸 기억하지 못 한다
 + 서버가 쿠키를 클라이언트에게 건네주고 그 쿠키를 보고 상태를 확인한다
## 쿠키 주고 받기
```
    private void makeViewCount(Long boardNo, HttpServletResponse response, HttpServletRequest request) {
        // 쿠키 조회 : HttpServletRequest request 필요
            // 해당 이름의 쿠키가 있으면 쿠키가 들어오고 없으면 null이 들어옴
        Cookie foundCookie = WebUtils.getCookie(request, "b" + boardNo);
        if(foundCookie == null){
            repository.upViewCount(boardNo);

            // 1. 쿠키 생성(javax.servlet) new Coocke("쿠키 이름", "쿠키 값")
            Cookie cookie = new Cookie("b" + boardNo, String.valueOf(boardNo));
            // 2. 쿠키 수명 설정(초) 초에 곱셈 수식으로 표현 가능 1시간 = 60*60
            cookie.setMaxAge(60);
            // 3. 쿠키 작동 범위
            cookie.setPath("/board/content");
            // 4. 클라이언트에 쿠키 전송 : HttpServletResponse response 필요
            response.addCookie(cookie);
        }
    }
```
----------------------------------------

# 1. 프로젝트 시작, 스프링 설정 종합편
## 1. https://start.spring.io/

### 1-1
```
Project : tGradle Project
Language : Java
Spring Boot : 2.7.1
Group : com.project
Artifact : web_prj
Packaging : War
Java : 11
디펜던시:  Lombok (DEVELOPER TOOLS), Spring Web (WEB), Spring Data JDBC (SQL)
```

### 1-2
- 압출 풀기 > 인텔리제이에서 열기 > 빌드
 + 폴더 안에 폴더 있는 구조가 안 되게 하기
> 빌드: BUILD SUCCESSFUL


## 2. build.gradle
- 외부 라이브러리 추가하기
```
	//jsp 라이브러리 추가
	implementation 'javax.servlet:jstl'
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'

	//오라클 라이브러리 (11g edition - gradle, maven 라이센스 문제 공식 지원 불가)
	implementation fileTree(dir: '/src/main/webapp/WEB-INF/lib', include: ['*.jar'])
```

## 3. com.project.web_prj.config.DataBaseConfig;
- DB 접속 정보 설정 (Datasource 설정)
```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.project.web_prj") // 등록된 빈을 어디 기준으로 스캔할 건지
public class DataBaseConfig {
    // DB 접속 정보 설정 (Datasource 설정)
    @Bean // 접속 정보 빈 등록
    public DataSource dataSource(){
        // 히카리 툴 사용
        HikariConfig config = new HikariConfig();
        config.setUsername("spring4");
        config.setPassword("1234");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        return new HikariDataSource(config);

    }
}
```

## 4. ordbc6.jar 파일 넣기
- 오라클xe는 라이센스 만료로 직접 넣어야 함
> src/main/webapp/WEB-INF/lib/
- ojdbc6.jar 파일 경로  
> C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib


## 5. main.resources.application.properties
- 포트 설정(번호 기억하기)
```
# 기본값 포트 8080은 오라클이 가지고 있기 때문에
# 톰캣 포트를 8181로 변경
server.port = 8181

# view resolver setting
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp

# log level setting
logging.level.root=info

logging.level.com.spring.webmvc.springmvc.chap03=trace
```


## 6. http://localhost:8183/
```
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Mon Jul 18 10:13:03 KST 2022
There was an unexpected error (type=Not Found, status=404).
```


# 2. 웰컴 페이지 만들기
## 1. src.main.wabapp.views
### 1-1 index.jsp 파일 생성 후 vscode에서 작업 
 + vscode에서는 src 폴더를 루트 폴더로 열어서 작업
### 1-2. jsp 에멧 설정하기
- 왼쪽 아래 톱니바퀴 > 사용자 코드 조각 구성 > html.json 검색
```
	"korea jsp form" : {
	   "scope": "html",
	   "prefix": "!jsp",
	   "body": [
		  "<%@ page contentType=\"text/html; charset=UTF-8\" language=\"java\" %>",
		  "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>",
		  "<!DOCTYPE html>",
		  "<html lang=\"ko\">",
		  "<head>",
			 "<meta charset=\"UTF-8\">",
			 "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">",
			 "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">",
			 
 
			 "<title>$1</title>",
		  "</head>",
		  "<body>",
		  "$2",
		  "</body>",
		  "</html>"
	   ],
	   "description": "jsp 자동완성 (한글페이지용)"
	}
```
### 1-3 main.webapp.WEB-INF.views.index.jsp
### 1-4 main.java.com.HomeController
- home 요청 수행하는 컨트롤러
```java
@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home(){
        log.info("welcome page!");
        return "index";
    }
}
```
### 1-5 index.jsp
- index.html 페이지 복붙
### 1-6 resources.static
- js, css, img 같은 정적 리소스는 스태틱에
- 이미지는 절대경로로 처리
 + 상대경로 : src="img/logo.png" > ./img/logo.png  
 + 절대경로 : src="/img/logo.png" > 파일 위치를 옮겨도 경로 유지 

### 1-7 테이블 생성하기
```roomsql
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

```
### 1-8 테이블에 매칭되는 도메인 클래스 만들기
- 컬럼 하나에 매칭되는 필드 하나
 + number의 자릿수 보고 int인지 long(Long)인지 결정
> com.project.web_prj.board.domain.Board
```java
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
```

### 1-9 레퍼지토리 만들기
> com.project.web_prj.board.repository
### 1-9-1 public interface BoardRepository
- 뭘 입력해서 어떻게 리턴할 것인지 고려해서 파라미터와 리턴 타입 정하기
### 1-9-2 BoardRepositoryImpl
- 
```java
@Repository
@Log4j2
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepository {

    private final JdbcTemplate template;

    @Override
    public boolean save(Board board) {
        log.info("save process with jdbc - {}", board);
        return false;
    }
}
```
### 1-9-3 BoardRepositoryImplTest
- DB 연결 테스트하기

### 1-10 BoardService
```java
@Service
@RequiredArgsConstructor
@Log4j2
public class BoardService {
    @Autowired
    private final BoardRepository repository;}
```

### 1-11 BoardController
```java
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private final BoardService boardService;
}
```
#### 1-11-1 컨트롤러 테스트하기
 1. 크롬 확장 프로그램 YARC (Yet Another Rest Client) 설치
 2. 톰캣 서버 켜기
```
URL: http://localhost:8183/board/write
Payload:
 {
     "writer" : "kkk",
     "title"  :  "hello hi~~",
     "content" : "hello java spring~~~~"
}
```

### 1-12 jsp 프래그먼트 파일 만들기
- html 파일 조각내기
 + views.include 폴더에 footer.jsp, header.jsp, static-head.jsp 만들기
 + head 태그들은 static-head.jsp 폴더에. header는 header.jsp, footer는 footer.jsp에 잘라내기, 붙여넣기
 + jsp 파일 맨 위줄에는
```
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
```
 + 잘라내고 남은 index.html에는 아래와 같이 넣어준다.
```html
<head>
    <%@ includ file="includ/static-head.jsp" %>
</head>
<%@ includ file="includ/header.jsp" %>
<%@ includ file="includ/footer.jsp" %>
```