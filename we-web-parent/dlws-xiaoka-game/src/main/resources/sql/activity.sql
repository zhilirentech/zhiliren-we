/*activityHot_list*/
SELECT
id,
title,
content,
cover,
STATUS,
createTime,
endTime,
NOW()
FROM
	up_activity
WHERE
	NOW()>= createTime
AND NOW()<= endTime
AND isDel=1

/*activityHis_list*/
SELECT
id,
title,
content,
cover,
STATUS,
createTime,
endTime,
NOW()
FROM
	up_activity
WHERE
	NOW()>endTime
AND isDel=1
AND  id not IN(SELECT
	id
FROM
	up_activity
WHERE
	NOW()>= createTime
and NOW()<= endTime
AND isDel=1)

/*activityHis_list_count*/
SELECT
count(*)
FROM
	up_activity
WHERE
	NOW()>endTime
AND isDel=1
AND  id not IN(SELECT
	id
	
FROM
	up_activity
WHERE
	NOW()>= createTime
and NOW()<= endTime
AND isDel=1)