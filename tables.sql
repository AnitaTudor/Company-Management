drop table if exists employee;
drop table if exists department;
drop table if exists office;
drop table if exists location;
drop table if exists company;
drop table if exists salary;
drop table if exists name;


create table name (name_id int not null auto_increment,
                   first_name varchar(150) not null,
                   last_name varchar(150) not null,
                   primary key (name_id)
);

create table salary (salary_id int not null auto_increment,
                     gross_value int not null,
                     net_value int not null,
                     annual_raise double not null,
                     primary key (salary_id)
);

create table company (company_id int not null auto_increment,
                      company_name varchar(150) not null,
                      market_cap bigint not null,
                      primary key (company_id)
);

create table location (location_id int not null auto_increment,
                       address_line_A varchar(150) not null,
                       address_line_B varchar(150) not null,
                       city varchar(150) not null,
                       country varchar(150) not null,
                       primary key (location_id)
);

create table office (office_id int not null auto_increment,
                     office_name varchar(150) not null,
                     location_id int not null,
                     company_id int not null,
                     primary key (office_id),
                     foreign key (location_id) references location(location_id),
                     foreign key (company_id) references company(company_id)
);

create table department (department_id int not null auto_increment,
                         department_name varchar(150) not null,
                         office_id int not null,
                         primary key (department_id),
                         foreign key (office_id) references office(office_id)
);

create table employee (employee_id int not null auto_increment,
                       name_id int not null,
                       salary_id int not null,
                       boss_name_id int not null,
                       department_id int not null,
                       primary key (employee_id),
                       foreign key (name_id) references name (name_id),
                       foreign key (salary_id) references salary (salary_id),
                       foreign key (boss_name_id) references name (name_id),
                       foreign key (department_id) references department (department_id)
);










