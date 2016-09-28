/*instal_messge_list*/
SELECT
  t1.title,
  t1.content,
  t1.createTime,
  t1.id messageId,
  t1.status,
  t2.id,
  t2.readTime
FROM
	up_message t1
LEFT JOIN 
	site_message_read t2
ON t1.id = t2.messageId
where (t1.status=1 AND t1.openId IS null) or (t1.status=1 AND t1.openId = @openId)
GROUP BY t1.id
ORDER BY t1.createTime desc 

/*instal_messge_list_count*/
SELECT
count(*)
FROM
	up_message t1
LEFT JOIN 
	site_message_read t2
ON t1.id = t2.messageId
where (t1.status=1 AND t1.openId == null) or (t1.status=1 AND t1.openId == @openId)
GROUP BY t1.id
ORDER BY t1.createTime desc 

/*messageReadArealy*/
SELECT id,openId,messageId
FROM site_message_read 
WHERE openId=@openId

AND messageId=@messageId
