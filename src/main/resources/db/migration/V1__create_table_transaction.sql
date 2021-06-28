CREATE TABLE tb_transaction(
    id serial not null primary key,
    transaction_identifier varchar unique not null,
    transaction_value float not null,
    credit_card_number varchar not null,
    establishment_name varchar not null,
    transaction_date timestamp not null
);