import java.util.*;
//not reccomended as it ll increase overhead

class Student{
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId(){  return id;  }

    public int getMarks(){  return marks;   }

    public String getRole(){    return "Undergrad"; }

    @Override
    public String toString(){
        return id + " - " + name + " (" +marks + ") " + getRole();
    }

}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area){
        super(id, name, marks);
        this.area = area;
    }

    public String getRole(){
        return "Grad (" + area + ")";
    }
}

class HonourStudent extends Student{
    private int bonusMarks;

    public HonourStudent(String id, String name, int marks, int bonusMarks ){
        super(id, name, marks);
        this.bonusMarks = bonusMarks;
    }

    @Override
    public int getMarks(){
        return super.getMarks() + bonusMarks;
    }

    @Override
    public String getRole(){
        return "Honours Student";
    }
}

//Custom Generic: made using <T>
class Repository<T>{

    private Map<String, T> data=new HashMap<>();

    //setter method
    public void save(String id, T obj){    data.put(id,obj);    }

    //getter method
    public T find (String id){ return data.get(id); }

    //delete
    public void delete(String id){  data.remove(id);    }

    
}



public class main{
    public static String getTopper(List<Student> list){
        //GetTopper
        Iterator<Student> ite = list.iterator();
        int topMark=0;
        String topID = " ";
        while(ite.hasNext()){
            Student st = ite.next();

            if(st.getMarks()>topMark){
                topMark = st.getMarks();
                topID = st.getId();
            }
        }
        return "Topper ID = "+ topID;
    }

    public static void main( String[] args){
        List<Student> list = new ArrayList();
        list.add(new Student("S1", "Yash", 75));
        list.add(new Student("S2", "Shubham", 100));
        list.add(new Student("S3", "Tarak", 60));

        list.add(new GraduateStudent("G1", "Ansh", 99, "Computer Science"));
        list.add(new HonourStudent("H1","Alisha", 80, 17));

        //Add
        Repository<Student> repo = new Repository<>();
        for(Student s: list){
            repo.save(s.getId(), s);
        }

        System.out.println("ALL: ");
        list.forEach(System.out :: println);

        System.out.println("\nLookUp S2");
        Student s = repo.find("S2");

        System.out.println(s != null ? s : "Not found");

        //Iterator can remove elements while foreach cannot
        Iterator<Student> it = list.iterator();
        //Delete
        while(it.hasNext()){
            Student st = it.next();
            if(st.getMarks()<80){
                it.remove();
                repo.delete(st.getId());
            }
        }
        System.out.println("\nAfter Removal: ");
        list.forEach(System.out :: println);

        //finding topper
        System.out.println(getTopper(list));


        

    }
}
