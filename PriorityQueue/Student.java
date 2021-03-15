package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
      this.name=trim;
      this.marks=parseInt;
    }

    private int getmarks() {
    	return this.marks;
    }
    @Override
    public int compareTo(Student student) {
        if(marks<student.getmarks()) {
        	return 1;
        }
        else {
        	return -1;
        }
    }
    public String toString() {
    	return  ("Student{Name: "+name+","+"marks:"+marks+"}");
    }

    public String getName() {
        return name;
    }
}
