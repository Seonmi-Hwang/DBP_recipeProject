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
	iname                CHAR(18) NULL ,
	icategory            CHAR(18) NULL 
);

CREATE UNIQUE INDEX XPKingredients_info ON ingredient_info
(ingredient_id   ASC);

ALTER TABLE ingredient_info
	ADD CONSTRAINT  XPKingredients_info PRIMARY KEY (ingredient_id);

CREATE TABLE member
(
	member_id            NUMBER NOT NULL ,
	pw                   CHAR(18) NOT NULL ,
	mname                CHAR(18) NOT NULL ,
	email_id             VARCHAR2(40) NOT NULL  CONSTRAINT  emailCheck CHECK (email_id like '%_@_%._%')
);

CREATE UNIQUE INDEX XPKmembers ON member
(member_id   ASC);

ALTER TABLE member
	ADD CONSTRAINT  XPKmembers PRIMARY KEY (member_id);

CREATE TABLE prefer_ingrdient
(
	member_id            NUMBER NOT NULL ,
	prefer               CHAR(1) NOT NULL ,
	ingredient_id        NUMBER NOT NULL 
);

CREATE UNIQUE INDEX XPKprefer_ingrdients ON prefer_ingrdient
(member_id   ASC,ingredient_id   ASC);

ALTER TABLE prefer_ingrdient
	ADD CONSTRAINT  XPKprefer_ingrdients PRIMARY KEY (member_id,ingredient_id);

CREATE TABLE recipe_info
(
	rname                VARCHAR2(30) NOT NULL ,
	time                 NUMBER DEFAULT  0  NULL ,
	hits                 NUMBER DEFAULT  0  NULL ,
	category_id          CHAR(18) NOT NULL ,
	recipe_id            NUMBER NOT NULL ,
	result_img           VARCHAR2(100) NULL 
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
	proc_id              NUMBER NOT NULL ,
	text                 CHAR(18) NOT NULL ,
	img_url              VARCHAR2(100) NULL ,
	recipe_id            NUMBER NOT NULL 
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

CREATE  TRIGGER  tD_category AFTER DELETE ON category for each row
-- erwin Builtin Trigger
-- DELETE trigger on category 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* category  recipe_info on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0000d880", PARENT_OWNER="", PARENT_TABLE="category"
    CHILD_OWNER="", CHILD_TABLE="recipe_info"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="category_id" */
    SELECT count(*) INTO NUMROWS
      FROM recipe_info
      WHERE
        /*  %JoinFKPK(recipe_info,:%Old," = "," AND") */
        recipe_info.category_id = :old.category_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete category because recipe_info exists.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_category AFTER UPDATE ON category for each row
-- erwin Builtin Trigger
-- UPDATE trigger on category 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* category  recipe_info on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00010084", PARENT_OWNER="", PARENT_TABLE="category"
    CHILD_OWNER="", CHILD_TABLE="recipe_info"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="category_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.category_id <> :new.category_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM recipe_info
      WHERE
        /*  %JoinFKPK(recipe_info,:%Old," = "," AND") */
        recipe_info.category_id = :old.category_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update category because recipe_info exists.'
      );
    END IF;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_ingredient_info AFTER DELETE ON ingredient_info for each row
-- erwin Builtin Trigger
-- DELETE trigger on ingredient_info 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* ingredient_info  prefer_ingrdient on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0001fd0f", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_26", FK_COLUMNS="ingredient_id" */
    SELECT count(*) INTO NUMROWS
      FROM prefer_ingrdient
      WHERE
        /*  %JoinFKPK(prefer_ingrdient,:%Old," = "," AND") */
        prefer_ingrdient.ingredient_id = :old.ingredient_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete ingredient_info because prefer_ingrdient exists.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* ingredient_info  ingredient on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="ingredient_id" */
    SELECT count(*) INTO NUMROWS
      FROM ingredient
      WHERE
        /*  %JoinFKPK(ingredient,:%Old," = "," AND") */
        ingredient.ingredient_id = :old.ingredient_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete ingredient_info because ingredient exists.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_ingredient_info AFTER UPDATE ON ingredient_info for each row
-- erwin Builtin Trigger
-- UPDATE trigger on ingredient_info 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* ingredient_info  prefer_ingrdient on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="000246f0", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_26", FK_COLUMNS="ingredient_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.ingredient_id <> :new.ingredient_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM prefer_ingrdient
      WHERE
        /*  %JoinFKPK(prefer_ingrdient,:%Old," = "," AND") */
        prefer_ingrdient.ingredient_id = :old.ingredient_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update ingredient_info because prefer_ingrdient exists.'
      );
    END IF;
  END IF;

  /* erwin Builtin Trigger */
  /* ingredient_info  ingredient on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="ingredient_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.ingredient_id <> :new.ingredient_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM ingredient
      WHERE
        /*  %JoinFKPK(ingredient,:%Old," = "," AND") */
        ingredient.ingredient_id = :old.ingredient_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update ingredient_info because ingredient exists.'
      );
    END IF;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_member AFTER DELETE ON member for each row
-- erwin Builtin Trigger
-- DELETE trigger on member 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* member  prefer_ingrdient on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0001e0a1", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="member_id" */
    SELECT count(*) INTO NUMROWS
      FROM prefer_ingrdient
      WHERE
        /*  %JoinFKPK(prefer_ingrdient,:%Old," = "," AND") */
        prefer_ingrdient.member_id = :old.member_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete member because prefer_ingrdient exists.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* member  users_recipe on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="member_id" */
    SELECT count(*) INTO NUMROWS
      FROM users_recipe
      WHERE
        /*  %JoinFKPK(users_recipe,:%Old," = "," AND") */
        users_recipe.member_id = :old.member_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete member because users_recipe exists.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_member AFTER UPDATE ON member for each row
-- erwin Builtin Trigger
-- UPDATE trigger on member 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* member  prefer_ingrdient on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00022fc1", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="member_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.member_id <> :new.member_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM prefer_ingrdient
      WHERE
        /*  %JoinFKPK(prefer_ingrdient,:%Old," = "," AND") */
        prefer_ingrdient.member_id = :old.member_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update member because prefer_ingrdient exists.'
      );
    END IF;
  END IF;

  /* erwin Builtin Trigger */
  /* member  users_recipe on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="member_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.member_id <> :new.member_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM users_recipe
      WHERE
        /*  %JoinFKPK(users_recipe,:%Old," = "," AND") */
        users_recipe.member_id = :old.member_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update member because users_recipe exists.'
      );
    END IF;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_prefer_ingrdient BEFORE INSERT ON prefer_ingrdient for each row
-- erwin Builtin Trigger
-- INSERT trigger on prefer_ingrdient 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* ingredient_info  prefer_ingrdient on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0002055b", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_26", FK_COLUMNS="ingredient_id" */
    SELECT count(*) INTO NUMROWS
      FROM ingredient_info
      WHERE
        /* %JoinFKPK(:%New,ingredient_info," = "," AND") */
        :new.ingredient_id = ingredient_info.ingredient_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert prefer_ingrdient because ingredient_info does not exist.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* member  prefer_ingrdient on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="member_id" */
    SELECT count(*) INTO NUMROWS
      FROM member
      WHERE
        /* %JoinFKPK(:%New,member," = "," AND") */
        :new.member_id = member.member_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert prefer_ingrdient because member does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_prefer_ingrdient AFTER UPDATE ON prefer_ingrdient for each row
-- erwin Builtin Trigger
-- UPDATE trigger on prefer_ingrdient 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* ingredient_info  prefer_ingrdient on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="0001ffc1", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_26", FK_COLUMNS="ingredient_id" */
  SELECT count(*) INTO NUMROWS
    FROM ingredient_info
    WHERE
      /* %JoinFKPK(:%New,ingredient_info," = "," AND") */
      :new.ingredient_id = ingredient_info.ingredient_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update prefer_ingrdient because ingredient_info does not exist.'
    );
  END IF;

  /* erwin Builtin Trigger */
  /* member  prefer_ingrdient on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="prefer_ingrdient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_25", FK_COLUMNS="member_id" */
  SELECT count(*) INTO NUMROWS
    FROM member
    WHERE
      /* %JoinFKPK(:%New,member," = "," AND") */
      :new.member_id = member.member_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update prefer_ingrdient because member does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_recipe_info AFTER DELETE ON recipe_info for each row
-- erwin Builtin Trigger
-- DELETE trigger on recipe_info 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* recipe_info  recipe_procedure on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="0002e635", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="recipe_procedure"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="recipe_id" */
    SELECT count(*) INTO NUMROWS
      FROM recipe_procedure
      WHERE
        /*  %JoinFKPK(recipe_procedure,:%Old," = "," AND") */
        recipe_procedure.recipe_id = :old.recipe_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete recipe_info because recipe_procedure exists.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* recipe_info  users_recipe on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="recipe_id" */
    SELECT count(*) INTO NUMROWS
      FROM users_recipe
      WHERE
        /*  %JoinFKPK(users_recipe,:%Old," = "," AND") */
        users_recipe.recipe_id = :old.recipe_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete recipe_info because users_recipe exists.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* recipe_info  ingredient on parent delete restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_4", FK_COLUMNS="recipe_id" */
    SELECT count(*) INTO NUMROWS
      FROM ingredient
      WHERE
        /*  %JoinFKPK(ingredient,:%Old," = "," AND") */
        ingredient.recipe_id = :old.recipe_id;
    IF (NUMROWS > 0)
    THEN
      raise_application_error(
        -20001,
        'Cannot delete recipe_info because ingredient exists.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tI_recipe_info BEFORE INSERT ON recipe_info for each row
-- erwin Builtin Trigger
-- INSERT trigger on recipe_info 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* category  recipe_info on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0000e44b", PARENT_OWNER="", PARENT_TABLE="category"
    CHILD_OWNER="", CHILD_TABLE="recipe_info"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="category_id" */
    SELECT count(*) INTO NUMROWS
      FROM category
      WHERE
        /* %JoinFKPK(:%New,category," = "," AND") */
        :new.category_id = category.category_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert recipe_info because category does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_recipe_info AFTER UPDATE ON recipe_info for each row
-- erwin Builtin Trigger
-- UPDATE trigger on recipe_info 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* recipe_info  recipe_procedure on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="0004507e", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="recipe_procedure"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="recipe_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.recipe_id <> :new.recipe_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM recipe_procedure
      WHERE
        /*  %JoinFKPK(recipe_procedure,:%Old," = "," AND") */
        recipe_procedure.recipe_id = :old.recipe_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update recipe_info because recipe_procedure exists.'
      );
    END IF;
  END IF;

  /* erwin Builtin Trigger */
  /* recipe_info  users_recipe on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="recipe_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.recipe_id <> :new.recipe_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM users_recipe
      WHERE
        /*  %JoinFKPK(users_recipe,:%Old," = "," AND") */
        users_recipe.recipe_id = :old.recipe_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update recipe_info because users_recipe exists.'
      );
    END IF;
  END IF;

  /* erwin Builtin Trigger */
  /* recipe_info  ingredient on parent update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_4", FK_COLUMNS="recipe_id" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.recipe_id <> :new.recipe_id
  THEN
    SELECT count(*) INTO NUMROWS
      FROM ingredient
      WHERE
        /*  %JoinFKPK(ingredient,:%Old," = "," AND") */
        ingredient.recipe_id = :old.recipe_id;
    IF (NUMROWS > 0)
    THEN 
      raise_application_error(
        -20005,
        'Cannot update recipe_info because ingredient exists.'
      );
    END IF;
  END IF;

  /* erwin Builtin Trigger */
  /* category  recipe_info on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="category"
    CHILD_OWNER="", CHILD_TABLE="recipe_info"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="category_id" */
  SELECT count(*) INTO NUMROWS
    FROM category
    WHERE
      /* %JoinFKPK(:%New,category," = "," AND") */
      :new.category_id = category.category_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update recipe_info because category does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_ingredient BEFORE INSERT ON ingredient for each row
-- erwin Builtin Trigger
-- INSERT trigger on ingredient 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* ingredient_info  ingredient on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="000204b7", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="ingredient_id" */
    SELECT count(*) INTO NUMROWS
      FROM ingredient_info
      WHERE
        /* %JoinFKPK(:%New,ingredient_info," = "," AND") */
        :new.ingredient_id = ingredient_info.ingredient_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert ingredient because ingredient_info does not exist.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* recipe_info  ingredient on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_4", FK_COLUMNS="recipe_id" */
    SELECT count(*) INTO NUMROWS
      FROM recipe_info
      WHERE
        /* %JoinFKPK(:%New,recipe_info," = "," AND") */
        :new.recipe_id = recipe_info.recipe_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert ingredient because recipe_info does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_ingredient AFTER UPDATE ON ingredient for each row
-- erwin Builtin Trigger
-- UPDATE trigger on ingredient 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* ingredient_info  ingredient on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00020762", PARENT_OWNER="", PARENT_TABLE="ingredient_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="ingredient_id" */
  SELECT count(*) INTO NUMROWS
    FROM ingredient_info
    WHERE
      /* %JoinFKPK(:%New,ingredient_info," = "," AND") */
      :new.ingredient_id = ingredient_info.ingredient_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update ingredient because ingredient_info does not exist.'
    );
  END IF;

  /* erwin Builtin Trigger */
  /* recipe_info  ingredient on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="ingredient"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_4", FK_COLUMNS="recipe_id" */
  SELECT count(*) INTO NUMROWS
    FROM recipe_info
    WHERE
      /* %JoinFKPK(:%New,recipe_info," = "," AND") */
      :new.recipe_id = recipe_info.recipe_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update ingredient because recipe_info does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_users_recipe BEFORE INSERT ON users_recipe for each row
-- erwin Builtin Trigger
-- INSERT trigger on users_recipe 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* recipe_info  users_recipe on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0001f3f3", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="recipe_id" */
    SELECT count(*) INTO NUMROWS
      FROM recipe_info
      WHERE
        /* %JoinFKPK(:%New,recipe_info," = "," AND") */
        :new.recipe_id = recipe_info.recipe_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert users_recipe because recipe_info does not exist.'
      );
    END IF;

    /* erwin Builtin Trigger */
    /* member  users_recipe on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="member_id" */
    SELECT count(*) INTO NUMROWS
      FROM member
      WHERE
        /* %JoinFKPK(:%New,member," = "," AND") */
        :new.member_id = member.member_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert users_recipe because member does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_users_recipe AFTER UPDATE ON users_recipe for each row
-- erwin Builtin Trigger
-- UPDATE trigger on users_recipe 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* recipe_info  users_recipe on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="000201e7", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="recipe_id" */
  SELECT count(*) INTO NUMROWS
    FROM recipe_info
    WHERE
      /* %JoinFKPK(:%New,recipe_info," = "," AND") */
      :new.recipe_id = recipe_info.recipe_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update users_recipe because recipe_info does not exist.'
    );
  END IF;

  /* erwin Builtin Trigger */
  /* member  users_recipe on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="member"
    CHILD_OWNER="", CHILD_TABLE="users_recipe"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="member_id" */
  SELECT count(*) INTO NUMROWS
    FROM member
    WHERE
      /* %JoinFKPK(:%New,member," = "," AND") */
      :new.member_id = member.member_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update users_recipe because member does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_recipe_procedure BEFORE INSERT ON recipe_procedure for each row
-- erwin Builtin Trigger
-- INSERT trigger on recipe_procedure 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* recipe_info  recipe_procedure on child insert restrict */
    /* ERWIN_RELATION:CHECKSUM="0000ffe8", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="recipe_procedure"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="recipe_id" */
    SELECT count(*) INTO NUMROWS
      FROM recipe_info
      WHERE
        /* %JoinFKPK(:%New,recipe_info," = "," AND") */
        :new.recipe_id = recipe_info.recipe_id;
    IF (
      /* %NotnullFK(:%New," IS NOT NULL AND") */
      
      NUMROWS = 0
    )
    THEN
      raise_application_error(
        -20002,
        'Cannot insert recipe_procedure because recipe_info does not exist.'
      );
    END IF;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_recipe_procedure AFTER UPDATE ON recipe_procedure for each row
-- erwin Builtin Trigger
-- UPDATE trigger on recipe_procedure 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* recipe_info  recipe_procedure on child update restrict */
  /* ERWIN_RELATION:CHECKSUM="0000fd17", PARENT_OWNER="", PARENT_TABLE="recipe_info"
    CHILD_OWNER="", CHILD_TABLE="recipe_procedure"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_24", FK_COLUMNS="recipe_id" */
  SELECT count(*) INTO NUMROWS
    FROM recipe_info
    WHERE
      /* %JoinFKPK(:%New,recipe_info," = "," AND") */
      :new.recipe_id = recipe_info.recipe_id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update recipe_procedure because recipe_info does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/
