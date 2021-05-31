select *
  from s_post;

insert into s_post(id, board_id, writer_id, title, content);			 
select get_id(seq_post_id.nextval), board_id, writer_id, title, content
  from s_post;
  
select *
  from s_post order by id;

  select *
  from s_post order by id || 'rrr';
  
  