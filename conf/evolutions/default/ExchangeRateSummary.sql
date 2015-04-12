
# --- !Ups
DROP TABLE IF EXISTS "exchangeratesummary";
DROP SEQUENCE IF EXISTS "s_exchangeratesummary_id";

create table "exchangeratesummary" (
    "id" bigint primary key not null,
    "name" varchar(128) not null,
    "coderate" varchar(128) not null,
    "minvalue" double precision not null,
    "maxvalue" double precision not null,
    "avgvalue" double precision not null,
    "listall" bigint default null,
    FOREIGN KEY ("listall") references ExchangeRateAll(id)
  );
create sequence "s_exchangeratesummary_id";

# --- !Downs