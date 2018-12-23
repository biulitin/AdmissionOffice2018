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

-- Тип паспорта 
create table TypePassport (
	id int primary key,    --код
	name text,             --наименование (тип паспорта)
	codeFIS text           --код выгрузки в ФИС
);

--Абитуриент_паспорт
create table AbiturientPassport (
	series text,           --серия
	number text,           --номер
	issued_by text,        --кем выдан
	dateOf_issue date,     --дата выдачи
	id_abiturient int,     --код абитуриента
	id_typePassport int,   --код паспорта
	
	--Внешние ключи
	foreign key (id_abiturient) references Abiturient(aid) on update cascade,
	foreign key (id_typePassport) references TypePassport(id) on update cascade	
);

--Регион
create table Region (
    id int primary key,  --код
	name text,			 --наименование
	codeFIS text		 --код выгрузки в ФИС
);

--Тип населенного пункта
create table TypeSettlement (
    id int primary key,  --код
	name text,			 --наименование
	codeFIS text		 --код выгрузки в ФИС
);

--Абитуриент_адрес 
create table AbiturientAdress (
    postcode text,           --индекс
	adress text,             --адрес
	id_abiturient int,       --код абитуриента
	id_region int,           --код региона
	id_typeSettlement int,   --код населенного пункта
	
	--Внешние ключи
	foreign key (id_abiturient) references Abiturient(aid) on update cascade,
	foreign key (id_region) references Region(id) on update cascade,
	foreign key (id_typeSettlement) references TypeSettlement(id) on update cascade	
);

--Индивидуальные достижения
create table IndividualAchievement(
	id int primary key,  --код
	name text,			 --наименование
	score int,           --балл
	codeFIS text		 --код выгрузки в ФИС
);

--Абитуриент_Индивидуальные достижения 
create table AbiturientAchievement(
	nameOf_document text,    --название документа
	series_document text,    --серия документа
	number_document text,    --номер документа
	issued_by text,          --кем выдан
	dateOf_issue date,       --дата выдачи
    id_abiturient int,       --код абитуриента
	id_individualAchievement int, --код индивидуального достижения
	
	--Внешние ключи
	foreign key (id_abiturient) references Abiturient(aid) on update cascade,
	foreign key (id_individualAchievement) references IndividualAchievement(id) on update cascade
);

--Уровень_образования
create table LevelEducation(
	id int primary key,     -- идентификатор         
	name text,		-- наименование
	code_fic text		--код_ФИС
);

--Тип_образования
create table TypeEducation(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	code_fic text		--код_ФИС
);

--Абитуриент_Образование
create table AbiturientEducation(
	series_document text,    --серия документа
	number_document text,    --номер документа
	Name_University text,    --название ВУЗа
	date_of_issue date,      --дата выдачи 
	year_of_graduation int,  --год окончания
	
	--Внешние ключи
	foreign key (id_abiturient) references Abiturietn(aid) on update cascade,
	foreign key (id_levelEducation) references LevelEducation(id) on update cascade,
	foreign key (id_typeEducation) references TypeEducation(id) on update cascade
);

--Вступительные_испытания
create table EntranceExam(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	code_fic text,		--код_ФИС
	min_score int		--минимальный балл
);

--Основание_оценки
create table BasisMark(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	code_fic text,		--код_ФИС
);

--Абитуриент_Вступительные испытания
create table AbiturietnEntranceExam(
	grouping text,
	number_in_group text,
	date_of_exam date,
	score int,
	mark int,

	--Внешние ключи
	foreign key (id_abiturient) references Abiturietn(aid) on update cascade,
	foreign key (id_entranceExam) references EntranceExam(id) on update cascade,
	foreign key (id_BasisMark) references BasisMark(id) on update cascade
);

--информации по таблице "Формат испытания" "Язык испытания" не нашла.
