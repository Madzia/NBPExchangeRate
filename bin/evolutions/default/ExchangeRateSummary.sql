
# --- !Ups
DROP TABLE IF EXISTS "exchangeratesummary";
DROP SEQUENCE IF EXISTS "s_exchangeratesummary_id";

create table "exchangeratesummary" (
    "id" bigint primary key not null,
    "name" varchar(128) not null,
    "minValue" double precision not null,
    "maxValue" double precision not null,
    "avgValue" double precision not null,
    "listAll" varchar references ExchangeRateAll(id)
  );
create sequence "s_exchangeratesummary_id";

# --- !Downs