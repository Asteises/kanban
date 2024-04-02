CREATE TABLE IF NOT EXISTS users
(
    id          UUID PRIMARY KEY,
    username    VARCHAR(255)  NOT NULL,
    chat_id     BIGINT UNIQUE NOT NULL,
    create_date timestamp,
    create_by   varchar,
    date_modify timestamp,
    user_modify varchar,
    deleted     boolean,
    version     int
);

CREATE TABLE IF NOT EXISTS boards
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    owner_id    UUID,
    create_date timestamp,
    create_by   varchar,
    date_modify timestamp,
    user_modify varchar,
    deleted     boolean,
    version     int
);

CREATE TABLE IF NOT EXISTS tasks
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    board_id    UUID,
    creator_id  UUID,
    executor_id UUID,
    create_date timestamp,
    create_by   varchar,
    date_modify timestamp,
    user_modify varchar,
    deleted     boolean,
    version     int,
    foreign key (board_id) references boards (id),
    foreign key (creator_id) references users (id),
    foreign key (executor_id) references users (id)
);

CREATE TABLE IF NOT EXISTS users_boards
(
    user_id  UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    board_id UUID NOT NULL REFERENCES boards (id) ON DELETE CASCADE
);