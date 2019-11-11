-- 재료 맞춤 레시피 보기
SELECT rin.recipe_id, rname, time, result_img, hits
FROM recipe_info rec, (SELECT distinct recipe_id 
                    FROM ingredient 
                    WHERE ingredient_id IN (1000, 1001)) rin
WHERE rec.recipe_id = rin.recipe_id;

-- 'SNS 인기 레시피' 보기
SELECT *
FROM recipe_info
WHERE category_id = 20
ORDER BY hits;

-- '나만의 레시피' 보기 // 재료는 따로 JDBC에서 해줘야 함
SELECT rname, time, result_img, hits
FROM users_recipe u JOIN recipe_info r ON u.recipe_id = r.recipe_id
ORDER BY hits DESC;

-- 내가 쓴 '나만의 레시피' 목록 보기
select rname, time, result_img, hits
from users_recipe u JOIN recipe_info r ON u.recipe_id = r.recipe_id
where member_id = 1; -- 입력받아오기

-- '기본 레시피' 보기
SELECT *
FROM recipe_info
WHERE category_id = 10
ORDER BY hits;

-- 전체 레시피 보기
SELECT *
FROM recipe_info
WHERE category_id IN (10, 20, 30)
ORDER BY hits;

-- '나만의 레시피' 추가
INSERT INTO recipe_info 
VALUES(rid_sequence.nextval, 30, '쉐하토스트', 10, null, 0);

-- (기본/SNS) 레시피 수정 (추가된 Use Case)
UPDATE recipe_procedure 
SET text = '밀가루와 우유를 섞는다', img_url = '' 
WHERE recipe_id = 100 AND proc_id = 1; -- 계란빵 첫 번째 단계 

-- '나만의 레시피' 수정 (추후에 추가할 것 : 권한 추가)

-- 'SNS 인기 레시피' 검색
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info
WHERE category_id = 20 AND rname = '짜파구리'; -- if) 사용자 입력이 '짜파구리'일 경우

-- '나만의 레시피' 검색
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info
WHERE category_id = 30 AND rname = '%쉐하%';

-- '기본 레시피' 검색
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info 
WHERE category_id = 10 AND rname = '계란빵'; -- if) 사용자 입력이 '계란빵'일 경우 

-- 전체 레시피 검색
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info
WHERE category_id IN (10, 20, 30) AND rname = '간장계란밥' -- if) 사용자 입력이 '간장계란밥'일 경우
ORDER BY hits;




 
