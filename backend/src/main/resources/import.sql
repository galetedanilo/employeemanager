INSERT INTO tb_departments(name, description) VALUES ('Ciência de Dados', 'Ciência de dados (em inglês: data science) é uma área interdisciplinar voltada para o estudo e a análise de dados econômicos, financeiros e sociais, estruturados e não-estruturados, que visa a extração de conhecimento, detecção de padrões e/ou obtenção de insights para possíveis tomadas de decisão.');
INSERT INTO tb_departments(name, description) VALUES ('Getão de Pessoas', 'Gestão de Recursos Humanos, Gestão de Pessoas ou Administração de Recursos Humanos é a aplicação de um conjunto de conhecimentos e técnicas administrativas especializadas no gerenciamento das relações das pessoas com as organizações, com o objetivo de atingir os objetivos organizacionais, bem como proporcionar a satisfação e a realização das pessoas envolvidas.');
INSERT INTO tb_departments(name, description) VALUES ('Programador', 'Em ciências da computação; programador, desenvolvedor, codificador ou engenheiro de software é alguém que escreve, desenvolve ou faz manutenção de software em um grande sistema ou alguém que desenvolve software para uso em computadores pessoais.');


INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date, email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Pedro', 'Souza', '123.466.789-80', '1989-08-12', 'pedro@gmail.com', 'Gerente', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar1.png', 2);
INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date,email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Joao', 'Deus', '457.895.789-45', '1989-05-08', 'joao@gmail.com', 'UX/UI', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar6.png', 3);
INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date, email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Maria', 'Beatriz', '134.489.756-12', '1988-07-12', 'maria@gmail.com', 'Ciêntita da Dados', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar4.png', 1);
INSERT INTO tb_employees(first_Name, last_Name, cpf, birth_Date, email, job_Title, employee_Code, image_Url, department_Id) VALUES ('Angela', 'Cruz', '335.778.998-45', '1987-11-02', 'angelacruz@gmail.com', 'Java/PHP', 'ldo458doi-89a78d-daof25hfook', 'https://bootdey.com/img/Content/avatar/avatar8.png', 3);

INSERT INTO tb_phones(phone_Type, phone_Number, employee_Id) VALUES ('MOBILE', '+55 19 99458-9976', 1);
INSERT INTO tb_phones(phone_Type, phone_Number, employee_Id) VALUES ('HOME', '+55 19 3021-7896', 1);
INSERT INTO tb_phones(phone_Type, phone_Number, employee_Id) VALUES ('COMMERCIAL', '+55 19 3987-5989', 1);
INSERT INTO tb_phones(phone_Type, phone_Number, employee_Id) VALUES ('HOME', '+55 11 4579-5989', 2);
INSERT INTO tb_phones(phone_Type, phone_Number, employee_Id) VALUES ('COMMERCIAL', '+55 11 3265-2149', 2);
INSERT INTO tb_phones(phone_Type, phone_Number, employee_Id) VALUES ('HOME', '+55 21 89875-3989', 3);
INSERT INTO tb_phones(phone_Type, phone_Number, employee_Id) VALUES ('MOBILE', '+55 21 98967-5989', 3);

