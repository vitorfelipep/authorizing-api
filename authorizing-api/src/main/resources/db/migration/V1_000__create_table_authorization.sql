CREATE TABLE IF NOT EXISTS authorizations (
    id BINARY(16) NOT NULL primary key,
    card_number BIGINT(20) NOT NULL,
    transaction_value DECIMAL(15,2) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;