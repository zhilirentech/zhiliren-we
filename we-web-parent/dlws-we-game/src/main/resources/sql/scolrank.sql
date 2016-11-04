/*scolrank_list*/
SELECT
	sgi.type,
	sgi.signDate,
	date_format(min(signTime), '%h:%i:%s')signTime,   
	cme.wxname,
  cme.openId,
	cme.headImgUrl,
	scl.id,
	scl.schoolName
FROM
	up_sign_in sgi
INNER JOIN xiaoka.v2_wx_customer cme ON sgi.openId = cme.openId
INNER JOIN xiaoka.tg_school_info scl ON scl.id = cme.tgSchoolId
WHERE
	sgi.type = 0 AND sgi.status=1 AND scl.id=(SELECT tgSchoolId FROM xiaoka.v2_wx_customer WHERE openId=@openId) 
AND date_format(signDate, '%Y-%m-%d')= date_format(now(), '%Y-%m-%d')
 GROUP BY cme.openId
ORDER BY
	min(signTime) ASC
	
/*scolrank_list_count*/
SELECT
	count(*)
FROM
	up_sign_in sgi
INNER JOIN xiaoka.v2_wx_customer cme ON sgi.openId = cme.openId
INNER JOIN xiaoka.tg_school_info scl ON scl.id = cme.tgSchoolId
WHERE
	sgi.type = 0 AND sgi.status=1 AND scl.id=(SELECT tgSchoolId FROM xiaoka.v2_wx_customer WHERE openId=@openId) 
AND date_format(signDate, '%Y-%m-%d')= date_format(now(), '%Y-%m-%d')
 GROUP BY cme.openId
ORDER BY
	min(signTime) ASC	
	
	
	