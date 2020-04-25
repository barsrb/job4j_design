CREATE TABLE "roles" (
    "id" serial primary key,
    "title" character varying(128)
);

CREATE TABLE "rules" (
    "id" serial primary key,
    "title" character varying(128)
);

CREATE TABLE "role_rule" (
    "id" serial primary key,
    "role" integer references roles(id),
    "rule" integer references rules(id)
);

CREATE TABLE "users" (
    "id" serial primary key,
    "login" character varying(128),
    "password" character varying(128),
    "name" character varying(128),
    "email" character varying(128),
    "role" integer references roles(id)
);


CREATE TABLE "task_state" (
    "id" serial primary key,
    "title" character varying(128)
);

CREATE TABLE "tasks" (
	"id" serial primary key,
    "title" character varying(128),
    "text" text,
    "owner" integer references users(id),
    "state" integer references task_state(id)
);

CREATE TABLE "comments" (
    "id" serial primary key,
    "task" integer references tasks(id),
    "text" text,
	"user" integer references users(id)
);


CREATE TABLE "attachements" (
    "id" serial primary key,
    "path" character varying(256),
    "task" integer references tasks(id)
);

