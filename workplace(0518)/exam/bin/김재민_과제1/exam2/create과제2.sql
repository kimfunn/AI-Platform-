create table student (
    isbn number(5) primary key;
    title varchar(50) not null;
    author varchar(50) not null;
    publisher varchar(50) not null;
    price number(6) not null;
    describe varchar(200);
    )
    
comment on student.isbn is '���� ��ȣ';
comment on student.title is '���� ��';
comment on student.author is '����';
comment on student.publisher is '���ǻ�';
comment on student.price is '����';
comment on student.describe is '��';

insert into book values(21424,'Java Basic','���ϳ�','Jaen.kr',15000,'Java �⺻ ����');
insert into book values(33455,'JDBC pro','��ö��','Jaen.kr',23000);
insert into book values(55355,'Sevlet/JSP','���ڹ�','Jaen.kr',41000,'Model2���');
insert into book values(35332,'Android APP','ȫ�浿','Jaen.kr',25000,'Lightweight Framework');
insert into book values(35355,'OOAD �м�,����','�ҳ���','Jaen.kr',30000);