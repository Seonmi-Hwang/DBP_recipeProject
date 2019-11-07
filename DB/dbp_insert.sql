INSERT INTO member VALUES (mid_sequence.nextval, '123@dongduk.com', 123, '양갱이');
INSERT INTO member VALUES (mid_sequence.nextval, '234@dongduk.com', 234, '양갱이');
INSERT INTO member VALUES (mid_sequence.nextval, '345@dongduk.com', 345, '나초');
INSERT INTO member VALUES (mid_sequence.nextval, '456@dongduk.com', 456, '도토');
INSERT INTO member VALUES (mid_sequence.nextval, '567@dongduk.com', 567, '후크');


INSERT INTO ingredient_info VALUES (iid_sequence.nextval, '계란류', '계란');
INSERT INTO ingredient_info VALUES (iid_sequence.nextval, '빵류', '식빵');
INSERT INTO ingredient_info VALUES (iid_sequence.nextval, '채소류', '오이');


INSERT INTO prefer_ingredient VALUES (1, 1000, 'T');
INSERT INTO prefer_ingredient VALUES (1, 1002, 'F');


INSERT INTO category VALUES (10, 'common');
INSERT INTO category VALUES (20, 'sns');
INSERT INTO category VALUES (30, 'myOwn');


--1. 계란빵 (recipe_id SEQUENCE 필요)
INSERT INTO recipe_info VALUES(rid_sequence.nextval, 10, 'eggBread', 15, null, 0);
--2. 간장계란밥
INSERT INTO recipe_info VALUES(rid_sequence.nextval, 10, 'soybeanPasteEggRice', 10, null, 0);
--3. 짜파구리
INSERT INTO recipe_info VALUES(rid_sequence.nextval, 20, 'zzapaguri', 5, null, 0);
--4. 전남친토스트
INSERT INTO recipe_info VALUES(rid_sequence.nextval, 20, 'exboyToast', 10, null, 0);
--5. 쉐하토스트
INSERT INTO recipe_info VALUES(rid_sequence.nextval, 30, 'shareHouseToast', 10, null, 0);


INSERT INTO ingredient VALUES(1000, 100, '2조각');
INSERT INTO ingredient VALUES(1001, 100, '2알');
INSERT INTO ingredient VALUES(1001, 101, '1알');


INSERT INTO recipe_procedure VALUES(100, 1, '밀가루에 우유를 붓는다', null);
INSERT INTO recipe_procedure VALUES(100, 2, '섞는다', null);
INSERT INTO recipe_procedure VALUES(100, 3, '반죽에 계란을 넣는다', null);
INSERT INTO recipe_procedure VALUES(100, 4, '오븐에 굽는다', null);
INSERT INTO recipe_procedure VALUES(101, 1, '밥을 짓는다', null);


INSERT INTO users_recipe VALUES (1, 104, to_date('11-07-2019','mm-dd-yyyy'));




-- 아래는 sequence를 실수했을 경우 실행하는 SQL이다.

DROP SEQUENCE iid_sequence;

CREATE SEQUENCE iid_sequence
	INCREMENT BY 1
	START WITH 1000;

DROP SEQUENCE mid_sequence;

CREATE SEQUENCE mid_sequence
	INCREMENT BY 1
	START WITH 1;

DROP SEQUENCE pid_sequence;

CREATE SEQUENCE pid_sequence
	INCREMENT BY 1
	START WITH 1;

DROP SEQUENCE rid_sequence;

CREATE SEQUENCE rid_sequence
	INCREMENT BY 1
	START WITH 100;
    
-- 아래는 테이블을 실수했을 경우 실행하는 SQL이다.
    
DROP TABLE prefer_ingrdient CASCADE CONSTRAINTS PURGE;    

CREATE TABLE prefer_ingredient
(
	member_id            NUMBER NOT NULL ,
	ingredient_id        NUMBER NOT NULL ,
	prefer               CHAR(1) NOT NULL 
);

DROP TABLE recipe_procedure CASCADE CONSTRAINTS PURGE;

CREATE TABLE recipe_procedure
(
	recipe_id            NUMBER NOT NULL ,
	proc_id              NUMBER NOT NULL ,
	text                 VARCHAR2(500) NOT NULL ,
	img_url              VARCHAR2(500) NULL
);