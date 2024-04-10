CREATE TABLE book (
  id BIGSERIAL PRIMARY KEY,
  title varchar(255),
  author varchar(255)
);

INSERT INTO book VALUES (10, 'WRATH', 'John Wrathful');