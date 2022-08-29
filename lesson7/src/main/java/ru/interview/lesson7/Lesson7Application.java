package ru.interview.lesson7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Урок 7. Spring
1. Создать сущность Student с полями id, name, age. +
2. Сконфигурировать Spring JPA контекст. +
3. Создать репозиторий для вставки, удаления, изменения и просмотра студентов. +
4. Создать контроллер с методами, которые вызывают методы репозитория.
5. Создать JSP-страницу, на которой в таблице отобразить список студентов с кнопками для добавления, обновления и удаления записей.
6. Привязать к каждой кнопке действие, которое будет передаваться на контроллер.
 */
@SpringBootApplication
public class Lesson7Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson7Application.class, args);
	}

}
