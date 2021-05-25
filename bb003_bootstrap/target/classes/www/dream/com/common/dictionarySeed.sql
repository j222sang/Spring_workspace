/** truncate 차지하고 있던 공간까지 함께 삭제됩니다. drop은 잘 안됩니다 */
truncate table s_id_seed
drop table s_id_seed;

create table s_id_seed(
	seq_id 		number(19)		primary key,
	seed		char(5)
);

create or replace function get_id(intSeed number) return char
is 
	ret char(5);
begin
	select seed into ret from s_id_seed where seq_id = intSeed;
	return ret;
end;

select get_id(15) from dual;