CREATE TABLE users (
	id uuid NOT NULL,
	email varchar(255) NOT NULL,
	full_name varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE customer (
	id uuid NOT NULL,
	api_key uuid NOT NULL,
	created_date timestamp NOT NULL,
	updated_date timestamp NOT NULL,
	user_id uuid NOT NULL REFERENCES users(id),
	CONSTRAINT customer_pkey PRIMARY KEY (id) 
);

CREATE TABLE log (
	id uuid NOT NULL,
	app_environment varchar(255) NULL,
	app_host varchar(255) NULL,
	app_ip varchar(255) NULL,
	app_name varchar(255) NULL,
	archived bool NOT NULL,
	check_alert bool NOT NULL,
	"content" text NULL,
	"level" varchar(255) NULL,
	"timestamp" timestamp NULL,
	title varchar(255) NOT NULL,
	customer_id uuid NOT NULL REFERENCES customer(id),
	CONSTRAINT log_pkey PRIMARY KEY (id)
);


CREATE TABLE trigger_filter (
	id uuid NOT NULL,
	app_name varchar(255) NOT NULL,
	environment varchar(255) NOT NULL,
	"level" varchar(255) NOT NULL,
	CONSTRAINT trigger_filter_pkey PRIMARY KEY (id)
);

CREATE TABLE "trigger" (
	id uuid NOT NULL,
	created_date timestamp NOT NULL,
	last_modified_date timestamp NOT NULL,
	active bool NOT NULL,
	archived bool NOT NULL,
	email varchar(255) NOT NULL,
	message varchar(255) NOT NULL,
	"name" varchar(99) NOT NULL,
	created_by_id uuid NOT NULL REFERENCES users(id),
	last_modified_by_id uuid NOT NULL REFERENCES users(id),
	trigger_filter_id uuid NULL REFERENCES trigger_filter(id),
	CONSTRAINT trigger_pkey PRIMARY KEY (id)
);

CREATE TABLE alert (
	id uuid NOT NULL,
	created_date timestamp NULL,
	last_modified_date timestamp NULL,
	visualized bool NOT NULL,
	log_id uuid NULL REFERENCES log(id),
	trigger_id uuid null REFERENCES trigger(id),
	CONSTRAINT alert_pkey PRIMARY KEY (id)
);