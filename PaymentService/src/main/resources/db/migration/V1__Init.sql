CREATE TABLE IF NOT EXISTS payments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          order_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          amount BIGINT NOT NULL,
                          payment_link VARCHAR(255) NOT NULL,
                          status VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP NOT NULL
);