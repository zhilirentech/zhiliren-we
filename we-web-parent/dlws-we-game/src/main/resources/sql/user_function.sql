/*user_function_all*/
SELECT
  `id`,
  `parentId`,
  `name`,
  `url`,
  `level`,
  `remark`,
  `createTime`,
  `updateTime`,
   sort
FROM `s_function`
WHERE id IN (SELECT rm.`functionId` FROM 
`s_user_role_map` ur,`s_role_function_map` rm
WHERE ur.`roleId`=rm.`roleId`
AND ur.`userId`=@userId)