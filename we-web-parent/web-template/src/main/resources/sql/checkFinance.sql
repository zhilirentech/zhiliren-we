/*withdraw_application_list*/
SELECT
	ap.id,
	ap.money,
	ap.checkStatus,
	ap.checkInfo,
	ci.customerName
FROM
	up_withdraw_application ap
LEFT JOIN up_customer_info ci ON ci.openId = ap.openId
$condition

/*withdraw_application_count*/
SELECT
	count(*)
FROM
	up_withdraw_application ap
LEFT JOIN up_customer_info ci ON ci.openId = ap.openId
$condition

/*withdraw_application_by_id*/
SELECT
	uwa.id,
	uwa.money,
	uwa.checkStatus,
	uwa.checkInfo,
	uwa.openId,
	uci.customerName
FROM
	up_withdraw_application uwa
LEFT JOIN up_customer_info uci ON uci.openId = uwa.openId
where uwa.id=@uId

/*getuser_balance*/
SELECT
	ua.id,ua.openId,ua.money,ua.balance,ua.platType,ua.type,ua.createTime,ua.remark
FROM
	up_account ua
WHERE
 ua.openId = @openId
and
 ua.createTime = (select Max(createTime) from up_account a where a.openId = @openId )
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
