# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table brand (
  id                        bigint not null,
  internal_name             varchar(255),
  display_name              varchar(255),
  seo_name                  varchar(255),
  logo_url                  varchar(255),
  alt                       varchar(255),
  active                    boolean,
  description               varchar(10000),
  remarks                   varchar(10000),
  constraint uq_brand_internal_name unique (internal_name),
  constraint pk_brand primary key (id))
;

create table watch (
  id                        bigint not null,
  external_reference        varchar(255),
  key                       varchar(255),
  creation_date             timestamp,
  last_modification_date    timestamp,
  brand_id                  bigint,
  model                     varchar(255),
  additionnal_model_infos   varchar(255),
  reference                 varchar(255),
  serial                    varchar(255),
  serial2                   varchar(255),
  movement                  varchar(255),
  movement_type             varchar(20),
  size                      float,
  strap                     varchar(255),
  additionnal_infos         varchar(10000),
  description               varchar(100000),
  long_description          varchar(10000),
  private_infos             varchar(10000),
  year                      varchar(255),
  has_box                   boolean,
  has_papers                boolean,
  is_new                    boolean,
  owner_status              varchar(17),
  status                    varchar(23),
  condition                 varchar(13),
  delivery_delay            varchar(15),
  selling_price             bigint,
  old_selling_price         bigint,
  list_price                bigint,
  should_display            boolean,
  constraint ck_watch_movement_type check (movement_type in ('MECHANICAL_MANUAL','MECHANICAL_AUTOMATIC','QUARTZ_STANDARD','QUARTZ_SPECIAL','UNKNOWN','RESERVED_1','RESERVED_2')),
  constraint ck_watch_owner_status check (owner_status in ('OWNED_BY_US','OWNED_BY_CUSTOMER','OWNED_BY_PARTNER','UNKNOWN','RESERVED_1','RESERVED_2')),
  constraint ck_watch_status check (status in ('TO_SELL','RESERVED_FOR_A_CUSTOMER','SOLD','SELLING_CANCELED','UNKNOWN','RESERVED_1','RESERVED_2')),
  constraint ck_watch_condition check (condition in ('NEVER_WORN_0','EXCELLENT_1','GOOD_2','USED_3','NOT_WORKING_4','FOR_PARTS_5','UNKWOWN_6')),
  constraint ck_watch_delivery_delay check (delivery_delay in ('IN_STOCK_0','THREE_TO_FIVE_1','SIX_TO_TEN_2','TO_ORDER_3','UNKWOWN')),
  constraint uq_watch_key unique (key),
  constraint pk_watch primary key (id))
;

create sequence brand_seq;

create sequence watch_seq;

alter table watch add constraint fk_watch_brand_1 foreign key (brand_id) references brand (id) on delete restrict on update restrict;
create index ix_watch_brand_1 on watch (brand_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists brand;

drop table if exists watch;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists brand_seq;

drop sequence if exists watch_seq;

