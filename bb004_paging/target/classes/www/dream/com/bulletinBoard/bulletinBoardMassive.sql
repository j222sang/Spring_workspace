select *
  from s_post;

insert into s_post(id, board_id, writer_id, title, content)
select get_id(seq_post_id.nextval), board_id, writer_id, title, content
  from s_post

select p.* , rownum
  from s_post p order by id desc;
  
select * 
  from s_post order by id || 'rrr';

select p.* , rownum
  from s_post p 
 where rownum > 10 and rownum<=20
   order by id desc;

select *   
  from (
  	--인라인 뷰
	select p.* , rownum rn
  	  from s_post p 
	 where rownum<=20
      order by id desc) p	--인라인뷰의 result set
 where rn > 10;

--위의 결과의 표준
limit 10 offset 10;

인덱스가 있는 컬럼을 그대로 사용하면 DB가 인덱스를 활용해 주는데
그 값을 강제적으로 바꾸면 인덱스를 활용하지 못하여 내부적으로
모든 데이터를 읽고 DB안의 메모리에서 정렬을 완료한다.
이 때 엄청난 시간이 걸린다.