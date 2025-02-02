-- Step 1: Create the database (if not created)
CREATE DATABASE employee_db;

-- Step 2: Connect to the database
\c employee_db;

-- Step 3: Create the employee table
CREATE TABLE employees (
    eid SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    designation VARCHAR(50) NOT NULL,
    department VARCHAR(50) NOT NULL
);

-- Step 4: Insert some sample data
INSERT INTO employees (name, age, salary, designation, department) 
VALUES 
('Alice', 30, 60000, 'Programmer', 'IT'),
('Bob', 35, 75000, 'Manager', 'HR'),
('Charlie', 28, 50000, 'Clerk', 'Admin');
