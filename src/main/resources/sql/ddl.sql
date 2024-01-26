CREATE TABLE `project_member` (
                                  `account_id`	varchar(50)	NOT NULL,
                                  `project_id`	BIGINT	NOT NULL,
                                  PRIMARY KEY (account_id, project_id)
);

CREATE TABLE `tasks` (
                         `task_id`	BIGINT	NOT NULL AUTO_INCREMENT primary key,
                         `task_title`	varchar(100)	NOT NULL,
                         `task_content`	text	NOT NULL,
                         `project_id`	BIGINT	NOT NULL,
                         `milestone_id` BIGINT	NOT NULL,
                         `account_id`	varchar(50)	NOT NULL
);

CREATE TABLE `comments` (
                            `comment_id`	BIGINT	NOT NULL AUTO_INCREMENT primary key,
                            `comment_content`	text	NOT NULL,
                            `task_id`	BIGINT	NOT NULL,
                            `account_id`	varchar(50)	NOT NULL
);

CREATE TABLE `projects` (
                            `project_id`	BIGINT	NOT NULL AUTO_INCREMENT primary key,
                            `account_id`	 varchar(50) NOT NULL,
                            `project_status`	varchar(10) NOT NULL,
                            `project_name`	varchar(100)	NOT NULL,
                            `project_explain`	varchar(300)	NULL
);

CREATE TABLE `milestones` (
                              `milestone_id`	BIGINT	NOT NULL AUTO_INCREMENT primary key,
                              `milestone_name`	varchar(50)	NOT NULL,
                              `project_id`	BIGINT	NOT NULL
);

CREATE TABLE `tags` (
                        `tag_id`	BIGINT	NOT NULL AUTO_INCREMENT primary key,
                        `tag_name`	varchar(12)	NOT NULL,
                        `project_id`	BIGINT	NOT NULL
);

CREATE TABLE `task_tag` (
                            `task_id`	BIGINT	NOT NULL,
                            `tag_id`	BIGINT	NOT NULL,
                            PRIMARY KEY (task_id, tag_id)
);