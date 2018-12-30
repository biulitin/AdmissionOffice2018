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
	grouping text,	        -- группа
	number_in_group text,	-- порядковый номер в группе
	date_of_exam date,	-- дата испытания
	score int,		-- балл
	mark int,		-- отметка сдачи

	--Внешние ключи
	foreign key (id_abiturient) references Abiturietn(aid) on update cascade,
	foreign key (id_entranceExam) references EntranceExam(id) on update cascade,
	foreign key (id_BasisMark) references BasisMark(id) on update cascade
);

--информации по таблице "Формат испытания" "Язык испытания" не нашла.

--Специальность
create table Speciality(
	id int primary key, --идентификатор
	name text, --наименование
	code_fic text  --код_ФИС
);

--Форма обучения
create table FormOfEducation(
	id int primary key, --идентификатор
	name text, --наименование
	code_fic text  --код_ФИС
);

--Конкурсная группа
create table CompetitiveGroup(
	id int primary key, --идентификатор
	name text, --наименование
	code_fic text  --код_ФИС
);

--Целевая организация
create table TargetOrganisation(
	id int primary key, --идентификатор
	name text, --наименование
	code_fic text  --код_ФИС
);

--План приёма
create table acceptance_plan(
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
	code_fic text  --код_ФИС
);

--Абитуриент БВИ
create table AbiturientBVI(
	id_abiturient int,
	type_bvi int,
--Внешние ключи
    foreign key(id_abiturient) references Abiturient(aid) on update cascade, --идентификатор абитуриента
	foreign key(type_bvi) references TypeBVI(id) on update cascade --тип БВИ
);

--Абитуриент_Конкурсные группы
create table AbiturientCompetitiveGroups(
     aid int,--id абитуриента
     specialty int, --специальность
     formOfEducation int,--форма обучения
     competitiveGroup int, --Конкурсная_группа
     targetOrganization int, --Целевая_организация
     BVIright int, --Право_БВИ
     quotaRight int, --Право_квота
     preferentialRight int, --Право_ПП
     competitiveScore int, --Конкурсный_балл
     sumOfIndividAchievmentScores int, --Сумма баллов за_индивидуальные_достижения
     noteAboutAdmission int, --Отметка_о_зачислении
--Внешние ключи
    foreign key(aid) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(specialty) references Speciality(id) on update cascade, --специальность
    foreign key(formOfEducation) references FormOfEducation(id) on update cascade, --форма обучения
    foreign key(competitiveGroup) references CompetitiveGroup(id) on update cascade, --Конкурсная_группа
    foreign key(targetOrganization) references TargetOrganisation(id) on update cascade --Целевая_организация
    
);

--Абитуриент_документ_БВИ
create table AbiturientDocumentBVI(
     aid int,--id абитуриента
     nameOfDocument text, --Наименование документа
     serial text, --Серия
     num text, --Номер
     issuedAt text, --Кем_выдан
     dateOfIssue date, --Дата_выдачи
--Внешние ключи
    foreign key(aid) references Abiturient(aid) on update cascade --идентификатор абитуриента
     
);

--Тип Квоты
create table TypeOfQuote(
     id int primary key, --идентификатор
     nameOfQuote text, --Наименование
     code_fic text --Код_ФИС

);

--Абитуриент_Квота
create table AbiturientQuote(
     aid int, --id абитуриента
     typeOfQuote int, --Тип_Квоты 
--Внешние ключи
    foreign key(aid) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(typeOfQuote) references TypeOfQuote(id) on update cascade --Тип Квоты
);
     
--Абитуриент_документ_Квота
create table AbiturientDocQuote(
         aid int, --id абитуриента
         nameOfDocument text, --Наименование документа
         serial text, --Серия
         num text, --Номер
         issuedAt text, --Кем_выдан
         dateOfIssue date, --Дата_выдачи
--Внешние ключи
    foreign key(aid) references Abiturient(aid) on update cascade --идентификатор абитуриента
     
);

--Тип ПП 
create table TypeOfPrefRight(
        id int primary key, --идентификатор
        nameOfQuote text, --Наименование
        code_fic text --Код_ФИС

);

--Абитуриент_Преимущественное право (ПП)
create table AbiturientPrefRight(
         aid int, --id абитуриента
         typeOfPrefRight int, --Тип_ПП
--Внешние ключи
    foreign key(aid) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(typeOfPrefRight) references TypeOfPrefRight(id) on update cascade --Тип ПП
);

--Абитуриент_документ_ПП
create table AbiturientDocPrefRight(
         aid int, --id абитуриента
         nameOfDocument text, --Наименование документа
         serial text, --Серия
         num text, --Номер
         issuedAt text, --Кем_выдан
         dateOfIssue date, --Дата_выдачи
--Внешние ключи
    foreign key(aid) references Abiturient(aid) on update cascade --идентификатор абитуриента
     
);

--Категория_допсведений
create table AdditionalInfCategory(
        id int primary key, --идентификатор
        nameOfQuote text, --Наименование
        code_fic text --Код_ФИС

);

--Абитуриент_допсведения
create table AbiturientAdditionalInf(
         aid int, --id абитуриента
         additionalInfCategory int, --Категория_допсведений
         nameOfDocument text, --Наименование документа
         serial text, --Серия
         num text, --Номер
         issuedAt text, --Кем_выдан
         dateOfIssue date, --Дата_выдачи
--Внешние ключи
    foreign key(aid) references Abiturient(aid) on update cascade, --идентификатор абитуриента
    foreign key(additionalInfCategory) references AdditionalInfCategory(id) on update cascade --Категория_допсведений
     
);


