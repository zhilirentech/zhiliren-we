/*dict_info_list*/
SELECT
  i.`id`,
  i.`typeCode`,
  t.`typeName`,
  i.`dictCode`,
  i.`dictName`,
  i.`description`,
  i.`status`
FROM `dict_info` i INNER JOIN dict_type t
ON i.`typeCode`=t.`typeCode`
$condition

/*dict_info_list_count*/
SELECT COUNT(*) 
FROM `dict_info` i INNER JOIN dict_type t
ON i.`typeCode`=t.`typeCode`
$condition

/*查询关联字典项*/
/*dict_info_relation_target_list*/
SELECT r.`id`,r.`sourceId`,r.`targetId`,info.`typeCode`,t.`typeName`,info.`dictCode`,info.`dictName`  
FROM dict_type t,`dict_info` info,`dict_relation` r
WHERE t.typeCode = info.`typeCode` AND info.`id` = r.`targetId`
AND r.sourceId=@sourceId

/*dict_info_relation_target_list_count*/
SELECT COUNT(*)  
FROM dict_type t,`dict_info` info,`dict_relation` r
WHERE t.typeCode = info.`typeCode` AND info.`id` = r.`targetId`
AND r.sourceId=@sourceId

/*dict_info_findbyid*/
SELECT
  info.`id`,
  info.`typeCode`,
  t.`typeName`,
  info.`dictCode`,
  info.`dictName`,
  info.`description`,
  info.`status`
FROM `dict_info` info ,`dict_type` t
WHERE info.`typeCode`=t.`typeCode`
AND info.`id` = @id