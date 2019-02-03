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
	inn text,				--ИНН
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
	id_abiturient int,     --код абитуриента
	id_typePassport int,   --код паспорта
	series text,           --серия
	number text,           --номер
	issued_by text,        --кем выдан
	dateOf_issue date,     --дата выдачи

	
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
	id_abiturient int,       --код абитуриента
	id_region int,           --код региона
	id_typeSettlement int,   --код населенного пункта
    postcode text,           --индекс
	adress text,             --адрес
	
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
    id_abiturient int,       --код абитуриента
	id_individualAchievement int, --код индивидуального достижения
	nameOf_document text,    --название документа
	series_document text,    --серия документа
	number_document text,    --номер документа
	issued_by text,          --кем выдан
	dateOf_issue date,       --дата выдачи
	score int,          	 --балл
	
	--Внешние ключи
	foreign key (id_abiturient) references Abiturient(aid) on update cascade,
	foreign key (id_individualAchievement) references IndividualAchievement(id) on update cascade
);

--Уровень_образования
create table LevelEducation(
	id int primary key,     -- идентификатор         
	name text,		-- наименование
	codeFIS text		--код_ФИС
);

--Тип_образования
create table TypeEducation(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	codeFIS text		--код_ФИС
);

--Абитуриент_Образование
create table AbiturientEducation(
    id_abiturient int,       --код абитуриента
	id_levelEducation int,   --код уровня образования
	id_typeEducation int,    --код типа образования
	series_document text,    --серия документа
	number_document text,    --номер документа
	Name_University text,    --название ВУЗа
	date_of_issue date,      --дата выдачи 
	year_of_graduation int,  --год окончания
	
	--Внешние ключи
	foreign key (id_abiturient) references Abiturient(aid) on update cascade,
	foreign key (id_levelEducation) references LevelEducation(id) on update cascade,
	foreign key (id_typeEducation) references TypeEducation(id) on update cascade
);

--Вступительные_испытания
create table EntranceExam(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	codeFIS text,		--код_ФИС
	min_score int		--минимальный балл
);

--Формат испытания
create table FormOfExam(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	codeFIS text		--код_ФИС
);

--Язык испытания
create table LanguageOfExam(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	codeFIS text		--код_ФИС
);

--Основание_оценки
create table BasisMark(
	id int primary key,	-- идентификатор  
	name text,		-- наименование
	codeFIS text		--код_ФИС
);

--Абитуриент_Вступительные испытания
create table AbiturientEntranceExam(
    id_abiturient int,       --код абитуриента
    id_entranceExam int,     --код ВИ
	id_FormOfExam int,        --код формы ВИ
    id_LanguageOfExam int,   --код языка ВИ
    id_BasisMark int,        --код основания для оценки	
	groupOfExam text,	     -- группа
	number_in_group text,	-- порядковый номер в группе
	date_of_exam date,		-- дата испытания
	score int,				-- балл
	is_passed int,			-- отметка сдачи
	has_100 int,			--флаг о наличии права на 100 баллов по предмету

	--Внешние ключи
	foreign key (id_abiturient) references Abiturient(aid) on update cascade,
	foreign key (id_entranceExam) references EntranceExam(id) on update cascade,
	foreign key (id_FormOfExam) references FormOfExam(id) on update cascade,
	foreign key (id_LanguageOfExam) references LanguageOfExam(id) on update cascade,
	foreign key (id_BasisMark) references BasisMark(id) on update cascade
);

--Абитуриент_документы, подтверждающие право на 100 баллов по предметам
create table AbiturientDocumentsFor100balls(
	id_abiturient int, --id абитуриента
	nameOfDocument text, --Наименование документа
	series_document text,    --серия документа
	number_document text,    --номер документа
	date_of_issue date,      --дата выдачи 
	issued_by text,          --кем выдан
	
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade --идентификатор абитуриента
);

--Специальность
create table Speciality(
	id int primary key, --идентификатор
	name text, --наименование
	codeFIS text  --код_ФИС
);

--Форма обучения
create table FormOfEducation(
	id int primary key, --идентификатор
	name text, --наименование
	codeFIS text  --код_ФИС
);

--Конкурсная группа
create table CompetitiveGroup(
	id int primary key, --идентификатор
	name text, --наименование
	codeFIS text  --код_ФИС
);

--Целевая организация
create table TargetOrganisation(
	id int primary key, --идентификатор
	name text, --наименование
	codeFIS text  --код_ФИС
);

--Абитуриент_Конкурсные группы
create table AbiturientCompetitiveGroups(
     id_abiturient int,--id абитуриента
     id_specialty int, --специальность
     id_formOfEducation int,--форма обучения
     id_competitiveGroup int, --Конкурсная_группа
     id_targetOrganization int, --Целевая_организация
     BVIright int, --Право_БВИ
     quotaRight int, --Право_квота
     preferentialRight int, --Право_ПП
     competitiveScore int, --Конкурсный_балл
     sumOfIndividAchievmentScores int, --Сумма баллов за_индивидуальные_достижения
     is_enrolled int, --Отметка_о_зачислении
	 
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(id_specialty) references Speciality(id) on update cascade, --специальность
    foreign key(id_formOfEducation) references FormOfEducation(id) on update cascade, --форма обучения
    foreign key(id_competitiveGroup) references CompetitiveGroup(id) on update cascade, --Конкурсная_группа
    foreign key(id_targetOrganization) references TargetOrganisation(id) on update cascade --Целевая_организация
    
);

--План приёма
create table AcceptancePlan(
	id_speciality int,  --код_специальности
	id_formOfEduc int,  --форма_обучения
	id_compGr int,  --конкурсная_группа
	id_targOrg int,  --целевая организация
	number_of_places int, --количество_мест
	number_of_places_quota int, --количество_мест_квота
	
--Внешние ключи
	foreign key(id_speciality) references Speciality(id) on update cascade,
	foreign key(id_formOfEduc) references FormOfEducation(id) on update cascade,
	foreign key(id_compGr) references CompetitiveGroup(id) on update cascade,
	foreign key(id_targOrg) references TargetOrganisation(id) on update cascade
);

--Тип БВИ
create table TypeBVI(
	id int primary key, --идентификатор
	name text, --наименование
	codeFIS text  --код_ФИС
);

--Абитуриент БВИ
create table AbiturientBVI(
	id_abiturient int,
	type_bvi int,
	
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade, --идентификатор абитуриента
	foreign key(type_bvi) references TypeBVI(id) on update cascade --тип БВИ
);

--Абитуриент_документ_БВИ
create table AbiturientDocumentBVI(
	id_abiturient int,--id абитуриента
	nameOfDocument text, --Наименование документа
	series_document text,    --серия документа
	number_document text,    --номер документа
	date_of_issue date,      --дата выдачи 
	issued_by text,          --кем выдан
	
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade --идентификатор абитуриента
     
);

--Тип Квоты
create table TypeOfQuote(
	id int primary key, --идентификатор
	nameOfQuote text, --Наименование
	codeFIS text --Код_ФИС
);

--Абитуриент_Квота
create table AbiturientQuote(
	id_abiturient int, --id абитуриента
	id_typeOfQuote int, --Тип_Квоты 

--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(id_typeOfQuote) references TypeOfQuote(id) on update cascade --Тип Квоты
);
     
--Абитуриент_документ_Квота
create table AbiturientDocQuote(
	id_abiturient int, --id абитуриента
	nameOfDocument text, --Наименование документа
	series_document text,    --серия документа
	number_document text,    --номер документа
	date_of_issue date,      --дата выдачи 
	issued_by text,          --кем выдан

--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade --идентификатор абитуриента
     
);

--Тип ПП 
create table TypeOfPrefRight(
	id int primary key, --идентификатор
	nameOfQuote text, --Наименование
	codeFIS text --Код_ФИС
);

--Абитуриент_Преимущественное право (ПП)
create table AbiturientPrefRight(
	id_abiturient int, --id абитуриента
	id_typeOfPrefRight int, --Тип_ПП
	
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(id_typeOfPrefRight) references TypeOfPrefRight(id) on update cascade --Тип ПП
);

--Абитуриент_документ_ПП
create table AbiturientDocPrefRight(
	id_abiturient int, --id абитуриента
	nameOfDocument text, --Наименование документа
	series_document text,    --серия документа
	number_document text,    --номер документа
	date_of_issue date,      --дата выдачи 
	issued_by text,          --кем выдан
	
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade --идентификатор абитуриента
     
);

--Категория_допсведений
create table AdditionalInfCategory(
	id int primary key, --идентификатор
	nameOfQuote text, --Наименование
	codeFIS text --Код_ФИС
);

--Абитуриент_допсведения
create table AbiturientAdditionalInf(
	id_abiturient int, --id абитуриента
	id_additionalInfCategory int, --Категория_допсведений
	nameOfDocument text, --Наименование документа
	series_document text,    --серия документа
	number_document text,    --номер документа
	date_of_issue date,      --дата выдачи 
	issued_by text,          --кем выдан
	
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(id_additionalInfCategory) references AdditionalInfCategory(id) on update cascade --Категория_допсведений
     
);

--Пользователи
create table Users(
      id int primary key, --идентификатор
      login text, --логин
      password text, --пароль
      fio text --ФИО
);
