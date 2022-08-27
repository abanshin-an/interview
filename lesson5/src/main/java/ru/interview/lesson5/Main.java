package ru.interview.lesson5;

import com.github.javafaker.Faker;
import ru.interview.lesson5.dao.Student;
import ru.interview.lesson5.dao.StudentDao;

import java.util.List;
import java.util.Random;

//        1. Создать базу данных Student с полями id, name, mark.
//        2. Создать сущность Student и добавить в нее аннотации. Поле id должно заполняться автоматически.
//        3. Создать конфигурационный файл hibernate.cfg.xml, в котором указать свойства для подключения к БД и список классов с аннотациями Entity.
//        4. Создать класс со статическим методом, который возвращает объект SessionFactory.
//        5. Создать DAO-слой с операциями добавления, удаления, изменения сущности, выборки записи по ID и всех записей.
//        6. Добавить 1000 записей в таблицу Student.
//        7. Проверить работоспособность приложения, выполнить методы по удалению, изменению, добавлению записей, а также выборки одной и всех записей.

public class Main {
    private static final Random r=new Random();

    public static void main(String[] args) {
        Main.createRecords();
    }

    public static void createRecords(){
        Faker f=new Faker();
        StudentDao dao=new StudentDao();

        try {

            dao.openCurrentSessionWithTransaction();
            dao.deleteAll();
            for (int i = 0; i < 1000; i++) {
                Student student = new Student();
                student.setName(f.name().fullName());
                student.setMark(r.nextInt(100));
                dao.persist(student);
            }
            System.out.println("add 1000  records");
            List<Student> studentList = dao.findAll();
            System.out.printf("fetch %d records %n",studentList.size());
            Student student=studentList.get(1);
            String id =  student.getId();
            dao.delete(student);
            studentList.remove(student);
            System.out.println("delete record "+id);
            student=studentList.get(5);
            String name = student.getName();
            student.getId();
            student.setName(f.name().fullName());
            System.out.printf("update record %s name '%s' change to '%s' %n",student.getId(),name,student.getName());
            dao.update(student);
    } finally {
        dao.closeCurrentSessionWithTransaction();
    }
    }
}
