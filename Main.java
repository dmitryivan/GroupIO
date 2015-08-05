
public class Main {

	public static void main(String[] args) {
	Student s1=new Student();
	Student s2=new Student("name1", "mutant" , 22, "No");
	Group g=new Group("1");
    g.addStudent(s1);
    g.addStudent(s2);

    
    System.out.println("");
	for (int i=0;i<20;i++){
		g.addStudent(s1);
		System.out.println(i);
	}

    g.sortStudents("age");
    
    g.listGroup();
	
	g.saveToFile("11111.txt");
    Group g2 = new Group("newgr");
	g2.readFromFile("11111.txt");
	
	
	}
	

}
