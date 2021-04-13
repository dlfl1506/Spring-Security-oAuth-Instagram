# 인스타그램 클론 코딩

### 의존성

- Sring Boot DevTools
- Lombok
- Spring Data JPA
- MySQL Driver
- Spring Security
- Spring Web
- oauth2-client

```xml
<!-- 시큐리티 태그 라이브러리 -->
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-taglibs</artifactId>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
	<groupId>org.apache.tomcat</groupId>
	<artifactId>tomcat-jasper</artifactId>
	<version>9.0.44</version>
</dependency>

<!-- JSTL -->
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
</dependency>
```


### 데이터베이스
```sql
create user 'costa'@'%' identified by 'costa1234';
GRANT ALL PRIVILEGES ON *.* TO 'costa'@'%';
create database costa;
```

### yml 설정
```
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: utf-8
      enabled: true
    
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
      
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/costa?serverTimezone=Asia/Seoul&useSSL=false&allowPublicKeyRetrieval=true
    username: costa
    password: costa1234
    
  jpa:
    open-in-view: true
    hibernate:
      ddl-auto: create
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true
      
  servlet:
    multipart:
      enabled: true
      max-file-size: 2MB

  security:
    user:
      name: test
      password: 1234   

file:
  path: C:/src/instagram/src/main/resources/upload/
```

### 태그 라이브러리
```
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

```

### ✔ 기능

#### 1. 회원가입

<img src= "https://user-images.githubusercontent.com/74044212/114503005-9c227300-9c67-11eb-8917-138d5b051d54.png" width="300" height="300px" />

##### 먼저 security 적용시켜서 @EnableWebSecurity 어노테이션을 통해 security를 커스터마이징
##### "/", "/user/**", "/image/**", "/follow/**","/comment/**" 주소로 접근하려는모든것을 loginForm 으로 리다이렉트 시킴
##### 그리고 "hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')" 유저,어드민 권한이 있어야만 접근가능

<img src= "https://user-images.githubusercontent.com/74044212/114503325-13580700-9c68-11eb-891b-33ba5796773b.png" width="600px" height="300px" />


<img src= "https://user-images.githubusercontent.com/74044212/114503643-9aa57a80-9c68-11eb-9ef4-df72031754f5.png" width="600px" height="300px" />

##### password는 BCryptPasswordEncoder 를 통해 암호화시켜서 디비에 insert 


#### 2. 로그인 

<img src= "https://user-images.githubusercontent.com/74044212/114503922-012a9880-9c69-11eb-8095-7ca84bd2bcd6.png" width="300px" height="300px" />

##### 시큐리티의 로그인은 UserDetails 를 리턴해주어야한다.
##### 그래서 UserDetails 를 implements !!

<img src= "https://user-images.githubusercontent.com/74044212/114514950-cdef0600-9c76-11eb-8132-2a4dd16470fb.png" width="600px" height="300px" />

<img src= "https://user-images.githubusercontent.com/74044212/114515077-ef4ff200-9c76-11eb-890a-c6c5e603f8b0.png" width="600px" height="300px" />


#### 3. 다른유저팔로잉

<img src= "https://user-images.githubusercontent.com/74044212/114524511-40181880-9c80-11eb-877e-709ae8c96166.gif" width="100%" height="600px" />

#### 4. 사진등록

<img src= "https://user-images.githubusercontent.com/74044212/114524550-48705380-9c80-11eb-80c5-4669b0dc5407.gif" width="100%" height="600px" />

#### 5. 프로필사진변경

<img src= "https://user-images.githubusercontent.com/74044212/114524596-51612500-9c80-11eb-9b55-2bf38cc650cc.gif" width="100%" height="600px" />

#### 6. 회원정보변경

<img src= "https://user-images.githubusercontent.com/74044212/114524659-5de57d80-9c80-11eb-99aa-54475b7fd913.gif" width="100%" height="600px" />

#### 7. 로그아웃

<img src= "https://user-images.githubusercontent.com/74044212/114524727-6b9b0300-9c80-11eb-9e87-ec60be67d63e.gif" width="100%" height="600px" />

#### 8. 팔로잉한사람만 피드보여주기

<img src= "https://user-images.githubusercontent.com/74044212/114524780-75bd0180-9c80-11eb-9ec4-6c65441a8195.gif" width="100%" height="600px" />

#### 9. 좋아요기능

<img src= "https://user-images.githubusercontent.com/74044212/114524820-82d9f080-9c80-11eb-8364-16c3b6e201fd.gif" width="100%" height="600px" />

#### 10. 댓글등록,삭제

<img src= "https://user-images.githubusercontent.com/74044212/114524856-8c635880-9c80-11eb-8cfb-427533eb62a2.gif" width="100%" height="600px" />

#### 11. 좋아요한 게시물 explore 사진보여주기

<img src= "https://user-images.githubusercontent.com/74044212/114524977-a8ff9080-9c80-11eb-8039-be02bf007619.png" width="100%" height="600px" />


