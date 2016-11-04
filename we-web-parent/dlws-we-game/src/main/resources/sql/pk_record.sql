/*pk_record_list*/
SELECT
	ug.id,
	ug.founder,
	tsi.schoolName,
	uci.customerName,
	ug.money,
	ug.createTime,
	ug.`status`
FROM
	up_game ug
LEFT JOIN `xiaoka`.v2_wx_customer wc ON ug.founder = wc.openId
LEFT JOIN `xiaoka`.tg_school_info tsi ON tsi.id = wc.tgSchoolId
LEFT JOIN up_customer_info uci on uci.openId = ug.founder
$condition

/*pk_record_list_count*/
SELECT
	COUNT(*)
FROM
	up_game ug
LEFT JOIN `xiaoka`.v2_wx_customer wc ON ug.founder = wc.openId
LEFT JOIN `xiaoka`.tg_school_info tsi ON tsi.id = wc.tgSchoolId
LEFT JOIN up_customer_info uci on uci.openId = ug.founder
$condition