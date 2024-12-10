CREATE TABLE IF NOT EXISTS notifications
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    recipient VARCHAR(255) NOT NULL,
    subject   VARCHAR(255) NOT NULL,
    message   TEXT         NOT NULL,
    channel   VARCHAR(20)  NOT NULL,
    purpose   VARCHAR(50)  NOT NULL,
    context   TEXT         NOT NULL,
    status    VARCHAR(20)  NOT NULL
);
