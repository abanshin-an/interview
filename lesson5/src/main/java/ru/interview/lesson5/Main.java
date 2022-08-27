package ru.interview.lesson5;

import com.github.javafaker.Faker;
import ru.interview.lesson5.dao.Student;
import ru.interview.lesson5.dao.StudentDao;

import java.util.Random;

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
            for (int i = 0; i < 1000; i++) {
                Student student = new Student();
                student.setName(f.name().fullName());
                student.setMark(r.nextInt(100));
                dao.persist(student);
            }
        } finally {
            dao.closeCurrentSessionWithTransaction();
        }
    }
}
