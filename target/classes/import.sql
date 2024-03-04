insert into Player(id,email,password,score,username,role) values (101,'papri@test.de','$2a$10$pvlf7y04Qz9wqUM48RlhLOzoOr2OHQ.HKIj8/WuOyFp3O.wNJeA2y',12,'Papri','user');
insert into Player(id,email,password,score,username,role) values (104,'warenik@test.de','$2a$10$31cFufhBohIj3S0SOJ3/zuc44IJxUCEst/x8GjKUznJyVvLYx9lXy',12,'Warenik','user');
insert into Player(id,email,password,score,username,role) values (105,'helen@test.de','$2a$10$NZ1jOk9a4lpRRKcWCeK.B.TVbq//AywaPvPBfNQhShg7TegxHNiqm',12,'Helen','user');
insert into Player(id,email,password,score,username,role) values (102,'basket@test.de','$2a$10$Boa44y9LQ2HQl0nUMnbZLO5PoVJoBmrpO6gIiBJtXBAP7gOBEB7B2',18,'Basket','user');
insert into Player(id,email,password,score,username,role) values (103,'strawt@test.de','$2a$10$vPMWQ2qa.X0STXJiR3uc0eAJfz/uY7hJO3cH./ZXY7QwkKBZa8HFa',20,'Straw','user');
insert into Player(id,email,password,score,username,role) values (106,'user@test.de','$2a$10$oKp9YGFFXW8MQ4MqHpajveATXqPrHe8OkHWbxaVDh/af0.YaWFzCC',50,'user','user'); --Passwort:user
insert into Player(id,email,password,score,username,role) values (107,'admin@test.de','$2a$10$TnQ55Ax3qxnkzdXOEgRKbuDS3NCrdimWR8bGzLcPLl4b3psOcTONa',10,'admin','admin'); --Passwort:admin

--Beispiel für korrekte Ausgabe
-- insert into quiz_table (id, topic ) values (1, 'ANIMALS');
-- insert into questions_table(id, description, points, quiz_id) values (100,'Wie heißt die giftigste Spinne am Amazonas?',3,1 );
-- insert into selections_table(id, selectiontext, isanswer,question_id) VALUES (200, 'Brasilianische Wanderspinne', 1,1 );
-- insert into selections_table(id, selectiontext, isanswer,question_id) VALUES (201, 'Bananenspinne', 0,1 );
-- insert into selections_table(id, selectiontext, isanswer,question_id) VALUES (202, 'Vogelspinne', 0,1 );
-- insert into selections_table(id, selectiontext, isanswer,question_id) VALUES (203, 'Schwarze Witwe', 0,1 );

insert into quiz_table(id,topic,question,answer,points) values (300,'ANIMALS','Wie heißt die giftigste Spinne am Amazonas?','Brasilianische Wanderspinne',2);
insert into quiz_table(id,topic,question,answer,points) values (301,'ANIMALS','Mit wem ist das Okapi verwandt?','Mit der Giraffe',4);
insert into quiz_table(id,topic,question,answer,points) values (302,'ANIMALS','Warum wechselt ein Chamäleon seine Farben?','Zur Tarnung',1);
insert into quiz_table(id,topic,question,answer,points) values (303,'ANIMALS','Was ist eine Koralle?','Ein Tier',1);
insert into quiz_table(id,topic,question,answer,points) values (304,'ANIMALS','Womit ist der Schnabel des Tukans gefüllt?','Mit Luft',2);
insert into quiz_table(id,topic,question,answer,points) values (305,'ANIMALS','Womit riechen Schlangen?','Mit der gespaltenen Zunge',2);
insert into quiz_table(id,topic,question,answer,points) values (306,'ANIMALS','Was ist ein Aguti?','Ein Nagetier',2);
insert into quiz_table(id,topic,question,answer,points) values (307,'ANIMALS','Welches Tier gibt es wirklich?','Aguti',2);
insert into quiz_table(id,topic,question,answer,points) values (308,'ANIMALS','Was können Kängurus nicht?','Rückwerts hüpfen',1);
insert into quiz_table(id,topic,question,answer,points) values (309,'ANIMALS','Welcher Vogel legt seine Eier in fremde Nester?','Kuckuck',4);

insert into quiz_table(id,topic,question,answer,points) values (310,'MUSIC','Wie lautet der zweite Vorname von Elvis Presley?','Aaron',4);
insert into quiz_table(id,topic,question,answer,points) values (311,'MUSIC','Wie viele Saiten hat eine Mandoline?','Acht Saiten',2);
insert into quiz_table(id,topic,question,answer,points) values (312,'MUSIC','Wie viele Saiten hat eine Geige?','Vier Saiten',1);
insert into quiz_table(id,topic,question,answer,points) values (313,'MUSIC','Was versteht man unter Rap-Musik?','Sprechgesang',2);
insert into quiz_table(id,topic,question,answer,points) values (314,'MUSIC','Wie viele Tasten besitzt ein Standard-Klavier?','88',4);
insert into quiz_table(id,topic,question,answer,points) values (315,'MUSIC','Welches Musikinstrument stammt aus Schottland?','Dudelsack',1);
insert into quiz_table(id,topic,question,answer,points) values (316,'MUSIC','Wer galt als erfolgreichster Rock ‘n’ Roll Musiker aller Zeiten?','Elvis Presley',2);
insert into quiz_table(id,topic,question,answer,points) values (317,'MUSIC','Wo liegt der Ursprung des Gongs?','Asien',2);
insert into quiz_table(id,topic,question,answer,points) values (318,'MUSIC','Wie spielt man eine Harfe?','Durch zupfen',1);
insert into quiz_table(id,topic,question,answer,points) values (319,'MUSIC','An welches Instrument erinnert die Mandoline?','Gitarre',4);


insert into quiz_table(id,topic,question,answer,points) values (320,'MOVIES','Wie lautet die Hausnummer der Simpsons?','742',4);
insert into quiz_table(id,topic,question,answer,points) values (321,'MOVIES','Wie heißt der kleine Drache in dem Zeichentrickfilm Mulan?','Mushu',2);
insert into quiz_table(id,topic,question,answer,points) values (322,'MOVIES','Welchen Beruf übt Popeye aus?','Seemann',1);
insert into quiz_table(id,topic,question,answer,points) values (323,'MOVIES','Wer spielte 1983 die Hauptrolle in dem Film Scarface?','Al Pacino',4);
insert into quiz_table(id,topic,question,answer,points) values (324,'MOVIES','Wer ist der Regisseur von Akte X?','Rob Bowman',4);
insert into quiz_table(id,topic,question,answer,points) values (325,'MOVIES','Wer spielte die Rolle des Peter Pan in der Peter-Pan-Verfilmung?','Robin Williams',4);
insert into quiz_table(id,topic,question,answer,points) values (326,'MOVIES','Welche Nummer steht auf Herbie, dem Beatle?','53',4);
insert into quiz_table(id,topic,question,answer,points) values (327,'MOVIES','Wie viele Oscars hat der Film Titanic bekommen?','11',2);
insert into quiz_table(id,topic,question,answer,points) values (328,'MOVIES','Wie heißt der glatzköpfige Kommandant der Enterprise in Star Trek?','Captain Picard',2);
insert into quiz_table(id,topic,question,answer,points) values (329,'MOVIES','Wer war Mozarts großer Rivale im Amadeus-Film?','Antonio Salieri',4);

insert into quiz_table(id,topic,question,answer,points) values (330,'HISTORY','Präsident welchen Landes war Nelson Mandela?','Südafrika',2);
insert into quiz_table(id,topic,question,answer,points) values (331,'HISTORY','Wann begann der Bau der Berliner Mauer?','1961',4);
insert into quiz_table(id,topic,question,answer,points) values (332,'HISTORY','Was fand 2004 in der Ukraine statt? ','Orangene Revolution',1);
insert into quiz_table(id,topic,question,answer,points) values (333,'HISTORY','Wer schrieb „Faust“?','Johann Wolfgang von Goethe',1);
insert into quiz_table(id,topic,question,answer,points) values (334,'HISTORY','Wofür steht der Stoizismus?','Tugend und Selbstbeherrschung',2);
insert into quiz_table(id,topic,question,answer,points) values (335,'HISTORY','Wie heißt die Hauptstadt Lettlands?','Riga',2);
insert into quiz_table(id,topic,question,answer,points) values (336,'HISTORY','Was begann 2010 in Tunesien?','Der „Arabische Frühling“',2);
insert into quiz_table(id,topic,question,answer,points) values (337,'HISTORY','Wo steht das weltbekannte Centre Pompidou?','Paris',2);
insert into quiz_table(id,topic,question,answer,points) values (338,'HISTORY','Wen zeichnete Albert Uderzo?','Asterix und Obelix',1);
insert into quiz_table(id,topic,question,answer,points) values (339,'HISTORY','Was fand NICHT an einem 09. November statt?','Bedingungslose Kapitulation des „Dritten Reichs“ ',4);


--insert into selection_table(id,selection,question,answer,points) values (329,'HISTORY','Weelwcheer Vogel legt seine Eier in fremde Nester?','Kuckuck',4);