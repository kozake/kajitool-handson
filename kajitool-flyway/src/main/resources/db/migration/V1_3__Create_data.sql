insert into MATERIAL values(1, 'どうのこうせき');
insert into MATERIAL values(2, 'てつのこうせき');
insert into MATERIAL values(3, 'ぎんのこうせき');

insert into RECIPE values(RECIPE__ID_SEQ.NEXTVAL, 'どうのつるぎ', 1, SYSDATE);
insert into RECIPE_DETAIL values(RECIPE_DETAIL__ID_SEQ.NEXTVAL, RECIPE__ID_SEQ.CURRVAL, 1, 3);

insert into RECIPE values(RECIPE__ID_SEQ.NEXTVAL, 'てつのつるぎ', 1, SYSDATE);
insert into RECIPE_DETAIL values(RECIPE_DETAIL__ID_SEQ.NEXTVAL, RECIPE__ID_SEQ.CURRVAL, 1, 2);
insert into RECIPE_DETAIL values(RECIPE_DETAIL__ID_SEQ.NEXTVAL, RECIPE__ID_SEQ.CURRVAL, 2, 3);

insert into RECIPE values(RECIPE__ID_SEQ.NEXTVAL, 'せいどうのつるぎ', 1, SYSDATE);
insert into RECIPE_DETAIL values(RECIPE_DETAIL__ID_SEQ.NEXTVAL, RECIPE__ID_SEQ.CURRVAL, 1, 3);
insert into RECIPE_DETAIL values(RECIPE_DETAIL__ID_SEQ.NEXTVAL, RECIPE__ID_SEQ.CURRVAL, 2, 1);
