/*function_manage_list*/
SELECT
  s.`id`,
  s.`parentId`,
  s.level,
  p.`name` parentName ,
  s.`name`,
  s.`url`,
  s.`remark`,
  s.`createTime`,
  s.`updateTime`,
  s.sort
FROM `s_function` s LEFT JOIN s_function p
ON s.`parentId`=p.`id`
$condition

/*function_manage_list_count*/
SELECT COUNT(*) 
FROM `s_function` s LEFT JOIN s_function p
ON s.`parentId`=p.`id`
$condition