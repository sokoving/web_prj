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
- index.jsp 파일 생성 후 vscode에서 작업 
