package backend;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class ModelDBConnection {
	static String login;
	static String password;
	static String serverAddress;
	static String dbName;
	static Properties props;

	static Connection con = null;
	static CallableStatement cstmt = null;
	static ResultSet rset = null;
	static Statement stmt = null;

	static boolean DEBUG = false;

	//Задание параметров для подключения к серверу и БД
	public static void setConnectionParameters(String serverAddress, String dbName, String login,
			String password) {
		ModelDBConnection.serverAddress = serverAddress;
		ModelDBConnection.dbName = dbName;
		ModelDBConnection.login = login;
		ModelDBConnection.password = password;

		ModelDBConnection.con = null;
	}

	public static String getLogin() {
		return login;
	}

	public static String getPassword() {
		return password;
	}

	public static String getServerAddress() {
		return serverAddress;
	}

	public static String getDBName() {
		return dbName;
	}

	public static Connection getConnection() {
		return con;
	}

	//Подключение к БД с ранее заданными параметрами 
	public static boolean initConnection() {
		if (con == null) {
			try {
				String connectionUrl = "jdbc:postgresql://localhost:5433/postgres";
                                serverAddress = "localhost:5433";
                                dbName = "postgres";
                                        //"jdbc:postgresql://" + serverAddress 
					//	+ "/" + dbName;

				props = new Properties();
				props.setProperty("user", login);
				props.setProperty("password", password);
				con = DriverManager.getConnection(connectionUrl,"user","password"); //props);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	//Количество строк в заданной таблице tableName
	public static int getCount(String tableName) {
		if (initConnection()) {
			try {
				int count = 0;
				cstmt = con.prepareCall("{? = call getCount(?)}");

				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.setString(2, tableName);

				cstmt.execute();

				count = cstmt.getInt(1);
				// System.out.println(count);
				return count;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		return -1;
	}

	//Количество записей по конкретному абитуриенту aid в таблице tableName 
	//(например, можно узнать количество его ИД и пр.)
	public static int getCountForAbitID(String tableName, String aid) {
		if (initConnection()) {
			try {
				int count = 0;
				cstmt = con.prepareCall("{? = call getCountForAbitID(?,?)}");

				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.setString(2, tableName);
				cstmt.setString(3, aid);

				cstmt.execute();

				count = cstmt.getInt(1);
				// System.out.println(count);
				return count;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return -1;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}

		return -1;
	}

	//Получает список № и ФИО всех абитуриентов (для таблицы в главном окне)
	public static String[][] getAllAbiturients() {

		String[][] data = null;

		try {
			cstmt = con.prepareCall("{call getAllAbiturients}", 1004, 1007);

			rset = cstmt.executeQuery();

			int countStrings = rset.last() ? rset.getRow() : 0;
			rset.beforeFirst();

			if (countStrings > 0) {
				ResultSetMetaData rsmd = rset.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
				data = new String[countStrings][numberOfColumns];
				int curPos = 0;
				while (rset.next()) {
					for (int i = 0; i < numberOfColumns; i++) {
						if (rset.getObject(i + 1) != null) {
							if (rset.getObject(i + 1) instanceof Date) {
								SimpleDateFormat format = new SimpleDateFormat();
								format.applyPattern("yyyy-MM-dd");
								Date docDate = format.parse(rset.getObject(i + 1).toString());
								format.applyPattern("dd.MM.yyyy");
								data[curPos][i] = format.format(docDate);
							} else
								data[curPos][i] = rset.getObject(i + 1).toString();
						}
					}
					curPos++;
				}
			}
			cstmt.close();
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;

	}

	//Получает основную информацию по абитуриенту (для окна основной информации)
         
	public static String[] getAbiturientGeneralInfoByID(String aid) throws SQLException {
		try {
			cstmt = con.prepareCall("{call getAbiturientGeneralInfoByID(?)}", 1004, 1007);

			cstmt.setString(1, aid);

			rset = cstmt.executeQuery();

			ResultSetMetaData rsmd = rset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			String[] result = new String[numberOfColumns];
			for (int i = 0; i < result.length; i++)
				result[i] = "";
			result[0] = aid;
                        System.out.println(result[0]);
                        //System.out.println(rset.getR);
                        
 
			while (rset.next()) {
				for (int i = 1; i < numberOfColumns; i++) {
					if (rset.getObject(i + 1) != null)
						if (rset.getObject(i + 1) instanceof Date) {
							SimpleDateFormat format = new SimpleDateFormat();
							format.applyPattern("yyyy-MM-dd");
							Date docDate = format.parse(rset.getObject(i + 1).toString());
							format.applyPattern("dd.MM.yyyy");
							result[i] = format.format(docDate);
						} else
							result[i] = rset.getObject(i + 1).toString();
				}
			}
			cstmt.close();
			rset.close();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	//Добавление абитуриента по всем полям (16) или по отдельному набору (10)
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
        //были внесены изменения, обеспечивающие полное соответствие полей таблицы Абитуриент с полями, указанными в данной функции
	public static void insertAbiturient(String[] info) throws SQLException {
		String aid, SName, FName, MName, birthday, birthplace, id_gender, id_nationality, email, phoneNumbers,inn,
				needHostel, registrationDate, returnDate, id_returnReason, needSpecConditions, is_enrolled;//, snils;
		String query;

		query = aid = SName = FName = MName = birthday = birthplace = id_gender = id_nationality = email = phoneNumbers = needHostel = registrationDate = returnDate = id_returnReason = needSpecConditions = is_enrolled = inn = null;

		switch (info.length) {
		case 17:
			aid = info[0];
			SName = "'" + info[1] + "'";
			FName = "'" + info[2] + "'";
			MName = "'" + info[3] + "'";
			birthday = "'" + info[4] + "'";
			birthplace = "'" + info[5] + "'";
			id_gender = info[6];
			id_nationality = info[7];
			email = "'" + info[8] + "'";
			phoneNumbers = "'" + info[9] + "'";
                        inn = "'" + info[10] + "'";
			needHostel = info[11];
			registrationDate = "'" + info[12] + "'";
			returnDate = "'" + info[13] + "'";
			id_returnReason = info[14];
			needSpecConditions = info[15];
			is_enrolled = info[16];

			query = "insert into Abiturient (aid, SName, FName, MName, Birthday, Birthplace, id_gender, id_nationality, email, phoneNumbers, inn,needHostel, registrationDate, returnDate, id_returnReason, needSpecConditions, is_enrolled) Values (" 
					+ aid + ", " + SName + ", " + FName + ", " + MName + ", "
					+ birthday + ", " + birthplace + ", " + id_gender + ", " + id_nationality + ", " + email + ", "
					+ phoneNumbers + ", " + inn +", " + needHostel + ", " + registrationDate + ", " + returnDate + ", "
					+ id_returnReason + ", " + needSpecConditions + ", " + is_enrolled + ");";

			break;
		case 12:
                    //System.out.println("this branch");
			aid = info[0];
			SName = "'" + info[1] + "'";
			FName = "'" + info[2] + "'";
			MName = "'" + info[3] + "'";
			birthday = "'" + info[4] + "'";
                        //birthplace = "'" + info[5] + "'";
			id_gender = info[5];
			id_nationality = info[6];
                        needHostel =  info[7] ;
			registrationDate = "'" + info[8] + "'";
                        returnDate = "'" + info[9] + "'";
                        id_returnReason = info[10];
			 
			is_enrolled = info[11];

			query = "insert into Abiturient (aid, SName, FName, MName, Birthday, id_gender, id_nationality, needHostel, registrationDate, returnDate, id_returnReason, is_enrolled) Values ("
					+ aid + ", " + SName + ", " + FName + ", " + MName + ", " + birthday + ", " 
                                + id_gender + ", "+ id_nationality + ", " + needHostel + ", "  + registrationDate + ", " 
                                +returnDate + ", " + id_returnReason + ", " + is_enrolled + ");";

			break;
		}

		if (initConnection()) {
			query = query.replaceAll("null", "'null'");
                                 
			stmt = con.createStatement();
			stmt.executeUpdate(query);

			stmt.close();
		}
	}

	//Редактирование абитуриента по всем полям (16) или по отдельному набору (8 or 11)
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static void editAbiturient(String[] info) throws SQLException {

		String aid, SName, FName, MName, birthday, birthplace, id_gender, id_nationality, email, phoneNumbers,
				needHostel, registrationDate, returnDate, id_returnReason, needSpecConditions, is_enrolled, snils;
		String query;

		query = aid = SName = FName = MName = birthday = birthplace = id_gender = id_nationality = email = phoneNumbers = needHostel = registrationDate = returnDate = id_returnReason = needSpecConditions = is_enrolled = null;

		switch (info.length) {
		case 16:
			aid = info[0];
			SName = "'" + info[1] + "'";
			FName = "'" + info[2] + "'";
			MName = "'" + info[3] + "'";
			birthday = "'" + info[4] + "'";
			birthplace = "'" + info[5] + "'";
			id_gender = info[6];
			id_nationality = info[7];
			email = "'" + info[8] + "'";
			phoneNumbers = "'" + info[9] + "'";
			needHostel = info[10];
			registrationDate = "'" + info[11] + "'";
			returnDate = "'" + info[12] + "'";
			id_returnReason = info[13];
			needSpecConditions = info[14];
			is_enrolled = info[15];

			query = "update Abiturient set SName = " + SName + ", FName = " + FName + ", MName = " + MName
					+ ", Birthday = " + birthday + ", Birthplace = " + birthplace + ", id_gender = " + id_gender
					+ ", id_nationality = " + id_nationality + ", email = " + email + ", phoneNumbers = " + phoneNumbers
					+ ", needHostel = " + needHostel + ", registrationDate = " + registrationDate + ", returnDate = "
					+ returnDate + ", id_returnReason = " + id_returnReason + ", needSpecConditions = "
					+ needSpecConditions + ", is_enrolled = " + is_enrolled + " where aid = " + aid + ";";

			break;
                
		case 8:
			aid = info[0];
			SName = "'" + info[1] + "'";
			FName = "'" + info[2] + "'";
			MName = "'" + info[3] + "'";
			birthday = "'" + info[4] + "'";
			id_gender = info[5];
			id_nationality = info[6];
			registrationDate = "'" + info[7] + "'";

			query = "update Abiturient set SName = " + SName + ", FName = " + FName + ", MName = " + MName
					+ ", Birthday = " + birthday + ", id_gender = " + id_gender + ", id_nationality = " + id_nationality
					+ ", registrationDate = " + registrationDate + " where aid = " + aid + ";";

			break;
		case 12:
                    aid = info[0];
			SName = "'" + info[1] + "'";
			FName = "'" + info[2] + "'";
			MName = "'" + info[3] + "'";
			birthday = "'" + info[4] + "'";
                        //birthplace = "'" + info[5] + "'";
			id_gender = info[5];
			id_nationality = info[6];
                        needHostel =  info[7] ;
			registrationDate = "'" + info[8] + "'";
                        returnDate = "'" + info[9] + "'";
                        id_returnReason = info[10];
			 
			is_enrolled = info[11];

			query = "update Abiturient set SName = " + SName + ", " + FName + ", " + MName + ", " + birthday + ", " 
                                + id_gender + ", "+ id_nationality + ", " + needHostel + ", "  + registrationDate + ", " 
                                +returnDate + ", " + id_returnReason + ", " + is_enrolled + " where aid = " + aid + ");";

			break;
			 
			
			 
		}

		if (initConnection()) {
			stmt = con.createStatement();
			stmt.executeUpdate(query);

			stmt.close();
		}
	}

	//Удаление убитуриента и информации по нему по aid
	public static void deleteAbiturient(String aid) throws SQLException {
		cstmt = con.prepareCall("{call deleteAbiturient(?)}");
		cstmt.setString(1, aid);
		cstmt.execute();
		cstmt.close();
	}

	//Извлечение содержимого (названий чего-либо) из таблиц-справочников
	public static String[] getNamesFromTableOrderedById(String table) {
		try {
			cstmt = con.prepareCall("{call getNamesFromTableOrderedById(?)}", 1004, 1007);

			cstmt.setString(1, table);

			rset = cstmt.executeQuery();

			int countStrings = rset.last() ? rset.getRow() : 0;
			rset.beforeFirst();

			int i = 0;
			String[] listElems = new String[countStrings];

			while (rset.next()) {
				listElems[i] = rset.getString(1);
				System.out.println(listElems[i]);
				i++;
			}

			cstmt.close();
			rset.close();

			return listElems;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//Извлечение всех строк из заданной таблицы, отсортированных по id
	public static String[][] getAllFromTableOrderedById(String table) throws SQLException {
		String id = "id";

		switch (table) {
		case "Abiturient":
			id = "aid";
			break;
		case "AbiturientAchievement":
		case "AbiturientEducation":
		case "AbiturientEntranceExam":
		case "AbiturientPassport":
		case "AbiturientAdress":
		case "AbiturientCompetitiveGroups":
		case "AbiturientDocumentsFor100balls":
		case "AbiturientBVI":
		case "AbiturientDocumentBVI":
		case "AbiturientQuote":
		case "AbiturientDocQuote":
		case "AbiturientPrefRight":
		case "AbiturientDocPrefRight":
		case "AbiturientAdditionalInf":
			id = "id_abiturient";
			break;
		case "AcceptancePlan":
			id = "id_speciality";
			break;
		case "Users":
			id = "login";
			break;
		default:
			id = "id";
		}

		String[][] data = null;

		try {
			cstmt = con.prepareCall("select * from " + table + " order by " + id, 1004, 1007);
			rset = cstmt.executeQuery();

			int countStrings = rset.last() ? rset.getRow() : 0;
			rset.beforeFirst();

			if (countStrings > 0) {
				ResultSetMetaData rsmd = rset.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
				data = new String[countStrings][numberOfColumns];
				int curPos = 0;
				while (rset.next()) {
					for (int i = 0; i < numberOfColumns; i++) {
						if (rset.getObject(i + 1) != null) {
							data[curPos][i] = rset.getObject(i + 1).toString();
						}
						System.out.println(data[curPos][i]);
					}
					curPos++;
				}
			}
			cstmt.close();
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	//Получение информации из таблицы по паре id
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static String[] getElementFromTableByIDs(String table, String[] data) {
		try {
			String id1 = "id_abiturient", id2 = "";
			switch (table) {
			case "AbiturientAchievement":
				id1 = "id_abiturient";
				id2 = "id_individualAchievement";
				break;
			case "AbiturientEntranceExam":
				id1 = "id_abiturient";
				id2 = "id_entranceExam";
				break;
			case "AbiturientDocumentsFor100balls":
				id1 = "id_abiturient";
				id2 = "id_olympiad";
				break;
			}

			String query = "select * from " + table + " where " + id1 + " = " + data[0] + " and " + id2 + " = " + data[1]
					+ ";";
			
			cstmt = con.prepareCall(query, 1004, 1007);

			rset = cstmt.executeQuery();

			ResultSetMetaData rsmd = rset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			String[] result = new String[numberOfColumns];
			for (int i = 0; i < result.length; i++)
				result[i] = "";

			while (rset.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					if (rset.getObject(i + 1) != null)
						result[i] = rset.getObject(i + 1).toString();
					System.out.println("Read: " + result[i]);
				}
			}
			cstmt.close();
			rset.close();
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	//Обновление данных в таблице по заданному id
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static void updateElementInTableById(String table, String[] data) throws SQLException {
		String id = "id";
		switch (table) {
		case "Abiturient":
			id = "aid";
			break;
		case "AbiturientAchievement":
		case "AbiturientEducation":
		case "AbiturientEntranceExam":
		case "AbiturientPassport":
		case "AbiturientAdress":
		case "AbiturientCompetitiveGroups":
		case "AbiturientDocumentsFor100balls":
		case "AbiturientBVI":
		case "AbiturientDocumentBVI":
		case "AbiturientQuote":
		case "AbiturientDocQuote":
		case "AbiturientPrefRight":
		case "AbiturientDocPrefRight":
		case "AbiturientAdditionalInf":
			id = "id_abiturient";
			break;
		case "AcceptancePlan":
			id = "id_speciality";
			break;
		case "Users":
			id = "login";
			break;
		default:
			id = "id";
		}

		String query = "select * from " + table + " where " + id + " = " + (!table.equals("Users") ? data[0] : "'" + data[0] + "'") + ";";
		System.out.println(query);
		int numberOfColumns = 0;
		if (initConnection()) {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			ResultSetMetaData rsmd = rset.getMetaData();
			numberOfColumns = rsmd.getColumnCount();

			int countStrings = 0;
			while (rset.next()) {
				countStrings++;
			}

			if (countStrings > 0) {
				query = "update " + table + " set ";
				for (int i = 1; i < numberOfColumns; i++) {
					if (i == numberOfColumns - 1)
						query = query + rsmd.getColumnLabel(i + 1) + " = " + "'" + data[i] + "'";
					else
						query = query + rsmd.getColumnLabel(i + 1) + " = " + "'" + data[i] + "'" + ", ";
				}
				query = query + " where " + id + " = " + (!table.equals("Users") ? data[0] : "'" + data[0] + "'")  + ";";
				if (table.equals("Users")) {
					query += (" DROP LOGIN " + data[0] + "; CREATE LOGIN " + data[0] + " WITH PASSWORD = '" + data[1] + "'; ALTER SERVER ROLE sysadmin ADD MEMBER " + data[0]);
				}
			} else {
				query = "insert into " + table + " values (" + (!table.equals("Users") ? data[0] : "'" + data[0] + "'")  + ", ";
				for (int i = 1; i < numberOfColumns; i++) {
					if (i == numberOfColumns - 1)
						query = query + "'" + data[i] + "')";
					else
						query = query + "'" + data[i] + "'" + ", ";
				}
				if (table.equals("Users")) {
					query += ("; CREATE LOGIN " + data[0] + " WITH PASSWORD = '" + data[1] + "'; ALTER SERVER ROLE sysadmin ADD MEMBER " + data[0]);
				}
			}
			stmt.close();
			rset.close();
		}

		query = query.replaceAll("'null'", "null");
		System.out.println(query);

		stmt = con.createStatement();
		stmt.executeUpdate(query);

		stmt.close();
		rset.close();
	}

	//Обновление данных в таблице по нескольким полям (идущим подряд, начиная с 1-ого)
	//Полезно для обновления, к примеру, плана примема или обновления информации в разрезе конкурсных групп абитуриента
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static void updateElementInTableByExpression(String table, String[] data, int countOfExprParams)
			throws SQLException {
		String id = "id";
		switch (table) {
		case "Abiturient":
			id = "aid";
			break;
		case "AbiturientAchievement":
		case "AbiturientEducation":
		case "AbiturientEntranceExam":
		case "AbiturientPassport":
		case "AbiturientAdress":
		case "AbiturientCompetitiveGroups":
		case "AbiturientDocumentsFor100balls":
		case "AbiturientBVI":
		case "AbiturientDocumentBVI":
		case "AbiturientQuote":
		case "AbiturientDocQuote":
		case "AbiturientPrefRight":
		case "AbiturientDocPrefRight":
		case "AbiturientAdditionalInf":
			id = "id_abiturient";
			break;
		case "AcceptancePlan":
			id = "id_speciality";
			break;
		case "Users":
			id = "login";
			break;
		default:
			id = "id";
		}

		String query = "select * from " + table + " where " + id + " = 0;";
		System.out.println(query);
		int numberOfColumns = 0;
		if (initConnection()) {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			ResultSetMetaData rsmd = rset.getMetaData();
			numberOfColumns = rsmd.getColumnCount();

			int countStrings = 0;
			query = "select * from " + table + " where ";
			for (int i = 0; i < countOfExprParams; i++)
				if (i == countOfExprParams - 1)
					query = query + rsmd.getColumnLabel(i + 1) + (data[i] != null ? " = " + data[i] : " is " + data[i]) + ";";
				else
					query = query + rsmd.getColumnLabel(i + 1) + (data[i] != null ? " = " + data[i] : " is " + data[i]) + " and ";
			System.out.println(query);
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				countStrings++;
			}

			if (countStrings > 0) {
				query = "update " + table + " set ";
				for (int i = 1; i < numberOfColumns; i++) {
					if (i == numberOfColumns - 1)
						query = query + rsmd.getColumnLabel(i + 1) + " = " + "'" + data[i] + "'";
					else
						query = query + rsmd.getColumnLabel(i + 1) + " = " + "'" + data[i] + "'" + ", ";
				}
				query = query + " where ";
				for (int i = 0; i < countOfExprParams; i++)
					if (i == countOfExprParams - 1)
						query = query + rsmd.getColumnLabel(i + 1) + (data[i] != null ? " = " + data[i] : " is " + data[i]) + ";";
					else
						query = query + rsmd.getColumnLabel(i + 1) + (data[i] != null ? " = " + data[i] : " is " + data[i]) + " and ";
			} else {
				query = "insert into " + table + " values (" + data[0] + ", ";
				for (int i = 1; i < numberOfColumns; i++) {
					if (i == numberOfColumns - 1)
						query = query + "'" + data[i] + "')";
					else
						query = query + "'" + data[i] + "'" + ", ";
				}
			}
			stmt.close();
			rset.close();
		}
		query = query.replaceAll("'null'", "null");
		System.out.println(query);

		stmt = con.createStatement();
		stmt.executeUpdate(query);

		stmt.close();
		rset.close();
	}

	//Обновление информации по паре id (например, ИД для конкретного абитуриента)
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static void updateElementInTableByIds(String table, String[] data) throws SQLException {
		String id1 = "id_abiturient", id2 = "";
		switch (table) {
		case "AbiturientAchievement":
			id1 = "id_abiturient";
			id2 = "id_individualAchievement";
			break;
		case "AbiturientEntranceExam":
			id1 = "id_abiturient";
			id2 = "id_entranceExam";
			break;
		case "AbiturientDocumentsFor100balls":
			id1 = "id_abiturient";
			id2 = "id_olympiad";
			break;
		}

		String query = "select * from " + table + " where " + id1 + " = " + data[0] + " and " + id2 + " = " + data[1]
				+ ";";
		System.out.println(query);
		int numberOfColumns = 0;
		if (initConnection()) {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			ResultSetMetaData rsmd = rset.getMetaData();
			numberOfColumns = rsmd.getColumnCount();

			int countStrings = 0;
			while (rset.next()) {
				countStrings++;
			}

			if (countStrings > 0) {
				query = "update " + table + " set ";
				for (int i = 1; i < numberOfColumns; i++) {
					if (i == numberOfColumns - 1)
						query = query + rsmd.getColumnLabel(i + 1) + " = " + "'" + data[i] + "'";
					else
						query = query + rsmd.getColumnLabel(i + 1) + " = " + "'" + data[i] + "'" + ", ";
				}
				query = query + " where " + id1 + " = " + data[0] + " and " + id2 + " = " + data[1] + ";";
			} else {
				query = "insert into " + table + " values (" + data[0] + ", ";
				for (int i = 1; i < numberOfColumns; i++) {
					if (i == numberOfColumns - 1)
						query = query + "'" + data[i] + "')";
					else
						query = query + "'" + data[i] + "'" + ", ";
				}
			}
			stmt.close();
			rset.close();
		}
		query = query.replaceAll("'null'", "null");
		System.out.println(query);

		stmt = con.createStatement();
		stmt.executeUpdate(query);

		stmt.close();
		rset.close();
	}

	//Удаление из таблицы по id
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static void deleteElementInTableById(String table, String data) throws SQLException {
		String id = "id";
		switch (table) {
		case "Abiturient":
			id = "aid";
			break;
		case "AbiturientAchievement":
		case "AbiturientEducation":
		case "AbiturientEntranceExam":
		case "AbiturientPassport":
		case "AbiturientAdress":
		case "AbiturientCompetitiveGroups":
		case "AbiturientDocumentsFor100balls":
		case "AbiturientBVI":
		case "AbiturientDocumentBVI":
		case "AbiturientQuote":
		case "AbiturientDocQuote":
		case "AbiturientPrefRight":
		case "AbiturientDocPrefRight":
		case "AbiturientAdditionalInf":
			id = "id_abiturient";
			break;
		case "AcceptancePlan":
			id = "id_speciality";
			break;
		case "Users":
			id = "login";
			break;
		default:
			id = "id";
		}

		cstmt = con.prepareCall("delete from " + table + " where " + id + " = " + (!table.equals("Users") ? data : "'" + data + "'"));

		cstmt.execute();

		cstmt.close();
	}

	//Удаление по нескольким полям (идущим подряд, начиная с 1-ого)
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static void deleteElementInTableByExpression(String table, String[] data, int countOfExprParams)
			throws SQLException {
		String id = "id_abiturient", query;
		switch (table) {
		case "Abiturient":
			id = "aid";
			break;
		case "AbiturientAchievement":
		case "AbiturientEducation":
		case "AbiturientEntranceExam":
		case "AbiturientPassport":
		case "AbiturientAdress":
		case "AbiturientCompetitiveGroups":
		case "AbiturientDocumentsFor100balls":
		case "AbiturientBVI":
		case "AbiturientDocumentBVI":
		case "AbiturientQuote":
		case "AbiturientDocQuote":
		case "AbiturientPrefRight":
		case "AbiturientDocPrefRight":
		case "AbiturientAdditionalInf":
			id = "id_abiturient";
			break;
		case "AcceptancePlan":
			id = "id_speciality";
			break;
		case "Users":
			id = "login";
			break;
		default:
			id = "id";
		}

		if (countOfExprParams > 0) {
			query = "select * from " + table + " where " + id + " = 0;";
			System.out.println(query);

			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			ResultSetMetaData rsmd = rset.getMetaData();

			query = "delete from " + table + " where ";
			for (int i = 0; i < countOfExprParams; i++)
				if (i == countOfExprParams - 1)
					query = query + rsmd.getColumnLabel(i + 1) + " = " + data[i] + ";";
				else
					query = query + rsmd.getColumnLabel(i + 1) + " = " + data[i] + " and ";
			System.out.println(query);
		} else
			query = "delete from " + table;

		query = query.replaceAll("= null", "is null");
		stmt = con.createStatement();
		stmt.executeUpdate(query);

		stmt.close();
	}

	//Удаление по паре id (например, ИД для конкретного абитуриента)
	//!!! НЕ ТЕСТИРОВАЛАСЬ - ПРОВЕРИТЬ КОРРЕКТНОСТЬ РАБОТЫ
	public static void deleteElementInTableByIds(String table, String[] data) throws SQLException {
		String id1 = "aid_abiturient", id2 = "";
		switch (table) {
		case "AbiturientAchievement":
			id1 = "id_abiturient";
			id2 = "id_individualAchievement";
			break;
		case "AbiturientEntranceExam":
			id1 = "id_abiturient";
			id2 = "id_entranceExam";
			break;
		case "AbiturientDocumentsFor100balls":
			id1 = "id_abiturient";
			id2 = "id_olympiad";
			break;
		}

		cstmt = con.prepareCall("{call deleteElementInTableByIds(?, ?, ?, ?, ?)}");

		cstmt.setString(1, table);
		cstmt.setString(2, id1);
		cstmt.setString(3, data[0]);
		cstmt.setString(4, id2);
		cstmt.setString(5, data[1]);

		cstmt.execute();

		cstmt.close();
	}

	//Получаем сведения по всем достижениям Абитуриента
	//2 версии - возвращаем либо все столбцы, либо краткую информацию, нужную для таблицы (название достижения и балл за него)
	public static String[][] getAllAchievmentsByAbiturientId(String aid, boolean needAllColumns) {
		String[][] data = null;

		try {
			if (needAllColumns)
				cstmt = con.prepareCall("select * from AbiturientAchievement where id_abiturient = " + aid, 1004, 1007);
			else
				cstmt = con.prepareCall("select name, score from AbiturientAchievement, IndividualAchievement where IndividualAchievement.id = AbiturientAchievement.id_individualAchievement and id_abiturient = " + aid, 1004, 1007);

			rset = cstmt.executeQuery();

			int countStrings = rset.last() ? rset.getRow() : 0;
			rset.beforeFirst();

			if (countStrings > 0) {
				ResultSetMetaData rsmd = rset.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
				data = new String[countStrings][numberOfColumns];
				int curPos = 0;
				while (rset.next()) {
					for (int i = 0; i < numberOfColumns; i++) {
						if (rset.getObject(i + 1) != null)
							if (rset.getObject(i + 1) instanceof Date) {
								SimpleDateFormat format = new SimpleDateFormat();
								format.applyPattern("yyyy-MM-dd");
								Date docDate = format.parse(rset.getObject(i + 1).toString());
								format.applyPattern("dd.MM.yyyy");
								data[curPos][i] = format.format(docDate);
							} else
								data[curPos][i] = rset.getObject(i + 1).toString();
					}
					curPos++;
				}
			}
			cstmt.close();
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return data;
	}

	//Получаем сведения по всем вступительным испытаниям Абитуриента
	//2 версии - возвращаем либо все столбцы, либо краткую информацию, нужную для таблицы
	public static String[][] getAllEntranceTestsResultsByAbiturientId(String aid, boolean need_all_columns) {
		String[][] data = null;

		try {
			if (need_all_columns) //Случай, когда мы уже ввели данные по ВИ
				cstmt = con.prepareCall("select * from AbiturientEntranceExam where id_abiturient = " + aid, 1004, 1007);
			else //если же еще не вводили, отобразить все их названия согласно справочнику
				cstmt = con.prepareCall("select EntranceExam.name, null, null, null, null, null, null from EntranceExam", 1004, 1007);

			rset = cstmt.executeQuery();

			int countStrings = rset.last() ? rset.getRow() : 0;
			rset.beforeFirst();

			if (countStrings > 0) {
				ResultSetMetaData rsmd = rset.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
				data = new String[countStrings][numberOfColumns];
				int curPos = 0;
				while (rset.next()) {
					for (int i = 0; i < numberOfColumns; i++) {
						if (rset.getObject(i + 1) != null)
							if (rset.getObject(i + 1) instanceof Date) {
								SimpleDateFormat format = new SimpleDateFormat();
								format.applyPattern("yyyy-MM-dd");
								Date docDate = format.parse(rset.getObject(i + 1).toString());
								format.applyPattern("dd.MM.yyyy");
								data[curPos][i] = format.format(docDate);
							} else
								data[curPos][i] = rset.getObject(i + 1).toString();
					}
					curPos++;
				}
			}
			cstmt.close();
			rset.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	//Получаем все конкурсные группы абитуриента
	public static String[][] getAllCompetitiveGroupsByAbiturientId(String aid) {
		String[][] data = null;

		try {
			cstmt = con.prepareCall("select	id_abiturient, id_specialty, id_competitiveGroup, id_formOfEducation, id_targetOrganization, "
					+ " BVIright, quotaRight, preferentialRight, competitiveScore, sumOfIndividAchievmentScores, originalsReceivedDate, agreementReceivedDate, is_enrolled, "
					+ " Speciality.name, CompetitiveGroup.name, FormOfEducation.name, priority"
					+ " from	Abiturient, AbiturientCompetitiveGroups, Speciality, CompetitiveGroup, FormOfEducation"
					+ " where	Abiturient.aid = AbiturientCompetitiveGroups.id_abiturient and "
					+ " Speciality.id = AbiturientCompetitiveGroups.id_specialty and "
					+ " CompetitiveGroup.id = AbiturientCompetitiveGroups.id_competitiveGroup and"
					+ " FormOfEducation.id = AbiturientCompetitiveGroups.id_formOfEducation and id_abiturient = " + aid
					+ " order by priority", 1004, 1007);

			rset = cstmt.executeQuery();

			int countStrings = rset.last() ? rset.getRow() : 0;
			rset.beforeFirst();

			if (countStrings > 0) {
				ResultSetMetaData rsmd = rset.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
				data = new String[countStrings][numberOfColumns];
				int curPos = 0;
				while (rset.next()) {
					for (int i = 0; i < numberOfColumns; i++) {
						if (rset.getObject(i + 1) != null)
							data[curPos][i] = rset.getObject(i + 1).toString();
					}
					curPos++;
				}
			}
			cstmt.close();
			rset.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}

	//Получаем паспортные данные абитуриента
	public static String[] getAbiturientPassportByID(String aid) throws SQLException {
		try {
			cstmt = con.prepareCall("select id_abiturient, id_typePassport, series, number, issued_by, dateOf_issue, Birthplace "
					+ " from Abiturient, AbiturientPassport where Abiturient.aid = AbiturientPassport.id_abiturient "
					+ " and aid_abiturient = " + aid, 1004, 1007);

			rset = cstmt.executeQuery();

			ResultSetMetaData rsmd = rset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			String[] result = new String[numberOfColumns];
			result[0] = aid;

			while (rset.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					if (rset.getObject(i + 1) != null)
						if (rset.getObject(i + 1) instanceof Date) {
							SimpleDateFormat format = new SimpleDateFormat();
							format.applyPattern("yyyy-MM-dd");
							Date docDate = format.parse(rset.getObject(i + 1).toString());
							format.applyPattern("dd.MM.yyyy");
							result[i] = format.format(docDate);
						} else
							result[i] = rset.getObject(i + 1).toString();
				}
			}
			cstmt.close();
			rset.close();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	//Обноовление паспортных данных
	public static void updateAbiturientPassportByID(String aid, String[] data) throws SQLException {
		try {
			String query = "update Abiturient set Birthplace = '" + data[5] + "' where aid = " + aid + ";";

			String[] abiturientPassportInfo = new String[6];
			abiturientPassportInfo[0] = aid;
			for (int i = 1; i < abiturientPassportInfo.length; i++)
				abiturientPassportInfo[i] = data[i - 1];

			ModelDBConnection.updateElementInTableById("AbiturientPassport", abiturientPassportInfo);
			query = query.replaceAll("'null'", "null");
			System.out.println(query);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Получаем адрес и контакты абитуриента
	public static String[] getAbiturientAddressAndContactsByID(String aid) throws SQLException {
		try {
			cstmt = con.prepareCall("select id_abiturient, id_region, id_typeSettlement, postcode, adress, email, phoneNumbers"
					+ " from Abiturient, AbiturientAdress where Abiturient.aid = AbiturientAdress.id_abiturient"
					+ " and aid_abiturient = " + aid, 1004, 1007);

			rset = cstmt.executeQuery();

			ResultSetMetaData rsmd = rset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			String[] result = new String[numberOfColumns];
			result[0] = aid;

			while (rset.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					if (rset.getObject(i + 1) != null)
						if (rset.getObject(i + 1) instanceof Date) {
							SimpleDateFormat format = new SimpleDateFormat();
							format.applyPattern("yyyy-MM-dd");
							Date docDate = format.parse(rset.getObject(i + 1).toString());
							format.applyPattern("dd.MM.yyyy");
							result[i] = format.format(docDate);
						} else
							result[i] = rset.getObject(i + 1).toString();
				}
			}
			cstmt.close();
			rset.close();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	//Обновление адреса и контактов
	public static void updateAbiturientAddressAndContactsByID(String aid, String[] data) throws SQLException {
		try {
			String query = "update Abiturient set email = '" + data[4] + "', phoneNumbers = '" + data[5]
					+ "' where aid = " + aid + ";";

			String[] abiturientAddressInfo = new String[5];
			abiturientAddressInfo[0] = aid;
			for (int i = 1; i < abiturientAddressInfo.length; i++)
				abiturientAddressInfo[i] = data[i - 1];

			ModelDBConnection.updateElementInTableById("AbiturientAdress", abiturientAddressInfo);
			query = query.replaceAll("'null'", "null");
			System.out.println(query);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Получаем информацию по образованию
	public static String[] getAbiturientEducationByID(String aid) throws SQLException {
		try {
			cstmt = con.prepareCall("select * from AbiturientEducation where id_abiturient = " + aid, 1004, 1007);

			rset = cstmt.executeQuery();

			ResultSetMetaData rsmd = rset.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			String[] result = new String[numberOfColumns];
			result[0] = aid;

			while (rset.next()) {
				for (int i = 0; i < numberOfColumns; i++) {
					if (rset.getObject(i + 1) != null)
						if (rset.getObject(i + 1) instanceof Date) {
							SimpleDateFormat format = new SimpleDateFormat();
							format.applyPattern("yyyy-MM-dd");
							Date docDate = format.parse(rset.getObject(i + 1).toString());
							format.applyPattern("dd.MM.yyyy");
							result[i] = format.format(docDate);
						} else
							result[i] = rset.getObject(i + 1).toString();
				}
			}
			cstmt.close();
			rset.close();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public static void updateAbiturientEducationByID(String[] data) throws SQLException {
		try {
			ModelDBConnection.updateElementInTableById("AbiturientEducation", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateAbiturientIndividualAchivementByID(String[] data) throws SQLException {
		try {
			ModelDBConnection.updateElementInTableByIds("AbiturientAchievement", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateAbiturientEntranceTestsResultsByID(String[] data) throws SQLException {
		try {
			String query = "update Abiturient set needSpecConditions = '" + data[data.length - 1] + "' where aid = "
					+ data[0] + ";";

			String[] abiturientEntranceTestsResultsInfo = new String[data.length - 1];
			for (int i = 0; i < abiturientEntranceTestsResultsInfo.length; i++)
				abiturientEntranceTestsResultsInfo[i] = data[i];

			ModelDBConnection.updateElementInTableByIds("AbiturientEntranceExam", abiturientEntranceTestsResultsInfo);
			System.out.println(query);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean needAbiturientSpecialConditionsByID(String aid) {
		boolean state = false;
		String query = "select needSpecConditions from Abiturient where aid = " + aid + ";";

		System.out.println(query);
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				state = rset.getBoolean(1);
			}

			stmt.close();
			rset.close();
			return state;
		} catch (Exception e) {
			e.printStackTrace();
			return state;
		}
	}

	public static void updateAbiturientCompetitiveGroupByID(String[] data) throws SQLException {
		try {
			ModelDBConnection.updateElementInTableByExpression("AbiturientCompetitiveGroups", data, 8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteAbiturientCompetitiveGroupByID(String aid, String[] data) throws SQLException {
		deleteElementInTableByExpression("AbiturientCompetitiveGroups", data, 8);
	}

	/*public static int getFreeNumberInGroupByExam(String idEntranceTest, String testGroup) {
		if (initConnection()) {
			try {
				int freeNumber = 0;
				cstmt = con.prepareCall("{? = call getFreeNumberInGroup(?,?)}");
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.setString(2, idEntranceTest);
				cstmt.setString(3, testGroup);
				cstmt.execute();
				freeNumber = cstmt.getInt(1);
				// System.out.println(count);
				return freeNumber;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return -1;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		return -1;
	}
	public static String[][] getAdmissionPlan() {
		String[][] data = null;
		try {
			cstmt = con.prepareCall("{call getAdmissionPlan()}", 1004, 1007);
			rset = cstmt.executeQuery();
			int countStrings = rset.last() ? rset.getRow() : 0;
			rset.beforeFirst();
			if (countStrings > 0) {
				ResultSetMetaData rsmd = rset.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();
				data = new String[countStrings][numberOfColumns];
				int curPos = 0;
				while (rset.next()) {
					for (int i = 0; i < numberOfColumns; i++) {
						if (rset.getObject(i + 1) != null)
							data[curPos][i] = rset.getObject(i + 1).toString();
					}
					curPos++;
				}
			}
			cstmt.close();
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}*/

	public static int checkUser(String login, String password) {
		System.out.println(login + " " + password);
		if (initConnection()) {
			try {
				int result;
				cstmt = con.prepareCall("{? = call checkUser(?, ?)}");

				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.setString(2, login);
				cstmt.setString(3, password);

				cstmt.execute();

				result = cstmt.getInt(1);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				MessageProcessing.displayErrorMessage(null, e);
				return 0;
			}
		}
		return 0;
	}

	/*public static void updateCompetitiveBallsByID(String aid) throws SQLException {
		if (initConnection()) {
			cstmt = con.prepareCall("{call updateCompetitiveBallsByID(?)}");
			cstmt.setString(1, aid);
			cstmt.execute();
			cstmt.close();
		}
	}*/


	public static void main(String[] args) throws Exception {
		System.out.println("Test of backend");
		
		setConnectionParameters("localhost", "Abiturient", "postgres", "grandopera");
		initConnection();
		
		System.out.println(getCount("gender"));
		System.out.println(getCountForAbitID("abiturientbvi", "1"));
		System.out.println(getAllAbiturients());
		System.out.println(getAbiturientGeneralInfoByID("1"));
		System.out.println(getNamesFromTableOrderedById("gender"));
		getAllFromTableOrderedById("gender");
}
}
     
