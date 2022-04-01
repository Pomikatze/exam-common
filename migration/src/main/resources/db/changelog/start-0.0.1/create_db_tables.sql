CREATE TABLE public."user"
(
    id bigserial NOT NULL,
    name character varying(64) NOT NULL,
    password character varying(32) NOT NULL,
    role bigint,
    PRIMARY KEY (id)
);

CREATE TABLE public."role"
(
    id bigserial NOT NULL,
    name character varying(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public."product"
(
    id bigserial NOT NULL,
    name character varying(128) NOT NULL,
    category bigint,
    comment text,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."category"
(
    id bigserial NOT NULL,
    name character varying(64) NOT NULL,
    comment text,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE public."storage"
(
    id bigserial NOT NULL,
    category bigint,
    product bigint,
    count bigint,
    PRIMARY KEY (id)
);

CREATE TABLE public."journal"
(
    id bigserial NOT NULL,
    type character varying(10),
    product bigint,
    category bigint,
    dttm timestamp without time zone
    PRIMARY KEY (id)
);

CREATE TABLE public."delivery_request"
(
    id bigserial NOT NULL,
    status character varying(10),
    store text,
    request_dt date,
    list bigint,
    PRIMARY KEY (id)
);

CREATE TABLE public."log"
(
    id bigserial NOT NULL,
    operation text,
    person text,
    log_dt date,
    PRIMARY KEY (id)
);

CREATE TABLE public.list
(
    id bigserial NOT NULL,
    product bigint,
    count bigint,
    number bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (product)
    REFERENCES public.product (id) MATCH SIMPLE
);

ALTER TABLE IF EXISTS user
    ADD CONSTRAINT role_id FOREIGN KEY (role)
    REFERENCES role (id) MATCH SIMPLE;

ALTER TABLE IF EXISTS product
    ADD CONSTRAINT category_id FOREIGN KEY (category)
    REFERENCES category (id) MATCH SIMPLE;

ALTER TABLE IF EXISTS storage
    ADD CONSTRAINT product_id FOREIGN KEY (product)
    REFERENCES product (id) MATCH SIMPLE;

ALTER TABLE IF EXISTS storage
    ADD CONSTRAINT category_id FOREIGN KEY (category)
    REFERENCES category (id) MATCH SIMPLE;

ALTER TABLE IF EXISTS journal
    ADD CONSTRAINT product_id FOREIGN KEY (product)
    REFERENCES product (id) MATCH SIMPLE;

ALTER TABLE IF EXISTS journal
    ADD CONSTRAINT category_id FOREIGN KEY (category)
    REFERENCES category (id) MATCH SIMPLE;

ALTER TABLE IF EXISTS public.delivery_request
    ADD FOREIGN KEY (list)
    REFERENCES public.list (id) MATCH SIMPLE;