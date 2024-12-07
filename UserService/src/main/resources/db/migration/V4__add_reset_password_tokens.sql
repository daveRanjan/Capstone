create table if not exists reset_password_token
(
    token      VARCHAR(254)                                               not null,
    user_id    int                                                        null,
    expiray_at TIMESTAMP default (CURRENT_TIMESTAMP + INTERVAL 30 MINUTE) null
);

create unique index reset_password_token_token_uindex
    on reset_password_token (token);
