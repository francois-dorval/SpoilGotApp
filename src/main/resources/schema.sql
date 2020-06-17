DROP TABLE IF EXISTS GotCharacterBack;

CREATE TABLE GotCharacterBack (
  id INT  DEFAULT NULL,
  name VARCHAR(250) NOT NULL,
  killedby INT DEFAULT NULL,
  killedinseason VARCHAR(250) DEFAULT NULL
);
