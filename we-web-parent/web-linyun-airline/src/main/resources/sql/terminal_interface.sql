/*terminal_device_pay*/
SELECT
  d.`id`,
  d.`salesAllotId`,
  d.`deviceNo`,
  d.`deliverTime`,
  d.`wareHouse`,
  d.`area`,
  d.`useStatus`,
  d.`payStatus`,
  d.`firmVersion`,
  de.`id` enableId
FROM `device` d,`device_enable` de
WHERE d.`id` = de.`deviceId`
AND d.`deviceNo`= @deviceNo
AND de.`status`=@enableStatus

/*terminal_find_retail_by_device*/
SELECT m.`id`,m.`no`,m.`name`,m.`address`,m.`adminName`,m.`mobile`,m.`createTime`,m.`retailerType`
FROM
`merchant` m,`device_enable` de
WHERE m.`id`=de.`merchantId`
AND de.`deviceId`= @deviceId
AND de.`status`=@enableStatus

/*terminal_find_enable_record_bydeviceId*/
SELECT
   de.`deviceId`,
   de.`merchantId`,
   de.payRecordId ,
   m.`no` retailerId,
   m.name merchantName
FROM `device_enable` de, `merchant` m
WHERE de.`merchantId`=m.`id`
AND de.`deviceId`=@deviceId 
AND de.`status`= @status

/*terminal_find_enable_record_byDeviceIds*/
SELECT
  de.`deviceId`,
  de.`merchantId`,
  d.`deviceNo`,
  m.`no` retailNo
FROM `device_enable` de, `merchant` m,device d
WHERE de.`merchantId`=m.`id`
AND de.`deviceId`=d.`id`
AND de.`status`= @status
AND de.`deviceId` IN (@deviceIds)