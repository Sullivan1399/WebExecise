DROP DATABASE IF EXISTS `jbdcdemo`;
CREATE DATABASE `jbdcdemo`;

USE `jbdcdemo`;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`roleId` INT NOT NULL AUTO_INCREMENT,
    `roleName` VARCHAR(500) NOT NULL,
    PRIMARY KEY (`roleid`)
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL UNIQUE,
  `password` VARCHAR(50) NULL,
  `email` VARCHAR(50) NULL,
  `fullname` NVARCHAR(100) NULL,
  `image` NVARCHAR(1000) NULL,
  `roleId` INT NOT NULL,
  `phone` VARCHAR(10) NULL,
  `createdDate` DATE NOT NULL,
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  PRIMARY KEY (`id`)
);

INSERT INTO `role`(`roleId`, `roleName`)
VALUES (1, 'Administrator');

INSERT INTO `role`(`roleId`, `roleName`)
VALUES (2, 'User');

INSERT INTO `role`(`roleId`, `roleName`)
VALUES (3, 'Manager');

INSERT INTO `user`(`username`, `password`, `email`, `fullname`, `image`, `roleId`, `phone`, `createdDate`) 
VALUES ('ntk', '123', 'ntk@gmail.com', 'Nguyễn Tuấn Kiệt', 'null', 1, '0812576612', '2024-09-20');