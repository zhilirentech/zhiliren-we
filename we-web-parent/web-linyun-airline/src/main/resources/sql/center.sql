/*center_getCustomer_byId*/
SELECT
	uci.customerName,si.schoolName,ua.balance,wc.headImgUrl
FROM
	up_customer_info uci
LEFT JOIN `xiaoka`.v2_wx_customer wc ON wc.openId = uci.openId
LEFT JOIN `xiaoka`.tg_school_info si on si.id = wc.tgSchoolId
LEFT JOIN up_account ua on ua.openId = uci.openId
where  uci.openId = @openId 
and ua.createTime = (
 select Max(a.createTime) from up_account a where a.openId=@openId
		)
/*center_noCheck_money*/
select sum(uaa.money) as money 
from up_withdraw_application uaa  
where uaa.checkStatus = 0 and openId = @openId 
		
/*center_account_list*/
SELECT
	id,
	openId,
	money,
	balance,
	platType,
	type,
	DATE_FORMAT(createTime,"%Y-%m-%d") as createTime,
	remark
FROM up_account
$condition

/*center_account_list_count*/
SELECT
	COUNT(*)
FROM up_account
$condition

/*center_banner_list*/
select id,title,imgUrl,type,sort,status,isDel
from up_banner
where 
type=@type and isDel = @isDel

