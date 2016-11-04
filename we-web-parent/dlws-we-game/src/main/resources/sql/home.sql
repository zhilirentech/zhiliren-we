/*home_getCustomer_byId*/
SELECT wc.openId,wc.wxname,wc.tgSchoolId as schoolId,wc.headImgUrl,tsi.cityId as cityId
FROM `xiaoka`.v2_wx_customer wc 
left join `xiaoka`.tg_school_info tsi on wc.tgSchoolId = tsi.id 
where openId = @openId

/*home_getUpCustomer_byOpneId*/
SELECT id,openId,customerName
FROM up_customer_info 
where openId= @openId

/*home_query_CityList*/
select t.id as id,
	   t.cityName as cityName,
	   t.number as number 
from `xiaoka`.tg_city t 
where t.isDelete = 0 
order by t.number asc

/*home_query_schoolByCityId*/
select t.id as id,
	   t.cityId as cityId,
	   t.schoolName as schoolName,
	   t.schoolNumber as schoolNumber 
from `xiaoka`.tg_school_info t 
where t.isDelete = 0 and t.cityId = @cityId

/*home_save_schoolId*/
update `xiaoka`.v2_wx_customer set tgSchoolId=@schoolId where openId=@openId

/*home_querySchool_BySchoolName*/
select t.id as id,
	   t.cityId as cityId,
	   t.schoolName as schoolName,
	   t.schoolNumber as schoolNumber 
from tg_school_info t 
where t.schoolName 
	like "'%"@schoolName"%'" 
	and t.isDelete = 0