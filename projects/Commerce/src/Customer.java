public class Customer {
    //속성
    private String name;
    private String email;
    private String grade;


    //생성자
    public Customer(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }


    //기능
    public void printInfo() {
        System.out.println("고객명: " + name);
        System.out.println("이메일: " + email);
        System.out.println("등급: " + grade);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        if (grade != null && !grade.isEmpty()) {
            this.grade = grade;
        }
    }
}