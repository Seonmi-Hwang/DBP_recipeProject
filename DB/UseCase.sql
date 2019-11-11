-- ��� ���� ������ ����
SELECT rin.recipe_id, rname, time, result_img, hits
FROM recipe_info rec, (SELECT distinct recipe_id 
                    FROM ingredient 
                    WHERE ingredient_id IN (1000, 1001)) rin
WHERE rec.recipe_id = rin.recipe_id;

-- 'SNS �α� ������' ����
SELECT *
FROM recipe_info
WHERE category_id = 20
ORDER BY hits;

-- '������ ������' ���� // ���� ���� JDBC���� ����� ��
SELECT rname, time, result_img, hits
FROM users_recipe u JOIN recipe_info r ON u.recipe_id = r.recipe_id
ORDER BY hits DESC;

-- ���� �� '������ ������' ��� ����
select rname, time, result_img, hits
from users_recipe u JOIN recipe_info r ON u.recipe_id = r.recipe_id
where member_id = 1; -- �Է¹޾ƿ���

-- '�⺻ ������' ����
SELECT *
FROM recipe_info
WHERE category_id = 10
ORDER BY hits;

-- ��ü ������ ����
SELECT *
FROM recipe_info
WHERE category_id IN (10, 20, 30)
ORDER BY hits;

-- '������ ������' �߰�
INSERT INTO recipe_info 
VALUES(rid_sequence.nextval, 30, '�����佺Ʈ', 10, null, 0);

-- (�⺻/SNS) ������ ���� (�߰��� Use Case)
UPDATE recipe_procedure 
SET text = '�а���� ������ ���´�', img_url = '' 
WHERE recipe_id = 100 AND proc_id = 1; -- ����� ù ��° �ܰ� 

-- '������ ������' ���� (���Ŀ� �߰��� �� : ���� �߰�)

-- 'SNS �α� ������' �˻�
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info
WHERE category_id = 20 AND rname = '¥�ı���'; -- if) ����� �Է��� '¥�ı���'�� ���

-- '������ ������' �˻�
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info
WHERE category_id = 30 AND rname = '%����%';

-- '�⺻ ������' �˻�
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info 
WHERE category_id = 10 AND rname = '�����'; -- if) ����� �Է��� '�����'�� ��� 

-- ��ü ������ �˻�
SELECT recipe_id, rname, time, result_img, hits
FROM recipe_info
WHERE category_id IN (10, 20, 30) AND rname = '��������' -- if) ����� �Է��� '��������'�� ���
ORDER BY hits;




 
