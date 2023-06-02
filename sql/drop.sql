CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    mail VARCHAR(255) NOT NULL
);

CREATE TABLE Complaints (
    complaint_id SERIAL PRIMARY KEY,
    author_id INTEGER NOT NULL REFERENCES users(user_id),
    text TEXT NOT NULL
);

CREATE TABLE Reviews (
    review_id SERIAL PRIMARY KEY,
    reactions TEXT,
    author_of_review_id INTEGER,
    FOREIGN KEY (author_of_review_id) REFERENCES users (user_id)
);

CREATE TABLE Literature (
    literature_id SERIAL PRIMARY KEY,
    title TEXT,
    author_id INTEGER,
    FOREIGN KEY (author_id) REFERENCES users (user_id),
    review_id INTEGER,
    FOREIGN KEY (review_id) REFERENCES reviews (review_id),
    text TEXT,
    amount_of_reads INTEGER,
    adding_time VARCHAR(255) NOT NULL
);

create table user_literature
(
  user_literature_id SERIAL PRIMARY KEY,
  user_id INT,
  literature_id INT,
  CONSTRAINT FK_users FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT FK_literature FOREIGN KEY (literature_id) REFERENCES literature (literature_id)
);

CREATE TABLE tags (
    tag_id SERIAL PRIMARY KEY,
    tag_name VARCHAR(255)
);