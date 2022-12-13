drop table test_member;

create table test_meber(
    id varchar2(100) not null,
    passwd varchar2(100),
    name varchar2(100),
    email varchar2(200)
);

ALTER TABLE test_meber ADD CONSTRAINT PK_test_meber PRIMARY KEY (id);

commit;