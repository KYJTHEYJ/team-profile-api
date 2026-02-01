CREATE TABLE member
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(120) NOT NULL,
    age  INT          NOT NULL,
    mbti VARCHAR(12)  NOT NULL,
    CONSTRAINT pk_member PRIMARY KEY (id)
);