/*ranking_list*/
SELECT MAX(sid.signDays) signDays,cme.headImgUrl,cme.wxname,scl.schoolName FROM up_sign_days sid INNER  JOIN xiaoka.v2_wx_customer cme ON
sid.openId=cme.openId INNER JOIN xiaoka.tg_school_info scl ON scl.id=cme.tgSchoolId 
GROUP BY cme.openId ORDER BY signDays DESC 


/*ranking_list_count*/
SELECT count(*) FROM up_sign_days sid INNER  JOIN xiaoka.v2_wx_customer cme ON
sid.openId=cme.openId INNER JOIN xiaoka.tg_school_info scl ON scl.id=cme.tgSchoolId 
ORDER BY sid.signDays desc

/*ranking_RenCount*/
SELECT count(*)count from ( SELECT count(*) FROM up_sign_days sid INNER  JOIN xiaoka.v2_wx_customer cme ON
sid.openId=cme.openId INNER JOIN xiaoka.tg_school_info scl ON scl.id=cme.tgSchoolId 
GROUP BY cme.openId ) as total
