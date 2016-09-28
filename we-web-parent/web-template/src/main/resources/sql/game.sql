/*sign_in_inner_game_list*/
SELECT
	COUNT(s.id) num,
	g.id id,
	g.name name,
	g.date date,
	g.beginTime beginTime,
	g.endTime endTime,
	g.money money,
	g.status status,
	g.founder founder,
	g.createTime createTime,
	s.inAmount,
	s.outAmount,
	s.signTime
FROM
	up_game g
INNER JOIN up_sign_in s ON s.gameId = g.id
$condition

/*sign_is_partic*/
SELECT
	id,openId,inAmount,outAmount,signDate,signTime,status,type,gameId,dividedStatus,createTime
FROM
	up_sign_in usi
WHERE
	usi.signDate = @gameDate
AND usi.openId = @openId
AND usi.gameId = @gameId
	