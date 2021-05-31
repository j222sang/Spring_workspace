create table s_post(
   id				varchar2(4000)	primary key,	
   board_id			number(9)		REFERENCES s_board(id),
   writer_id		varchar2(10)	REFERENCES s_party(user_id),
   title			varchar2(100),

);

