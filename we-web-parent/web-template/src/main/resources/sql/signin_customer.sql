/*signin_list*/
SELECT
	uci.customerName customerName,
	wc.headImgUrl headImgUrl,
	si.openId openId,
	tsi.schoolName schoolName,
	si.inAmount,
	si.type,
	si.signDate signDate,
	si.signTime signTime,
	si.status status,
	si.createTime createTime,
	si.outAmount outAmount
FROM
	up_sign_in si
INNER JOIN up_customer_info uci ON uci.openId = si.openId
INNER JOIN `xiaoka`.v2_wx_customer wc ON wc.openId = si.openId
INNER JOIN `xiaoka`.tg_school_info tsi ON tsi.id = wc.tgSchoolId
$condition

/*signin_list_count*/
SELECT
	COUNT(si.id)
FROM
	up_sign_in si
INNER JOIN up_customer_info uci ON uci.openId = si.openId
INNER JOIN `xiaoka`.v2_wx_customer wc ON wc.openId = si.openId
INNER JOIN `xiaoka`.tg_school_info tsi ON tsi.id = wc.tgSchoolId
$condition
