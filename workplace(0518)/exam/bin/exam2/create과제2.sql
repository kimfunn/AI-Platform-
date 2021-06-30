create table student (
    isbn number(5) primary key;
    title varchar(50) not null;
    author varchar(50) not null;
    publisher varchar(50) not null;
    price number(6) not null;
    describe varchar(200);
    )
    
comment on student.isbn is '도서 번호';
comment on student.title is '도서 명';
comment on student.author is '저자';
comment on student.publisher is '출판사';
comment on student.price is '가격';
comment on student.describe is '상세';

insert into book values(21424,'Java Basic','김하나','Jaen.kr',15000,'Java 기본 문법');
insert into book values(33455,'JDBC pro','김철수','Jaen.kr',23000);
insert into book values(55355,'Sevlet/JSP','박자바','Jaen.kr',41000,'Model2기반');
insert into book values(35332,'Android APP','홍길동','Jaen.kr',25000,'Lightweight Framework');
insert into book values(35355,'OOAD 분석,설계','소나무','Jaen.kr',30000);