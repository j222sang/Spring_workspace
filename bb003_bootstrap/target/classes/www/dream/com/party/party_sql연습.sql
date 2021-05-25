select * 
  from s_party;

-- 카티션 프로덕트. 테이블에 들어있는 정보 건수의 곱하기로 건수가 만들어져...가치 없는 것
select * 
  from s_party p, s_contact_point cp;
--join 
select * 
  from s_party p, s_contact_point cp
 where p.user_id = cp.user_id;
--outer
select * 
  from s_party p, s_contact_point cp
 where p.user_id = cp.user_id(+);
 
select * 
  from s_party p left outer join s_contact_point cp on p.user_id = cp.user_id;
 
--회장님의 정보와 연락처 정보를 조회
select name, birth_dt, sex, enabled, descrim, contact_point_type, info
  from s_party p left outer join s_contact_point cp on p.user_id = cp.user_id
 where p.user_id='admin';
