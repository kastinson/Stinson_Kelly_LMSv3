/*
================================================================================
                          LMS Project SQL Script
================================================================================

Author: Kelly Stinson
Course: CEN-3024C-14320 Software Development I
Date: 11-16-2024

Project: Library Management System (LMS)
Description: 
    This SQL script is designed to create and configure the necessary database, tables, and user permissions
    for the Library Management System (LMS). It includes:
        - Database creation with appropriate character set.
        - User creation and permission setup.
        - Definition of the `books` table schema to store library inventory data.
        - Sample data insertion for testing purposes.

Usage Notes:
    - Ensure the MySQL server is running before executing this script.
    - Replace the placeholder password with a secure password for the `lms` user.
    - This script uses modern MySQL directives for compatibility and performance.

Compatibility:
    - MySQL 5.7 or later (tested)
    - Uses UTF-8 encoding for compatibility with diverse character sets.

Instructions:
    1. Execute this script on your MySQL server using a client such as MySQL Workbench or the `mysql` CLI tool.
    2. Ensure you have the necessary privileges to create databases and users.

Disclaimer:
    This script is intended for development and testing purposes. 
    Use secure practices when deploying in production.

================================================================================
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lms` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `lms`;

CREATE USER 'lms'@'localhost' IDENTIFIED VIA mysql_native_password USING 'password123';GRANT ALL PRIVILEGES ON *.* TO 'lms'@'localhost' REQUIRE NONE WITH GRANT OPTION MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;

GRANT ALL PRIVILEGES ON `lms`.* TO 'lms'@'localhost'; 

/*Table structure for table `books` */

DROP TABLE IF EXISTS `books`;

CREATE TABLE books (
    Barcode VARCHAR(255) PRIMARY KEY,           -- Unique barcode identifier
    Title VARCHAR(255) NOT NULL,                -- Title of the book
    Author VARCHAR(255) NOT NULL,               -- Author's name
    Genre VARCHAR(100),                         -- Genre of the book
    Status VARCHAR(50) DEFAULT 'Checked In',    -- Status of the book (Checked In/Out)
    DueDate DATE                                -- Due date for returning the book
) ENGINE=InnoDB;

ALTER TABLE books
ADD CONSTRAINT unique_barcode UNIQUE (Barcode);

INSERT INTO books (Barcode, Title, Author, Genre, Status, DueDate) VALUES
('9781250145121', 'The Grey Wolf', 'Louise Penny', 'Mystery', 'Checked In', NULL),
('9780316498933', 'Throne of Secrets', 'Kerri Maniscalco', 'Fantasy', 'Checked Out', '2024-11-17'),
('9780593159242', 'In Too Deep: A Reacher Novel', 'Lee Child, Andrew Child', 'Thriller', 'Checked In', NULL),
('9781250178619', 'The Women: A Novel', 'Kristin Hannah', 'Historical Fiction', 'Checked In', NULL),
('9780440000231', 'Counting Miracles: A Novel', 'Nicholas Sparks', 'Romance', 'Checked In', NULL),
('9780316499534', 'The Waiting: A Ballard and Bosch Novel', 'Michael Connelly', 'Crime', 'Checked In', NULL),
('9781649374042', 'Iron Flame', 'Rebecca Yarros', 'Fantasy', 'Checked In', NULL),
('9780735211235', 'The Blue Hour: A Novel', 'Paula Hawkins', 'Thriller', 'Checked Out', '2024-12-02'),
('9780989028232', 'Blood Over Bright Haven: A Novel', 'M. L. Wang', 'Fantasy', 'Checked In', NULL),
('9780861404213', 'Intermezzo: A Novel', 'Sally Rooney', 'Literary Fiction', 'Checked In', NULL),
('9780393247086', 'The God of the Woods: A Novel', 'Liz Moore', 'Mystery', 'Checked In', NULL),
('9781922330945', 'James: A Novel', 'Percival Everett', 'Literary Fiction', 'Checked In', NULL),
('9781922330655', 'Here One Moment', 'Liane Moriarty', 'Contemporary Fiction', 'Checked In', NULL),
('9781524760348', 'The Boyfriend', 'Freida McFadden', 'Psychological Thriller', 'Checked In', NULL),
('9781649371515', 'Fourth Wing', 'Rebecca Yarros', 'Fantasy', 'Checked In', NULL),
('9780593184574', 'The Housemaid', 'Freida McFadden', 'Thriller', 'Checked In', NULL),
('9781733617794', 'The Striker (Deluxe Edition)', 'Ana Huang', 'Romance', 'Checked Out', '2024-12-21'),
('9781619635173', 'A Court of Thorns and Roses', 'Sarah J. Maas', 'Fantasy', 'Checked In', NULL),
('9781399706002', 'Hexed', 'Emily McIntire', 'Fantasy', 'Checked In', NULL),
('9781529372927', 'The Stars Are Dying', 'Chloe C.', 'Science Fiction', 'Checked In', NULL),
('9781982131739', 'Greenlights', 'Matthew McConaughey', 'Memoir', 'Checked In', NULL),
('9781982185824', 'I’m Glad My Mom Died', 'Jennette McCurdy', 'Memoir', 'Checked In', NULL),
('9780593230257', 'The Light We Carry', 'Michelle Obama', 'Memoir', 'Checked In', NULL),
('9780593230578', 'Spare', 'Prince Harry', 'Memoir', 'Checked Out', '2024-11-19'),
('9781982160272', 'The Body Keeps the Score', 'Bessel van der Kolk', 'Psychology', 'Checked In', NULL),
('9780593139130', 'Becoming', 'Michelle Obama', 'Memoir', 'Checked In', NULL),
('9781984822185', 'Educated', 'Tara Westover', 'Memoir', 'Checked In', NULL),
('9781501161933', 'The Subtle Art of Not Giving a F*ck', 'Mark Manson', 'Self-Help', 'Checked In', NULL),
('9781982157289', 'Atomic Habits', 'James Clear', 'Self-Help', 'Checked In', NULL),
('9780525559474', 'Where the Crawdads Sing', 'Delia Owens', 'Memoir', 'Checked In', NULL),
('9781501171345', 'The Four Agreements', 'Don Miguel Ruiz', 'Self-Help', 'Checked In', NULL),
('9780062316110', 'Sapiens', 'Yuval Noah Harari', 'History', 'Checked In', NULL),
('9781501124020', 'Can’t Hurt Me', 'David Goggins', 'Memoir', 'Checked In', NULL),
('9781982137273', 'Dare to Lead', 'Brené Brown', 'Business', 'Checked In', NULL)