
# --- !Ups
DROP TABLE IF EXISTS "exchangerateall";
DROP SEQUENCE IF EXISTS "s_exchangerateall_id";

create table "exchangerateall" (
    "id" bigint foreign key not null,
    "name" varchar(128) not null,
    "date" date not null,
    "value" double precision not null
  );
create sequence "s_exchangerateall_id";

# --- !Downs