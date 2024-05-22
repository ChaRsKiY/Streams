package streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            cars.add(new Car("streams.Car" + i, i % 2 == 0 ? "red" : "blue", 150 + i));
        }

        Car fastestRedCar = cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("red"))
                .max(Comparator.comparingInt(Car::getMaxSpeed))
                .orElse(null);

        System.out.println("Fastest red car: " + fastestRedCar.getName());

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            students.add(new Student("streams.Student" + i, 15 + i, "Group" + i));
        }

        List<String> surnamesUnder16 = students.stream()
                .filter(student -> student.getAge() < 16)
                .map(student -> student.getFullName().split("\\s+")[0])
                .toList();

        System.out.println("Surnames of students under 16: " + surnamesUnder16);

        List<Student> studentsWithPoints = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            studentsWithPoints.add(new Student("streams.Student" + i, 16, "Group" + i));
        }

        double averagePoints = studentsWithPoints.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);

        System.out.println("Average age of students: " + averagePoints);

        List<Employee> employees = students.stream()
                .map(student -> new Employee(student.getFullName(), "", "", student.getAge()))
                .toList();

        System.out.println("Converted to employees: " + employees);
    }
}
