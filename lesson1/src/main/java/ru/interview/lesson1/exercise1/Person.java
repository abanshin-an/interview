package ru.interview.lesson1.exercise1;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public static Builder newBuilder() {
        return new Person().new Builder();
    }
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ",\n country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ",\n age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public class Builder {
        private Builder() {
            // private constructor
        }

        public Builder addPersonInfo(String firstName, String lastName, String middleName, String gender, int age) {
            Person.this.firstName=firstName;
            Person.this.lastName=lastName;
            Person.this.middleName=middleName;
            Person.this.age=age;
            Person.this.gender=gender;
            return this;
        }
        public Builder addContacts( String country, String address, String phone) {
            Person.this.country=country;
            Person.this.address=address;
            Person.this.phone=phone;
            return this;
        }
        public Person build(){
            return Person.this;
        }
    }
}
