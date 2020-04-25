INSERT INTO "roles" ("id", "title") VALUES (1, 'Administrator');
INSERT INTO "roles" ("id", "title") VALUES (2, 'Moderator');
INSERT INTO "roles" ("id", "title") VALUES (3, 'User');

INSERT INTO "rules" ("id", "title") VALUES (1, 'Create users');
INSERT INTO "rules" ("id", "title") VALUES (2, 'Create tasks');
INSERT INTO "rules" ("id", "title") VALUES (3, 'Modify tasks');

INSERT INTO "role_rule" ("role", "rule") VALUES (1, 1);
INSERT INTO "role_rule" ("role", "rule") VALUES (1, 2);
INSERT INTO "role_rule" ("role", "rule") VALUES (1, 3);
INSERT INTO "role_rule" ("role", "rule") VALUES (2, 2);
INSERT INTO "role_rule" ("role", "rule") VALUES (2, 3);
INSERT INTO "role_rule" ("role", "rule") VALUES (3, 2);

INSERT INTO "users" ("id", "login", "password", "name", "email", "role") VALUES (1, 'admin', 'padmin', 'Administrator', 'admin@mail.ru', 1);
INSERT INTO "users" ("id", "login", "password", "name", "email", "role") VALUES (2, 'moder1', 'pmoder1', 'First moderator', 'moder1@mail.ru', 2);
INSERT INTO "users" ("id", "login", "password", "name", "email", "role") VALUES (3, 'moder2', 'pmoder2', 'Second  moderator', 'moder2@mail.ru', 2);
INSERT INTO "users" ("id", "login", "password", "name", "email", "role") VALUES (4, 'user_001', 'puser_001', 'User 001', 'user_001@mail.ru', 3);
INSERT INTO "users" ("id", "login", "password", "name", "email", "role") VALUES (5, 'user_002', 'puser_002', 'User 002', 'user_002@mail.ru', 3);
INSERT INTO "users" ("id", "login", "password", "name", "email", "role") VALUES (6, 'user_003', 'puser_003', 'User 003', 'user_003@mail.ru', 3);

INSERT INTO "task_category" ("id", "title") VALUES (1, 'New feature');
INSERT INTO "task_category" ("id", "title") VALUES (2, 'Bug fix');
INSERT INTO "task_category" ("id", "title") VALUES (3, 'Optimisation');

INSERT INTO "task_state" ("id", "title") VALUES (1, 'New task');
INSERT INTO "task_state" ("id", "title") VALUES (2, 'In work');
INSERT INTO "task_state" ("id", "title") VALUES (3, 'Closed');

INSERT INTO "tasks" ("id", "title", "text", "owner", "category", "state") VALUES (1, 'Create DB for app', 'Create DB for application. DB - postgress.', 4, 1, 3);
INSERT INTO "tasks" ("id", "title", "text", "owner", "category", "state") VALUES (2, 'Upload initial data', 'Import initial data in tables for developers', 4, 1, 3);
INSERT INTO "tasks" ("id", "title", "text", "owner", "category", "state") VALUES (3, 'Create GIT repo for', 'Create repository and upload SSH-keys', 6, 1, 2);
INSERT INTO "tasks" ("id", "title", "text", "owner", "category", "state") VALUES (4, 'Rename tables in DB', 'Rename table LOGINS into USERS', 5, 2, 1);

INSERT INTO "comments" ("id", "task", "text", "user") VALUES (1, 1, 'DB created successful', 6);
INSERT INTO "comments" ("id", "task", "text", "user") VALUES (2, 3, 'Please, upload SSH-keys', 4);
INSERT INTO "comments" ("id", "task", "text", "user") VALUES (3, 3, 'Keys attached', 6);

INSERT INTO "attachements" ("id", "path", "task") VALUES (1, 'data.sql', 2);
INSERT INTO "attachements" ("id", "path", "task") VALUES (2, 'user_001.key', 3);
INSERT INTO "attachements" ("id", "path", "task") VALUES (3, 'user_002.key', 3);
INSERT INTO "attachements" ("id", "path", "task") VALUES (4, 'user_003.key', 3);