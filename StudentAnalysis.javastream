import java.util.*;
import java.util.stream.Collectors;

class Student {
    int rollNo;
    String name;
    int age;
    String standard;
    String school;
    String gender;
    double percentage;
    Fees fees;

    public Student(int rollNo, String name, int age, String standard, String school, String gender, double percentage, Fees fees) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.standard = standard;
        this.school = school;
        this.gender = gender;
        this.percentage = percentage;
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "RollNo: " + rollNo + ", Name: " + name + ", Age: " + age + ", Standard: " + standard + ", School: " + school + ", Gender: " + gender + ", Percentage: " + percentage;
    }
}

class Fees {
    double totalFees;
    double feesPaid;
    
    public Fees(double totalFees, double feesPaid) {
        this.totalFees = totalFees;
        this.feesPaid = feesPaid;
    }

    public double getFeesPending() {
        return totalFees - feesPaid;
    }
}

public class StudentAnalysis {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student(1, "Alice", 15, "10th", "Greenwood High", "Female", 85, new Fees(50000, 50000)),
            new Student(2, "Bob", 14, "9th", "Oakridge", "Male", 35, new Fees(45000, 30000)),
            new Student(3, "Charlie", 16, "10th", "Greenwood High", "Male", 55, new Fees(50000, 50000)),
            new Student(4, "David", 17, "12th", "DPS", "Male", 75, new Fees(55000, 50000)),
            new Student(5, "Eve", 14, "9th", "Oakridge", "Female", 40, new Fees(45000, 45000)),
            new Student(6, "Fay", 17, "12th", "DPS", "Female", 95, new Fees(55000, 55000))
        );

        double passingPercentage = 40.0;

        // 1. Number of students in each standard
        Map<String, Long> studentsPerStandard = students.stream()
                .collect(Collectors.groupingBy(s -> s.standard, Collectors.counting()));
        System.out.println("Students in each standard: " + studentsPerStandard);

        // 2. Number of male and female students
        Map<String, Long> genderCount = students.stream()
                .collect(Collectors.groupingBy(s -> s.gender, Collectors.counting()));
        System.out.println("Male & Female student count: " + genderCount);

        // 3. Pass and fail count (whole university)
        Map<Boolean, Long> passFailUniversity = students.stream()
                .collect(Collectors.partitioningBy(s -> s.percentage >= passingPercentage, Collectors.counting()));
        System.out.println("Passed: " + passFailUniversity.get(true) + ", Failed: " + passFailUniversity.get(false));

        // 4. Pass and fail count (school-wise)
        Map<String, Map<Boolean, Long>> passFailSchoolWise = students.stream()
                .collect(Collectors.groupingBy(s -> s.school, 
                        Collectors.partitioningBy(s -> s.percentage >= passingPercentage, Collectors.counting())));
        System.out.println("Pass/Fail school-wise: " + passFailSchoolWise);

        // 5. Top 3 students (whole university)
        List<Student> top3Students = students.stream()
                .sorted(Comparator.comparingDouble(s -> -s.percentage))
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Top 3 students: " + top3Students);

        // 6. Top scorer school-wise
        Map<String, Optional<Student>> topScorerSchoolWise = students.stream()
                .collect(Collectors.groupingBy(s -> s.school, 
                        Collectors.maxBy(Comparator.comparingDouble(s -> s.percentage))));
        System.out.println("Top scorer school-wise: ");
        topScorerSchoolWise.forEach((school, student) -> 
                System.out.println(school + ": " + student.get()));

        // 7. Average age of male and female students
        Map<String, Double> averageAgeByGender = students.stream()
                .collect(Collectors.groupingBy(s -> s.gender, Collectors.averagingInt(s -> s.age)));
        System.out.println("Average age of Male & Female students: " + averageAgeByGender);

        // 8. Total fees collected school-wise
        Map<String, Double> totalFeesCollected = students.stream()
                .collect(Collectors.groupingBy(s -> s.school, 
                        Collectors.summingDouble(s -> s.fees.feesPaid)));
        System.out.println("Total fees collected school-wise: " + totalFeesCollected);

        // 9. Total fees pending school-wise
        Map<String, Double> totalFeesPending = students.stream()
                .collect(Collectors.groupingBy(s -> s.school, 
                        Collectors.summingDouble(s -> s.fees.getFeesPending())));
        System.out.println("Total fees pending school-wise: " + totalFeesPending);

        // 10. Total number of students in university
        long totalStudents = students.size();
        System.out.println("Total number of students in university: " + totalStudents);
    }
}
