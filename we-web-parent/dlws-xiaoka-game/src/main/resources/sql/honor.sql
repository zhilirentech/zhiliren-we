/*honor_percen*/
SELECT
	wxname,
	headImgUrl,
	schoolName
FROM
	xiaoka.v2_wx_customer cme
INNER JOIN xiaoka.tg_school_info scl ON cme.tgSchoolId = scl.id
WHERE cme.openId=@openId

/*honor_sign_max*/
SELECT max(signDays)signDays FROM up_sign_days 

WHERE openId=@openId