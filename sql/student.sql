use learnersacademy;

CREATE TABLE student (
    RollNo int NOT NULL,
	FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
    studentClassId int NOT NULL,
    PRIMARY KEY (RollNo,studentClassId)
);

