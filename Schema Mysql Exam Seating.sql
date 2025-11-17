-- MySQL schema for Exam Seating Arrangement System

CREATE DATABASE IF NOT EXISTS exam_seating_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE exam_seating_db;

-- Simple admin table could be added later. For now we use a hard-coded admin in AuthServlet.

CREATE TABLE IF NOT EXISTS students (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(50)  NOT NULL UNIQUE,
    name        VARCHAR(150) NOT NULL,
    program     VARCHAR(150) NOT NULL,
    semester    INT          NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS rooms (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    code     VARCHAR(50) NOT NULL UNIQUE,
    capacity INT         NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS exams (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    course_code  VARCHAR(50)  NOT NULL,
    course_name  VARCHAR(200) NOT NULL,
    exam_date    DATE         NOT NULL,
    start_time   TIME         NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS allocations (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    exam_id     INT NOT NULL,
    student_id  INT NOT NULL,
    room_id     INT NOT NULL,
    seat_number INT NOT NULL,
    CONSTRAINT fk_alloc_exam FOREIGN KEY (exam_id) REFERENCES exams (id) ON DELETE CASCADE,
    CONSTRAINT fk_alloc_student FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
    CONSTRAINT fk_alloc_room FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE
) ENGINE=InnoDB;
