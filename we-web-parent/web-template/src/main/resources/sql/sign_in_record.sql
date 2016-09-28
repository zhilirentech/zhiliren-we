/*sign_in_record_list*/
SELECT
	ci.customerName,
	si.inAmount,
	si.outAmount,
	si.signTime,
	si.status,
	ug.name
FROM
	up_sign_in si
LEFT JOIN up_customer_info ci ON ci.openId = si.openId
left join up_game ug on ug.id = si.gameId
$condition

/*sign_in_list_count*/
SELECT
	count(*)
FROM
	up_sign_in si
LEFT JOIN up_customer_info ci ON ci.openId = si.openId
left join up_game ug on ug.id = si.gameId
$condition
/*sign_in_total_money*/
SELECT 
	SUM(inAmount) as total
FROM 
	up_sign_in
$condition