CREATE TABLE members
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(120)          NOT NULL,
    age  INT                   NOT NULL,
    mbti VARCHAR(12)           NOT NULL,
    CONSTRAINT pk_members PRIMARY KEY (id)
);

CREATE TABLE images
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    member_id BIGINT                NOT NULL,
    image_key VARCHAR(4096)         NOT NULL,
    CONSTRAINT pk_images PRIMARY KEY (id)
);

ALTER TABLE images
    ADD CONSTRAINT uc_images_member UNIQUE (member_id);

ALTER TABLE images
    ADD CONSTRAINT FK_IMAGES_ON_MEMBER FOREIGN KEY (member_id) REFERENCES members (id);