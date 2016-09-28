/*account_list*/
SELECT
	uci.customerName customerName,
	tsi.schoolName schoolName,
	ua.openId openId,
	ua.money money,
	ua.balance balance,
	ua.platType platType,
	ua.type type,
	ua.createTime createTime,
	ua.remark remark 
FROM
	up_account ua
INNER JOIN up_customer_info uci ON uci.openId = ua.openId
INNER JOIN `xiaoka`.v2_wx_customer wc ON wc.openId = ua.openId
INNER JOIN `xiaoka`.tg_school_info tsi ON tsi.id = wc.tgSchoolId
$condition

/*account_list_count*/
SELECT
	count(ua.id)
FROM
	up_account ua
INNER JOIN up_customer_info uci ON uci.openId = ua.openId
INNER JOIN `xiaoka`.v2_wx_customer wc ON wc.openId = ua.openId
INNER JOIN `xiaoka`.tg_school_info tsi ON tsi.id = wc.tgSchoolId
$condition