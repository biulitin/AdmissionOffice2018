CREATE OR REPLACE FUNCTION getCount(tableName text)
RETURNS integer 
AS 
$$
DECLARE 
	countOf integer;
	query text;
BEGIN
	query := 'select count(*) from ' || tableName;
	execute query into countOf;
	
	return countOf;
END;
$$  LANGUAGE plpgsql;

--select getCount('gender')

CREATE OR REPLACE FUNCTION getCountForAbitID(tableName text, aid text)
RETURNS integer 
AS 
$$
DECLARE 
	countOf integer;
	query text;
BEGIN
	query := 'select count(*) from ' || tableName || ' where id_abiturient = ' || aid;
	execute query into countOf;
	
	return countOf;
END;
$$  LANGUAGE plpgsql;

--select getCountForAbitID('abiturientbvi', '1')

CREATE OR REPLACE FUNCTION getAllAbiturients() returns table (aid integer, SName text, FName text, MName text)
    AS $$ SELECT aid, SName, Fname, MName from Abiturient order by aid $$
    LANGUAGE SQL;
	
--select * from getAllAbiturients()
--DROP FUNCTION getAbiturientGeneralInfoByID(aid text);
CREATE OR REPLACE FUNCTION getAbiturientGeneralInfoByID(aid text) returns table (aid integer, SName text, FName text, MName text, Birthday Date, id_gender integer, id_nationality integer, registrationDate Date, id_returnReason integer, returnDate Date, needHostel integer, is_enrolled integer)
    AS $$ SELECT aid, SName, FName, MName, Birthday, id_gender, id_nationality, registrationDate, id_returnReason, returnDate, needHostel,is_enrolled from Abiturient where aid = aid $$
    LANGUAGE SQL;
	
--select * from getAbiturientGeneralInfoByID('1')

CREATE OR REPLACE FUNCTION deleteAbiturient(aid text)
RETURNS void 
AS 
$$
DECLARE 
	query text;
BEGIN
	query := 'delete from Abiturient where aid = ' || aid;
	execute query;
END;
$$  LANGUAGE plpgsql;

--select deleteAbiturient('2')

CREATE OR REPLACE FUNCTION getNamesFromTableOrderedById(tableName text)
RETURNS setof text
AS 
$$
DECLARE 
	query text;
BEGIN
	query := 'select name from ' || tableName || ' order by id';
	RETURN QUERY EXECUTE query;
END;
$$  LANGUAGE plpgsql;

--select * from getNamesFromTableOrderedById('gender')

CREATE OR REPLACE FUNCTION getAllFromTable(tableName text, name_id text)
RETURNS setof record
AS 
$$
DECLARE 
	query text;
BEGIN
	if (name_id is null) then
		query := 'select * from ' || tableName;
		RETURN QUERY EXECUTE query;
	else
		query := 'select * from ' || tableName || ' order by ' || name_id;
		RETURN QUERY EXECUTE query;
	end if;
END;
$$  LANGUAGE plpgsql;

--select * from getAllFromTable('gender', null)

CREATE OR REPLACE FUNCTION checkUser(login text, passwd text)
RETURNS integer 
AS 
$$
DECLARE 
	countOf integer;
	query text;
BEGIN
	query := 'select count(*) from Users where login like ''' || login || ''' and password like ''' || passwd || '''';
	execute query into countOf;
	
	return countOf;
END;
$$  LANGUAGE plpgsql

--select checkUser('admin', 'admin')
