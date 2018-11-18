--create database Abiturient;
--go

--Пол
create table Gender (
	id int primary key,	--код
	name text,			--название пола (для отображения в интерфейсе)
	codeFIS text		--код выгрузки в ФИС
);


--Гражданство
create table Nationality (
	id int primary key,	--код
	name text,			--название гражданства/страна (для отображения в интерфейсе)
	codeFIS text		--код выгрузки в ФИС
);


--Причины возврата документов
create table ReturnReasons (
	id int primary key,	--код
	name text,			--наименование причины (для отображения в интерфейсе)
	codeFIS text		--код выгрузки в ФИС
);


--Абитуриент
create table Abiturient (
	aid int primary key,	--код/номер личного дела абитуриента
	SName varchar(200),		--Фамилия
	Fname varchar(200),		--Имя
	MName varchar(200),		--Отчество
	Birthday Date,			--Дата рождения
	Birthplace text,		--Место рождения
	id_gender int,			--код пола
	id_nationality int,		--код страны гражданства
	email text,				--e-mail
	phoneNumbers text,		--телефоны
	needHostel int,			--метка "Нуждается в общежитии"
	registrationDate Date,	--дата подачи заявления
	returnDate Date,		--Дата возврата документов
	id_returnReason int,	--причина возврата документов
	needSpecConditions int,	--метка "Нуждается в специальных условиях вступительных испытаний"
	is_enrolled int,		--метка о зачислении
	
	--Внешние ключи
	foreign key (id_gender) references Gender(id) on update cascade,
	foreign key (id_nationality) references Nationality(id) on update cascade,
	foreign key (id_returnReason) references ReturnReasons(id) on update cascade
);

