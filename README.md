
# 책 순위 크롤링

- 각 서점별로 크롤링한 후 텔레그램 챗봇으로 순위 전달

- 챗봇 결과
~~~
[교보] 혼자 살아도 괜찮아
 -----------------------------
(변경) 종합순위: 101위   (89 -> 101)
(변경없음) 분야순위(인문): 17위
2020.01.24 15:52 => 2020.01.28 13:42
 -----------------------------

[YES24] 혼자 살아도 괜찮아
 -----------------------------
(변경) 인문 top20: 3주   (2 -> 3)
(변경) 분야순위(인문): 20위   (182 -> 20)
(변경) 판매지수: 24573   (23577 -> 24573)
2020.01.24 15:52 => 2020.01.28 13:42
 -----------------------------
~~~


## 구성
- Spring Boot
- JPA
- Querydsl
- React.js
- Telegrambot


## 실행 시 주의할 점
- 백앤드 수행포트: 9100
- 프론트앤드 폴더: frontend
- 프론트앤드 실행:
~~~ 
cd frontend/bot-admin/
npm start
~~~
- telegram token 이 등록되어 있지 않으면 에러가 발생합니다
~~~
java.lang.RuntimeException: 텔레그램 챗봇 API Token 을 입력해 주세요(application-*.properties)
~~~

등록위치
application.properties
~~~
telegram.token=[token]
~~~


## 기타
- bootJar build 시, frontend 의 빌드가 "build/resources/main/static" 로 복사
    - 프론트앤드를 별도 실행하지 않아도 됨