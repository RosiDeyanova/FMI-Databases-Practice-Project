INSERT INTO FN71924.BOOKSTORE (NAME, ADDRESS) VALUES ('Sofia       ', 'Atanas Manchev 1');
INSERT INTO FN71924.BOOKSTORE  (NAME, ADDRESS) VALUES ('Plovdiv              ', 'Hristo Botev 10');
INSERT INTO FN71924.BOOKSTORE  (NAME, ADDRESS) VALUES ('Pazardzhik            ', 'Ivan Vasov 5');
INSERT INTO FN71924.BOOKSTORE  (NAME, ADDRESS) VALUES ('Burgas               ', 'Yanko Komitov 6');

INSERT INTO FN71924.CLIENT (CARD_NUMBER, CLIENT_NAME ,CLIENT_PHONE_NUMBER, EMAIL, BOOKSTORE_NAME) VALUES ('100001', 'Nikolai Ivanov', '0886754326', 'nikivan15@abv.bg', 'Sofia' );
INSERT INTO FN71924.CLIENT (CARD_NUMBER, CLIENT_NAME ,CLIENT_PHONE_NUMBER, EMAIL, BOOKSTORE_NAME) VALUES ('100002', 'Petya Georgieva', '0887457345' ,'p.georgieva1986@abv.bg', 'Sofia ');
INSERT INTO FN71924.CLIENT (CARD_NUMBER, CLIENT_NAME ,CLIENT_PHONE_NUMBER, EMAIL, BOOKSTORE_NAME) VALUES ('100003', 'Galina Gencheva', '0885670943' ,'galena1@abv.bg', 'Pazardzhik ');

INSERT INTO FN71924.STAFF (STAFF_NUMBER, NAME, STAFF_PHONE_NUMBER , BOOKSTORE_NAME) VALUES (1, 'Hristo Botev', '0879563546', 'Sofia ');
INSERT INTO FN71924.STAFF (STAFF_NUMBER, NAME, STAFF_PHONE_NUMBER , BOOKSTORE_NAME) VALUES (2, 'Evgeni Minchev', '0886402564', 'Pazardzhik  ');

INSERT INTO F71924.BOOK (SERIAL_NUMBER,BOOK_NAME,DISCOUNT,RATING,SUMMARY,PRICE,INVENTORY_LEFT,PUBLISHING_HOUSE,AUTHOR_NAME,GENRE,PUBLISHING_YEAR,CLIENT_NUMBER) VALUES ('100', 'Hunger Games', '10', '3.2', 'The Hunger Games is a 2008 dystopian novel by the American writer Suzanne Collins.', '22.50', '12,'Scholastic Press','Suzanne Collins', 'Utopian', '2008','100001');
INSERT INTO F71924.BOOK (SERIAL_NUMBER,BOOK_NAME,DISCOUNT,RATING,SUMMARY,PRICE,INVENTORY_LEFT,PUBLISHING_HOUSE,AUTHOR_NAME,GENRE,PUBLISHING_YEAR,CLIENT_NUMBER) VALUES ('101', 'Under the yoke', '30', '4.9', 'The plot follows the story of Boycho Ognyanov, who, having escaped from a prison in Diarbekir, returns to the Bulgarian town of Byala Cherkva to take part in the rebellion', '19.20', '43,'William Heinemann','Ivan Vazov', 'Historical', '1894','100003');
CREATE VIEW DATA
    AS
      SELECT C.NAME, B.NAME, B.PRICE
      FROM BOOK B, CLIENT C
      WHERE C.CARD_NUMBER = F.CLIENT_NUMBER;


SELECT * FROM DATA;


CREATE VIEW DATA2
    AS
      SELECT S.NAME, S.STAFF_NUMBER
      FROM STAFF S, BOOKSTORE B
      WHERE S.BOOKSTORE_NAME = B.NAME;

SELECT * FROM DATA2;
