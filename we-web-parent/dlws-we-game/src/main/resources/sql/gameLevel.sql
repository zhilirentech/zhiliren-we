/*gameLevel_query_list*/
SELECT
	id,
	levelName,
	levelNo,
	grayIcon,
	brightIcon,
	isDel
FROM
	up_game_level
$condition

/*gameLevel_query_list_count*/
SELECT
	count(*)
FROM
	up_game_level
$condition


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
