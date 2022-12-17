use onlineqaa;

create table if not exists
    STUDENT
(
    ID         int          not null auto_increment primary key,
    STUDENT_ID varchar(20)  not null,
    USERNAME   varchar(30)  not null unique,
    PASSWORD   varchar(128) not null
) charset = utf8mb4;

create table if not exists
    TEACHER
(
    ID          int          not null auto_increment primary key,
    USERNAME    varchar(20)  not null unique,
    PASSWORD    varchar(128) not null,
    DESCRIPTION varchar(150) not null comment '教师简介',
    LEVEL       varchar(20)  not null
) charset = utf8mb4;

create table if not exists
    COURSE
(
    ID          int          not null auto_increment primary key,
    NAME        varchar(20)  not null unique ,
    TIME        varchar(20)  not null unique,
    DESCRIPTION varchar(150) not null,
    RATE        int          not null
) charset = utf8mb4;

create table if not exists
    QUESTION
(
    ID     int          not null auto_increment primary key,
    CREATE_TIME timestamp not null ,
    ANSWER_TIME timestamp default null,
    INFO   varchar(150) not null,
    ANSWER varchar(150) default null
) charset = utf8mb4;

create table if not exists
    TEACHER_COURSE
(
    ID         int not null auto_increment primary key,
    TEACHER_ID int not null references TEACHER (`ID`),
    COURSE_ID  int not null references COURSE (`ID`),
    constraint TEACHER_COURSE_UNIQUE
        unique (TEACHER_ID, COURSE_ID),
    constraint TEACHER_COURSE_UNIQUE1
        unique (COURSE_ID)
) charset = utf8mb4;
create table if not exists
    STUDENT_COURSE
(
    ID         int not null auto_increment primary key,
    STUDENT_ID int not null references STUDENT (`ID`),
    COURSE_ID  int not null references COURSE (`ID`),
    constraint TEACHER_COURSE_UNIQUE
        unique (STUDENT_ID, COURSE_ID)
) charset = utf8mb4;

create table if not exists
    STUDENT_COURSE_QUESTION
(
    ID                int not null auto_increment primary key,
    STUDENT_COURSE_ID int not null references STUDENT_COURSE (`ID`),
    QUESTION_ID       int not null references QUESTION (`ID`),
    constraint STUDENT_COURSE_QUESTION
        unique (STUDENT_COURSE_ID, QUESTION_ID),
    constraint STUDENT_COURSE_QUESTION1
        unique (QUESTION_ID)
) charset = utf8mb4;
