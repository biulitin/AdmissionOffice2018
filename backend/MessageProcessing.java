﻿package backend;

import java.awt.Component;

import javax.swing.JOptionPane;

public abstract class MessageProcessing {
	public static void displaySuccessMessage(Component parent, int messageType) {
		String message = null, titleMessage = null;

		switch (messageType) {
		case 1:
			titleMessage = "Результат добавления абитуриента";
			message = "Абитуриент успешно добавлен!";
			break;
		case 2:
			titleMessage = "Результат редактирования абитуриента";
			message = "Данные успешно изменены!";
			break;
		case 3:
			titleMessage = "Результат удаления абитуриента";
			message = "Абитуриент успешно удален из базы!";
			break;
		case 4:
			titleMessage = "Результат редактирования справочника";
			message = "Данные успешно изменены!";
			break;
		case 5:
			titleMessage = "Результат редактирования справочника";
			message = "Данные успешно удалены!";
			break;
		case 6:
			titleMessage = "Результат редактирования паспортных данных";
			message = "Данные успешно сохранены!";
			break;
		case 7:
			titleMessage = "Результат редактирования контактной информации";
			message = "Данные успешно сохранены!";
			break;
		case 8:
			titleMessage = "Результат редактирования информации по образованию";
			message = "Данные успешно сохранены!";
			break;
		case 9:
			titleMessage = "Результат редактирования вступительных испытаний";
			message = "Данные успешно сохранены!";
			break;
		case 10:
			titleMessage = "Результат редактирования индивидуальных достижений";
			message = "Данные успешно сохранены!";
			break;
		case 11:
			titleMessage = "Результат добавления конкурсной группы";
			message = "Конкурсная группа успешно добавлена!";
			break;
		case 12:
			titleMessage = "Результат удаления конкурсной группы";
			message = "Конкурсная группа успешно удалена!";
			break;
		case 13:
			titleMessage = "Результат редактирования конкурсной группы";
			message = "Изменения в конкурсной группе успешно сохранены!";
			break;
		case 14:
			titleMessage = "Результат редактирования сведений по аккредитации";
			message = "Данные успешно сохранены!";
			break;
		}

		JOptionPane.showMessageDialog(parent, message, titleMessage, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void displayErrorMessage(Component parent, Exception e) {
		JOptionPane.showMessageDialog(parent, e.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
	}

	public static void displayErrorMessage(Component parent, int messageType) {
		String message = null, titleMessage = null;

		switch (messageType) {
		case 1:
			titleMessage = "Результат добавления абитуриента";
			message = "Абитуриент не может быть добавлен!";
			break;
		case 2:
			titleMessage = "Результат редактирования справочника";
			message = "Справочник не может быть отредактирован!";
			break;
		case 3:
			titleMessage = "Результат редактирования справочника";
			message = "Элемент не может быть удален из справочника!";
			break;
		case 4:
			titleMessage = "Результат авторизации";
			message = "Ошибка входа! Неверный логин или пароль!";
			break;
		case 5:
			titleMessage = "Результат вывода листа вступительных испытаний";
			message = "Список специальностей пуст. Вывод невозможен!";
			break;
		case 6:
			titleMessage = "Результат вывода листа вступительных испытаний";
			message = "Список вступительных испытаний пуст. Вывод невозможен!";
			break;
		case 7:
			titleMessage = "Результат проверки данных";
			message = "Не указан регион проживания абитуриента!";
			break;
		case 8:
			titleMessage = "Результат проверки данных";
			message = "Не указан тип населенного пункта!";
			break;
		case 9:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат данных! Исправьте поля, выделенные красным";
			break;
		case 10:
			titleMessage = "Результат проверки данных";
			message = "Не указан блок испытаний!";
			break;
		case 11:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат поля с баллами вступительных испытаний!";
			break;
		case 12:
			titleMessage = "Результат проверки данных";
			message = "Группа не указана либо указана не верно!";
			break;
		case 13:
			titleMessage = "Результат проверки данных";
			message = "Неверно введена дата! Правильный формат: dd.mm.yyyy";
			break;
		case 14:
			titleMessage = "Результат проверки данных";
			message = "Невозможно сохранить несколько одинаковых типов вступительных испытаний!";
			break;
		case 15:
			titleMessage = "Результат проверки данных";
			message = "Не указан тип документа, удостоверяющего личность!";
			break;
		case 16:
			titleMessage = "Результат проверки данных";
			message = "Не указан пол!";
			break;
		case 17:
			titleMessage = "Результат проверки данных";
			message = "Не указано гражданство!";
			break;
		case 18:
			titleMessage = "Результат проверки данных";
			message = "Не задан идентификатор!";
			break;
		case 19:
			titleMessage = "Результат проверки данных";
			message = "Не задана фамилия!";
			break;
		case 20:
			titleMessage = "Результат проверки данных";
			message = "Не задано имя!";
			break;
		case 21:
			titleMessage = "Результат проверки данных";
			message = "Не указана дата рождения!";
			break;
		case 22:
			titleMessage = "Результат проверки данных";
			message = "Абитуриент с таким идентификатором уже существует!\nПопробуйте еще раз";
			break;
		case 23:
			titleMessage = "Результат проверки данных";
			message = "Не выбрана причина возврата документов!";
			break;
		case 24:
			titleMessage = "Результат проверки данных";
			message = "Не выбрано направление обучения!";
			break;
		case 25:
			titleMessage = "Результат проверки данных";
			message = "Не выбрана специальность!";
			break;
		case 26:
			titleMessage = "Результат проверки данных";
			message = "Не выбрана форма обучения!";
			break;
		case 27:
			titleMessage = "Результат проверки данных";
			message = "Не выбрана кафедра!";
			break;
		case 28:
			titleMessage = "Результат проверки данных";
			message = "Не выбрана конкурсная группа!";
			break;
		case 29:
			titleMessage = "Результат проверки данных";
			message = "Не выбран стандарт образования!";
			break;
		case 30:
			titleMessage = "Результат проверки данных";
			message = "Такая конкурсная группа уже была добавлена ранее!";
			break;
		case 31:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат поля id!";
			break;
		case 32:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат поля с баллом!";
			break;
		case 33:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат поля с кодом ФИС!";
			break;
		case 34:
			titleMessage = "Результат проверки данных";
			message = "Такой элемент справочника уже был добавлен ранее!\nПроверьте идентификатор и имя";
			break;
		case 35:
			titleMessage = "Результат проверки данных";
			message = "Идентификатор не может быть пустым!";
			break;
		case 36:
			titleMessage = "Результат проверки данных";
			message = "Имя не может быть пустым!";
			break;
		case 37:
			titleMessage = "Результат проверки данных";
			message = "В плане приема не может быть одинаковых строк!";
			break;
		case 38:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат поля с количеством мест приема!";
			break;
		case 39:
			titleMessage = "Результат проверки данных";
			message = "Количеством мест приема не может быть пустым!";
			break;
		case 40:
			titleMessage = "Результат проверки данных";
			message = "Не выбрано наименование индивидуального достижения!";
			break;
		case 41:
			titleMessage = "Результат проверки данных";
			message = "Не может быть двух одинаковых индивидуальных достижений!";
			break;
		case 42:
			titleMessage = "Результат проверки данных";
			message = "Оригинал не может быть предоставлен в рамках нескольких конкурсных групп одновременно!";
			break;
		case 43:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат поля с кодом направления!";
			break;
		case 44:
			titleMessage = "Результат проверки данных";
			message = "Некорректный формат поля с кодом специальности по стандарту!";
			break;
		default:
			titleMessage = "Неизвестная ошибка";
			message = "Произошла неизвестная ошибка. Обратитесь к администратору!";
		}

		JOptionPane.showMessageDialog(parent, message, titleMessage, JOptionPane.ERROR_MESSAGE);
	}

	public static int displayDialogMessage(Component parent, int messageType) {
		String message = null, titleMessage = null;

		switch (messageType) {
		case 1:
			titleMessage = "Удаление абитуриента";
			message = "Вы действительно хотите удалить текущего абитуриента?";
			break;
		}

		return JOptionPane.showConfirmDialog(parent, message, titleMessage, JOptionPane.YES_NO_OPTION);
	}
}
