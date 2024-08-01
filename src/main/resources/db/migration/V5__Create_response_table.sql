CREATE TABLE responses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    topic_id BIGINT,
    creation_date DATETIME NOT NULL,
    author_id BIGINT,
    solution BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE SET NULL,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE SET NULL
);