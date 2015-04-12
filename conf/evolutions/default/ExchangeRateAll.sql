
# --- !Ups
DROP TABLE IF EXISTS "exchangeratesummary";
DROP SEQUENCE IF EXISTS "s_exchangeratesummary_id";

DROP TABLE IF EXISTS "exchangerateall";
DROP SEQUENCE IF EXISTS "s_exchangerateall_id";

create table "exchangerateall" (
    "id" bigint primary key not null,
    "name" varchar(128) not null,
    "coderate" varchar(128) not null,
    "date"varchar(128) not null,
    "value" double precision not null
  );
create sequence "s_exchangerateall_id";

# --- !Downs