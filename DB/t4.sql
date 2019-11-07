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

DROP TABLE ingredient CASCADE CONSTRAINTS PURGE;

DROP TABLE users_recipe CASCADE CONSTRAINTS PURGE;

DROP TABLE recipe_procedure CASCADE CONSTRAINTS PURGE;

DROP TABLE recipe_info CASCADE CONSTRAINTS PURGE;

DROP TABLE category CASCADE CONSTRAINTS PURGE;

DROP TABLE prefer_ingrdient CASCADE CONSTRAINTS PURGE;

DROP TABLE ingredient_info CASCADE CONSTRAINTS PURGE;

DROP TABLE member CASCADE CONSTRAINTS PURGE;

CREATE TABLE category
(
	category_id          CHAR(18) NOT NULL ,
	cname                CHAR(18) NOT NULL 
);

CREATE UNIQUE INDEX XPKcategories ON category
(category_id   ASC);

ALTER TABLE category
	ADD CONSTRAINT  XPKcategories PRIMARY KEY (category_id);

CREATE TABLE ingredient_info
(
	ingredient_id        NUMBER NOT NULL ,
	icategory            CHAR(18) NULL ,
	iname                CHAR(18) NULL 
);

CREATE UNIQUE INDEX XPKingredients_info ON ingredient_info
(ingredient_id   ASC);

ALTER TABLE ingredient_info
	ADD CONSTRAINT  XPKingredients_info PRIMARY KEY (ingredient_id);

CREATE TABLE member
(
	member_id            NUMBER NOT NULL ,
	mname                CHAR(18) NOT NULL ,
	pw                   CHAR(18) NOT NULL ,
	email_id             VARCHAR2(40) NOT NULL 
);

CREATE UNIQUE INDEX XPKmembers ON member
(member_id   ASC);

ALTER TABLE member
	ADD CONSTRAINT  XPKmembers PRIMARY KEY (member_id);

CREATE TABLE prefer_ingrdient
(
	member_id            NUMBER NOT NULL ,
	ingredient_id        NUMBER NOT NULL ,
	prefer               CHAR(1) NOT NULL 
);

CREATE UNIQUE INDEX XPKprefer_ingrdients ON prefer_ingrdient
(member_id   ASC,ingredient_id   ASC);

ALTER TABLE prefer_ingrdient
	ADD CONSTRAINT  XPKprefer_ingrdients PRIMARY KEY (member_id,ingredient_id);

CREATE TABLE recipe_info
(
	recipe_id            NUMBER NOT NULL ,
	category_id          CHAR(18) NOT NULL ,
	rname                VARCHAR2(30) NOT NULL ,
	time                 NUMBER DEFAULT  0  NULL ,
	result_img           VARCHAR2(100) NULL ,
	hits                 NUMBER DEFAULT  0  NULL 
);

CREATE UNIQUE INDEX XPKrecipes_info ON recipe_info
(recipe_id   ASC);

ALTER TABLE recipe_info
	ADD CONSTRAINT  XPKrecipes_info PRIMARY KEY (recipe_id);

CREATE TABLE ingredient
(
	ingredient_id        NUMBER NOT NULL ,
	recipe_id            NUMBER NOT NULL ,
	quantity             VARCHAR2(10) NULL 
);

CREATE UNIQUE INDEX XPKingredients ON ingredient
(ingredient_id   ASC,recipe_id   ASC);

ALTER TABLE ingredient
	ADD CONSTRAINT  XPKingredients PRIMARY KEY (ingredient_id,recipe_id);

CREATE TABLE users_recipe
(
	member_id            NUMBER NOT NULL ,
	recipe_id            NUMBER NOT NULL ,
	createdDate          DATE NULL 
);

CREATE UNIQUE INDEX XPKusers_recipies ON users_recipe
(recipe_id   ASC,member_id   ASC);

ALTER TABLE users_recipe
	ADD CONSTRAINT  XPKusers_recipies PRIMARY KEY (recipe_id,member_id);

CREATE TABLE recipe_procedure
(
	recipe_id            NUMBER NOT NULL ,
	proc_id              NUMBER NOT NULL ,
	text                 CHAR(18) NOT NULL ,
	img_url              VARCHAR2(100) NULL
);

CREATE UNIQUE INDEX XPKrecipe_procedure ON recipe_procedure
(recipe_id   ASC,proc_id   ASC);

ALTER TABLE recipe_procedure
	ADD CONSTRAINT  XPKrecipe_procedure PRIMARY KEY (recipe_id,proc_id);

ALTER TABLE prefer_ingrdient
	ADD (CONSTRAINT R_25 FOREIGN KEY (member_id) REFERENCES member (member_id));

ALTER TABLE prefer_ingrdient
	ADD (CONSTRAINT R_26 FOREIGN KEY (ingredient_id) REFERENCES ingredient_info (ingredient_id));

ALTER TABLE recipe_info
	ADD (CONSTRAINT R_5 FOREIGN KEY (category_id) REFERENCES category (category_id));

ALTER TABLE ingredient
	ADD (CONSTRAINT R_3 FOREIGN KEY (ingredient_id) REFERENCES ingredient_info (ingredient_id));

ALTER TABLE ingredient
	ADD (CONSTRAINT R_4 FOREIGN KEY (recipe_id) REFERENCES recipe_info (recipe_id));

ALTER TABLE users_recipe
	ADD (CONSTRAINT R_7 FOREIGN KEY (member_id) REFERENCES member (member_id));

ALTER TABLE users_recipe
	ADD (CONSTRAINT R_6 FOREIGN KEY (recipe_id) REFERENCES recipe_info (recipe_id));

ALTER TABLE recipe_procedure
	ADD (CONSTRAINT R_24 FOREIGN KEY (recipe_id) REFERENCES recipe_info (recipe_id));
