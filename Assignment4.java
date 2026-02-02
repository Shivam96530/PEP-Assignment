import java.util.*;
import java.util.stream.*;

public class Assignment4Streams {

    static class Employee {
        int id;
        String name;
        String dept;
        int salary;
        List<String> skills;

        Employee(int id, String name, String dept, int salary, List<String> skills) {
            this.id = id;
            this.name = name;
            this.dept = dept;
            this.salary = salary;
            this.skills = skills;
        }

        public String toString() {
            return name + " | " + dept + " | " + salary;
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = List.of(
            new Employee(1, "Aman", "IT", 70000, List.of("Java", "Spring")),
            new Employee(2, "Ravi", "HR", 40000, List.of("Recruitment")),
            new Employee(3, "Neha", "IT", 90000, List.of("Java", "Docker")),
            new Employee(4, "Pooja", "Finance", 60000, List.of("Excel", "Accounts")),
            new Employee(5, "Aman", "IT", 70000, List.of("Java", "Spring"))
        );

        System.out.println("Task 1:");
        employees.stream()
                .filter(e -> e.salary > 60000)
                .forEach(System.out::println);

        System.out.println("\nTask 2:");
        employees.stream()
                .map(e -> e.name)
                .forEach(System.out::println);

        System.out.println("\nTask 3:");
        employees.stream()
                .map(e -> e.name)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("\nTask 4:");
        employees.stream()
                .sorted((a, b) -> b.salary - a.salary)
                .forEach(System.out::println);

        System.out.println("\nTask 5:");
        employees.stream()
                .sorted((a, b) -> b.salary - a.salary)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("\nTask 6:");
        employees.stream()
                .flatMap(e -> e.skills.stream())
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("\nTask 7:");
        int totalSalary =
                employees.stream()
                         .map(e -> e.salary)
                         .reduce(0, Integer::sum);
        System.out.println(totalSalary);

        System.out.println("\nTask 8:");
        double avgSalary =
                employees.stream()
                         .map(e -> e.salary)
                         .reduce(0, Integer::sum) / (double) employees.size();
        System.out.println(avgSalary);

        System.out.println("\nTask 9:");
        Map<String, List<Employee>> byDept =
                employees.stream()
                         .collect(Collectors.groupingBy(e -> e.dept));
        byDept.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\nTask 10:");
        Map<String, Employee> highestByDept =
                employees.stream()
                         .collect(Collectors.groupingBy(
                             e -> e.dept,
                             Collectors.collectingAndThen(
                                 Collectors.maxBy(Comparator.comparingInt(e -> e.salary)),
                                 Optional::get
                             )
                         ));
        highestByDept.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\nTask 11:");
        employees.stream()
                .filter(e -> e.dept.equals("IT") && e.salary > 60000)
                .flatMap(e -> e.skills.stream())
                .distinct()
                .sorted()
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\nTask 12:");
        employees.stream()
                .collect(Collectors.groupingBy(e -> e.dept))
                .forEach((dept, list) -> {
                    int total = list.stream().map(e -> e.salary).reduce(0, Integer::sum);
                    double avg = total / (double) list.size();
                    System.out.println(dept + " -> total: " + total + ", avg: " + avg + ", count: " + list.size());
                });
    }
}
