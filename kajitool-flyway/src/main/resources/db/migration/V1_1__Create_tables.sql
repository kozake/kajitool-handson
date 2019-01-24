create table RECIPE (
    ID bigint not null,
    NAME varchar(100) not null,
    VERSION int not null,
    UPDATED_AT datetime not null,
    PRIMARY KEY (ID)
);

create table RECIPE_DETAIL (
    ID bigint not null,
    RECIPE_ID bigint not null,
    MATERIAL_ID bigint not null,
    QUANTITY int not null,
    PRIMARY KEY (ID),
);

create index I1__RECIPE_DETAIL on RECIPE_DETAIL (RECIPE_ID);

create table MATERIAL (
    ID bigint not null,
    NAME varchar(100) not null,
    PRIMARY KEY (ID)
);

alter table RECIPE_DETAIL
  add constraint FK__RECIPE_DETAIL__RECIPE_ID 
  foreign key (RECIPE_ID) references RECIPE (ID);

alter table RECIPE_DETAIL
  add constraint FK__RECIPE_DETAIL__MATERIAL_ID 
  foreign key (MATERIAL_ID) references MATERIAL (ID);
