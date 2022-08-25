package ru.interview.lesson1.exercise1;
//1. Создать builder для класса Person со следующими полями:
// String firstName, String lastName, String middleName,
// String country,
// String address,
// String phone,
// int age,
// String gender.

public class Main {

    public static void main(String[] args) {
        Person c = Person.newBuilder().addPersonInfo("Иван","Иванов","Иванович","M",25)
                .addContacts("United States", "Infinite loop 1", "1-800-MY-APPLE")
                .build();
        System.out.println("Created person "+c);
    }
}
