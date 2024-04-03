package Task1;


import java.io.*;

/**
 * Класс представляет сущность "Студент" с полями name, age и GPA.
 */
public class Student implements Serializable {
    // private static Student student;
    private String name;
    private int age;
    // Удалите "transient" чтобы вывести средний балл
    private transient double GPA;

    /**
     * Конструктор класса Student для создания объекта "Student" с заданным именем возрастом и средним баллом.
     *
     * @param name Имя человека
     * @param age  Возраст человека
     * @param GPA Средний балл
     */
    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    /**
     * Метод для сериализации объекта Student в файл bin.
     *
     * @param Student Объект Student для сериализации
     * @param fileName Имя файла для сохранения сериализованного объекта
     */
    public static void serializeStudent(Student student, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для десериализации объекта Student из файла bin.
     *
     * @param fileName Имя файла, из которого происходит десериализация объекта
     * @return Возвращает десериализованный объект Student
     */
    public static Student deserializeStudent(String fileName) {
        Student student = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            student = (Student) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static void main(String[] args) {
        Student student1 = new Student("Артём", 30, 3.7);
        Student student2 = new Student("Сергей", 25, 3.9);
        Student student3 = new Student("Жанна", 21, 4.0);

        // Сериализация и десериализация объектов Student
        serializeStudent(student1, "student1.bin");
        serializeStudent(student2, "student2.bin");
        serializeStudent(student3, "student3.bin");

        Student deserializedStudent1 = deserializeStudent("student1.bin");
        Student deserializedStudent2 = deserializeStudent("student2.bin");
        Student deserializedStudent3 = deserializeStudent("student3.bin");

        System.out.println("Десериализованный объект student1: " + deserializedStudent1.name + " возвраст-" + deserializedStudent1.age + " ср.балл-" + deserializedStudent1.GPA);
        System.out.println("Десериализованный объект student2: " + deserializedStudent2.name + " возвраст-" + deserializedStudent2.age + " ср.балл-" + deserializedStudent2.GPA);
        System.out.println("Десериализованный объект student3: " + deserializedStudent3.name + " возвраст-" + deserializedStudent3.age + " ср.балл-" + deserializedStudent3.GPA);
    }

}

