CREATE TABLE`company` (
    `id` INT auto_increment PRIMARY KEY,
    `name` varchar(255) NOT NULL,
    `address` varchar(255)
);

CREATE TABLE `employee` (
    `id` INT auto_increment PRIMARY KEY,
    `name` varchar(255) NOT NULL,
    `companyId` INT NOT NULL,
     FOREIGN KEY (companyId) REFERENCES company (id)
);