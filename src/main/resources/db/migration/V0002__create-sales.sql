CREATE TABLE IF NOT EXISTS sales (
    id                  uuid                  primary key             default uuid_generate_v4()        ,
    id_vehicle          uuid                  not null                                                  ,
    document            varchar(11)           not null                                                  ,
    sale_date           date                  not null                                                  ,
    payment_status      varchar(255)          not null                                                  ,
    payment_code        uuid                  not null
);