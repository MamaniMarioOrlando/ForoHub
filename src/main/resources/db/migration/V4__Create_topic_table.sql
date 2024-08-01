CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    user_id BIGINT,
    curse_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (curse_id) REFERENCES courses(id) ON DELETE SET NULL
);