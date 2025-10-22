CREATE TABLE `users` (
  `user_id` int PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `guild_id` int,
  `discord_id` varchar(255),
  `role_code` varchar(255),
  `main_character_id` int,
  `created_at` timestamp,
  `updated_at` timestamp,
  `deleted_at` timestamp
);

CREATE TABLE `guild` (
  `guild_id` int PRIMARY KEY AUTO_INCREMENT,
  `guild_name` varchar(255)
);

CREATE TABLE `characters` (
  `character_id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `nickname` varchar(255),
  `class_code` varchar(255),
  `item_level` double,
  `is_main` bool,
  `created_at` timestamp
);

CREATE TABLE `common_codes` (
  `code_value` varchar(255) PRIMARY KEY ,
  `code_type` varchar(255) COMMENT 'class, raid_difficulty, user_role 등',
  `code_name` varchar(255)
);

CREATE TABLE `raids` (
  `raid_id` int PRIMARY KEY AUTO_INCREMENT,
  `raid_name` varchar(255),
  `difficulty_code` varchar(255) NOT NULL,
  `required_player_count` int,
  `gold_reward` int
);

CREATE TABLE `raid_applications` (
  `application_id` int PRIMARY KEY AUTO_INCREMENT,
  `character_id` int NOT NULL,
  `raid_id` int NOT NULL,
  `status_code` varchar(255) NOT NULL,
  `start_date_time` timestamp COMMENT '신청자가 가능한 시작 시간',
  `end_date_time` timestamp COMMENT '신청자가 가능한 종료 시간',
  `created_at` timestamp
);

CREATE TABLE `parties` (
  `party_id` int PRIMARY KEY AUTO_INCREMENT,
  `raid_id` int NOT NULL,
  `scheduled_at` timestamp COMMENT '파티 확정 시간' NOT NULL
);

CREATE TABLE `party_members` (
  `party_member_id` int PRIMARY KEY AUTO_INCREMENT,
  `party_id` int NOT NULL,
  `character_id` int NOT NULL
);


CREATE TABLE `synergy` (
  `synergy_id` int PRIMARY KEY AUTO_INCREMENT,
  `synergy_name` varchar(255)
);

CREATE TABLE `class_synergy_map` (
  `class_synergy_id` int PRIMARY KEY AUTO_INCREMENT,
  `class_code` varchar(255),
  `synergy_id` int
);

ALTER TABLE `users` ADD FOREIGN KEY (`guild_id`) REFERENCES `guild` (`guild_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`role_code`) REFERENCES `common_codes` (`code_value`);

ALTER TABLE `characters` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `characters` ADD FOREIGN KEY (`class_code`) REFERENCES `common_codes` (`code_value`);

ALTER TABLE `raids` ADD FOREIGN KEY (`difficulty_code`) REFERENCES `common_codes` (`code_value`);

ALTER TABLE `raid_applications` ADD FOREIGN KEY (`character_id`) REFERENCES `characters` (`character_id`);

ALTER TABLE `raid_applications` ADD FOREIGN KEY (`raid_id`) REFERENCES `raids` (`raid_id`);

ALTER TABLE `parties` ADD FOREIGN KEY (`raid_id`) REFERENCES `raids` (`raid_id`);

ALTER TABLE `party_members` ADD FOREIGN KEY (`party_id`) REFERENCES `parties` (`party_id`);

ALTER TABLE `party_members` ADD FOREIGN KEY (`character_id`) REFERENCES `characters` (`character_id`);

ALTER TABLE `users` ADD FOREIGN KEY (`main_character_id`) REFERENCES `characters` (`character_id`);

ALTER TABLE `class_synergy_map` ADD FOREIGN KEY (`synergy_id`) REFERENCES `synergy` (`synergy_id`);

ALTER TABLE `class_synergy_map` ADD FOREIGN KEY (`class_code`) REFERENCES `common_codes` (`code_value`);

ALTER TABLE `raid_applications` ADD FOREIGN KEY (`status_code`) REFERENCES `common_codes` (`code_value`);
