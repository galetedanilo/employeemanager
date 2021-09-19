INSERT INTO tb_departments(name, description) VALUES ('Technology', 'This department is very ...');
INSERT INTO tb_departments(name, description) VALUES ('Human Resources', 'This department is very ...');
INSERT INTO tb_departments(name, description) VALUES ('Big Data', 'This department is very ...');

INSERT INTO tb_phones(type, number) VALUES ('HOME', '1989875989');
INSERT INTO tb_phones(type, number) VALUES ('MOBILE', '1989875989');
INSERT INTO tb_phones(type, number) VALUES ('HOME', '1989875989');
INSERT INTO tb_phones(type, number) VALUES ('HOME', '1989875989');
INSERT INTO tb_phones(type, number) VALUES ('COMMERCIAL', '1989875989');
INSERT INTO tb_phones(type, number) VALUES ('HOME', '1989875989');
INSERT INTO tb_phones(type, number) VALUES ('MOBILE', '1989875989');

INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date, email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Pedro', 'Souza', '123.466.789-80', '1989-08-12', 'pedro@gmail.com', 'Java', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar1.png', 1);
INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date,email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Joao', 'Deus', '457.895.789-45', '1989-05-08', 'joao@gmail.com', 'UX/UI', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar6.png', 2);
INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date, email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Maria', 'Beatriz', '134.489.756-12', '1988-07-12', 'maria@gmail.com', 'Angular', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar4.png', 2);
INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date, email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Angela', 'Cruz', '335.778.998-45', '1987-11-02', 'angela@gmail.com', 'Java/PHP', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar8.png', 3);

INSERT INTO tb_employees_phones(employee_id, phones_id) VALUES (1, 1);
INSERT INTO tb_employees_phones(employee_id, phones_id) VALUES (1, 2);
INSERT INTO tb_employees_phones(employee_id, phones_id) VALUES (2, 3);
INSERT INTO tb_employees_phones(employee_id, phones_id) VALUES (2, 4);
INSERT INTO tb_employees_phones(employee_id, phones_id) VALUES (3, 5);
INSERT INTO tb_employees_phones(employee_id, phones_id) VALUES (3, 6);
INSERT INTO tb_employees_phones(employee_id, phones_id) VALUES (4, 7);
