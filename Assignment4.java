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

        @Override
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

        List<Employee> task1 =
            employees.stream()
                     .filter(e -> e.salary > 60000)
                     .toList();

        List<String> task2 =
            employees.stream()
                     .map(e -> e.name)
                     .toList();

        Set<String> task3 =
            employees.stream()
                     .map(e -> e.name)
                     .collect(Collectors.toSet());

        List<Employee> task4 =
            employees.stream()
                     .sorted((a, b) -> Integer.compare(b.salary, a.salary))
                     .toList();

        List<Employee> task5 =
            employees.stream()
                     .sorted((a, b) -> Integer.compare(b.salary, a.salary))
                     .skip(1)
                     .limit(2)
                     .toList();

        Set<String> task6 =
            employees.stream()
                     .flatMap(e -> e.skills.stream())
                     .collect(Collectors.toSet());

        int task7 =
            employees.stream()
                     .map(e -> e.salary)
                     .reduce(0, Integer::sum);

        double task8 =
            employees.stream()
                     .map(e -> e.salary)
                     .reduce(Integer::sum)
                     .orElse(0) / (double) employees.stream().count();

        Map<String, List<Employee>> task9 =
            employees.stream()
                     .collect(Collectors.groupingBy(e -> e.dept));

        Map<String, Employee> task10 =
            employees.stream()
                     .collect(Collectors.groupingBy(
                         e -> e.dept,
                         Collectors.collectingAndThen(
                             Collectors.maxBy(Comparator.comparingInt(e -> e.salary)),
                             Optional::get
                         )
                     ));

        List<String> task11 =
            employees.stream()
                     .filter(e -> e.dept.equals("IT"))
                     .filter(e -> e.salary > 60000)
                     .flatMap(e -> e.skills.stream())
                     .distinct()
                     .sorted()
                     .limit(3)
                     .toList();

        Map<String, Map<String, Double>> task12 =
            employees.stream()
                     .collect(Collectors.groupingBy(
                         e -> e.dept,
                         Collectors.collectingAndThen(
                             Collectors.toList(),
                             list -> {
                                 double total =
                                     list.stream()
                                         .map(e -> e.salary)
                                         .reduce(0, Integer::sum);
                                 double count = list.size();
                                 double average = total / count;

                                 Map<String, Double> report = new HashMap<>();
                                 report.put("total", total);
                                 report.put("average", average);
                                 report.put("count", count);
                                 return report;
                             }
                         )
                     ));
    }
}
