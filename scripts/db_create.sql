CREATE TABLE person(
id_person SERIAL NOT NULL,
username VARCHAR(255) NOT NULL UNIQUE,
passwd_hash VARCHAR(60) NOT NULL,
salt VARCHAR NOT NULL,
name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
role INTEGER NOT NULL,
email VARCHAR(255) NOT NULL,
phone VARCHAR(12),
PRIMARY KEY (id_person)
);

CREATE TABLE manager(
id_manager SERIAL NOT NULL,
id_person SERIAL references person(id_person),
PRIMARY KEY(id_manager)
);

CREATE TABLE mentor(
id_mentor SERIAL NOT NULL,
id_person SERIAL references person(id_person),
PRIMARY KEY(id_mentor)
);

CREATE TABLE student(
id_student SERIAL NOT NULL,
id_person SERIAL references person(id_person) NOT NULL,
id_class SERIAL references class(id_class),
PRIMARY KEY (id_student)
);

CREATE TABLE class(
id_class SERIAL NOT NULL,
name VARCHAR(255) NOT NULL,
description VARCHAR(255) NOT NULL,
PRIMARY KEY (id_class)
);

CREATE TABLE mentor_class(
id_mentor SERIAL references mentor(id_mentor) NOT NULL,
id_class SERIAL references class(id_class) NOT NULL
);

CREATE TABLE artifact(
id_artifact SERIAL NOT NULL,
name VARCHAR(255) NOT NULL,
description VARCHAR(255) NOT NULL,
price INTEGER NOT NULL,
PRIMARY KEY (id_artifact)
);

CREATE TABLE quest(
id_quest SERIAL NOT NULL,
name VARCHAR(255) NOT NULL,
description VARCHAR(255) NOT NULL,
amount INTEGER NOT NULL,
PRIMARY KEY (id_quest)
);

CREATE TABLE student_quest(
id_student SERIAL references student(id_student),
id_quest SERIAL references quest(id_quest),
amount INTEGER NOT NULL,
completion_date DATE NOT NULL
);

CREATE TABLE student_artifact(
id_student SERIAL references student(id_student) NOT NULL,
id_artifact SERIAL references artifact(id_artifact) NOT NULL,
price INTEGER NOT NULL,
id_status SERIAL references status_enum(id_status) NOT NULL,
buy_date DATE NOT NULL
);

CREATE TABLE student_group(
id_student SERIAL references student(id_student) NOT NULL,
id_student_group SERIAL references students_group(id_student_group) NOT NULL,
amount_paid INTEGER NOT NULL
);

CREATE TABLE students_group(
id_student_group SERIAL NOT NULL,
id_artifact SERIAL references artifact(id_artifact),
price INTEGER NOT NULL,
status BOOLEAN NOT NULL,
id_status SERIAL references status_enum(id_status) NOT NULL,
buy_date DATE,
PRIMARY KEY (id_student_group)
);

CREATE TABLE status_enum(
id_status SERIAL NOT NULL,
name VARCHAR,
PRIMARY KEY (id_status)
);
