# 책 테이블
drop table book;
CREATE TABLE book (
    id int(11) DEFAULT NULL AUTO_INCREMENT,
    book_name varchar(100) NOT NULL COMMENT '책이름',
    author varchar(30) COMMENT '저자',
    publisher varchar(30) COMMENT '출판사',
    publish_date date COMMENT '출판날짜',
    use_yn bit DEFAULT 1 COMMENT '사용여부',
    created_dt datetime DEFAULT current_timestamp() COMMENT '최초 등록 일자',
    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;


# 크롤러 설정 테이블
drop table set_crawler;
CREATE TABLE set_crawler(
    id int(11) DEFAULT NULL AUTO_INCREMENT,
    book_id int(11) not null COMMENT 'BOOK ID',
    store_name VARCHAR(30) NOT NULL COMMENT '서점명',
    url VARCHAR(500) NOT NULL COMMENT 'URL',
    use_yn bit NOT NULL DEFAULT 1 COMMENT '사용여부',
    created_dt datetime DEFAULT current_timestamp() COMMENT '최초 등록 일자',
    PRIMARY KEY (ID),
    KEY created_dt(created_dt)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;


# 크롤러 로그테이블
drop table log_crawler_kyobo;
CREATE TABLE log_crawler_kyobo (
    id int(11) DEFAULT NULL AUTO_INCREMENT,
    book_id int(11) not null COMMENT 'BOOK ID',
    crawler_dt datetime DEFAULT current_timestamp() COMMENT '수집날짜',
    total_rank int(11) DEFAULT 0 COMMENT '전체 순위',
    category_name varchar(50) DEFAULT 0 COMMENT '분야 이름',
    category_rank int(11) DEFAULT 0 COMMENT '분야별 순위',
    created_dt datetime DEFAULT current_timestamp() COMMENT '최초 등록 일자',
    PRIMARY KEY (ID),
    KEY created_dt(created_dt)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;


# 크롤러 받을 대상
drop table chat_user;
create table chat_user
(
    chat_name  varchar(50) not null comment '챗봇 이름',
    chat_id    int not null comment '개인 챗봇 방 ID',
    first_name varchar(20) not null comment '성',
    last_name  varchar(30) not null comment '이름',
    use_yn bit NOT NULL DEFAULT 1 COMMENT '사용여부',
    created_dt datetime DEFAULT current_timestamp() COMMENT '최초 등록 일자',
    primary key (chat_name, chat_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;