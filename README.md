# Spring_Boot
스프링 부트 공부 용도 입니다

## 학습
- MemberService를 이용하였습니다 - Misson1 2023-10-12
- Builder에 대해서 학습하였습니다 - BuilderStudy 2023-10-13
- 의존성 관리와 자동 설정  2023-10-16
- misson2 h2 database를 이용한 쿼리문 처리 2023-10-16
- misson h2와 service , controller를 이용한 로직을 구현 2023-10-19
- JPA를 이용한 처리 2023-10-23
-쿼리 스트링 -페이징 처리, 정렬 2023-10-25 
- 연관관계 매핑 2023-10-27
- Misson05  JpaRepository 2023-10-30
- 스프링 부트 화면 개발(타임리프 적용) 2023-11-02
	- Brower -> Controller -> Service -> ServiceImpl -> Repository 
	- 손님	    웨이터         주방장      보조	  -> Board(DB)
- 사용자 인증(로그인 처리) 2023-11-03 
- Spring Boot Security 2023-11-06
   - 시큐리티 커스터마이징하기
   - JPA 연동 시큐리티
   - UsernamePasswordAuthenticationFilter
   - AuthorizationFilter
- 로그인 세션 정보 체크 추가 & JPX 2023-11-08
   - JWT (JSON Web Token)
   - 인증에 필요한 정보를 암호화한 JSON Token
- 웹 애플리케이션 통합 2023-11-13
   - 1. 비즈니스 레이어 개발
      - Entity 클래스 만들기
      - Repository Interface 작성하기
      - JPA 테스트 하기
      - Service Interface & Class 만들기
   - 2. 프레젠테이션 레이어 개발
      - 글 목록 기능 구현하기
         - Controller 작성
      - 게시판 기능 구현하기
   - 3. 시큐리티 적용(인증,인가)
      - 비밀번호 암호화
   - 4. 기타 기능 추가 하기
      - 타임리프에서 로그인 정보 이용 하기
      - 검색 기능 추가
## 기억해야할 어노테이션
- Component
	- RestController, Controller , Service , Repository
- Configuration
	- Bean

RestController : REST Full 통신을 위하여 사용
Controller : HTML 페이지 렌더링을 위하여 사용

C insert Post
R select Get
U upddate Put
D delete Delete

## 유용한 단축어
- 스프링 부트
	- ctrl + shift + O 자동 임포트

## 환경
- Sptring Tools 4 for Exclipse 4.20.1 - Windows X86_64
- Postman app - Windows 64-bit
- lombok 추가 : mvnrepository.com 에서 다운로드 해서 , sts4 설치 위치에 설치, 프로젝트 우클릭 - Maven - Update Project
- https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30

### 의존성
- H2 Database
- Lombok
- Spring Boot DevTools
- Spring Data JPA
- Spring Security
- Spring Web
- Thymeleaf

### QueryDSL
> <!--dependency-->
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-jpa</artifactId>
			<version>5.0.0</version>
			<classifier>jakarta</classifier>
		</dependency>
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-apt</artifactId>
			<version>5.0.0</version>
			<classifier>jakarta</classifier>
</dependency>

> <!--dependency-->
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-jpa</artifactId>
			<version>5.0.0</version>
			<classifier>jakarta</classifier>
		</dependency>
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-apt</artifactId>
			<version>5.0.0</version>
			<classifier>jakarta</classifier>
</dependency>