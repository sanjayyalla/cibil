drop database cibil;
CREATE DATABASE cibil;
use cibil;

create table creditReport(
	report_id INT primary key AUTO_INCREMENT,
    generated_on DATE
);

create table Customers(
	customer_id INT primary key auto_increment,
    report_id int ,
    full_name varchar(50),
	date_of_birth	date,
    gender varchar(10),
    pan_number	varchar(10),
    mobile varchar(10),
    email varchar(20),
    aadhaar varchar(12),
    FOREIGN KEY(report_id) references creditReport(report_id)
);

CREATE TABLE Address (
    address_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    line VARCHAR(255),
    street VARCHAR(255),
    city VARCHAR(100),
    pincode INT,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

CREATE TABLE CibilScore (
    score_id INT PRIMARY KEY AUTO_INCREMENT,
    report_id INT,
    score INT,
    score_date DATE,
    risk_grade VARCHAR(10),
    FOREIGN KEY (report_id) REFERENCES creditReport(report_id)
);

CREATE TABLE Accounts (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    report_id INT,
    account_number VARCHAR(20),
    account_type VARCHAR(20),
    member_name VARCHAR(50),
    ownership VARCHAR(20),
    date_opened DATE,
    last_payment_date DATE,
    current_balance DECIMAL(10,2),
    credit_limit DECIMAL(10,2),
    sanctioned_amount DECIMAL(10,2),
    EMI_amount DECIMAL(10,2),
    tenure_months INT,
    payment_history VARCHAR(50),
    account_status VARCHAR(10),
    FOREIGN KEY (report_id) REFERENCES creditReport(report_id)
);
CREATE TABLE Enquiries (
    enquiry_id INT PRIMARY KEY AUTO_INCREMENT,
    report_id INT,
    enquiry_date DATE,
    member_name VARCHAR(50),
    enquiry_purpose VARCHAR(20),
    enquiry_amount DECIMAL(10,2),
    FOREIGN KEY (report_id) REFERENCES creditReport(report_id)
);

CREATE TABLE Remarks (
    remark_id INT PRIMARY KEY AUTO_INCREMENT,
    report_id INT,
    description TEXT,
    FOREIGN KEY (report_id) REFERENCES creditReport(report_id)
);

ALTER TABLE Accounts 
    MODIFY date_opened DATETIME(6),
    MODIFY last_payment_date DATETIME(6);
    