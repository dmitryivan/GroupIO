import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Group implements GroupInterface  {
		protected final int maxGroupSize=20;
		protected int currentGroupSize=0;
		protected StudentInterface [] group = new StudentInterface [maxGroupSize];
		protected String groupName;
		
		public Group(String groupName) {
			this.groupName = groupName;
		}

        @Override
		public int getGroupSize() {
			return currentGroupSize;
		}

        @Override
		public String getGroupName() {
			return groupName;
		}

	    public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

	    @Override
		public void sortStudents (String criteria){
	    	for (int i = 0; i<currentGroupSize; i++) {
		          if (group[i]!=null) group[i].setSortCriteria(criteria);
		        }	    	
	        	Arrays.sort(group,0,this.getGroupSize());	    	
	    	System.out.println("Students are sorted by " + criteria);
	    }
	    
	    @Override
		public void listGroup(){
   		     System.out.println("Grop list:");
	    	 for (int i = 0; i<currentGroupSize; i++) {
		         System.out.println(group[i].toString());
	    	 }
	    }
	    
        @Override
		public StudentInterface searchStudent (String name){
	        for (int i = 0; i<currentGroupSize; i++) {
	            try{
	        	    if ( name.equals(group[i].getName()) ) {
	                    return (StudentInterface)group[i];
	                }
	            } catch (NullPointerException e) {
	            	return null;
	            }
	        }
	        return null;
	    }

	    public void addStudent(StudentInterface s) {
	    	for (int i = 0; i<maxGroupSize; i++) {
	            try{
	    		    if (group[i] == null) {
	                group[i] = s;
	                System.out.println( "Student added to group: " +groupName+ " "+ s.info() );
	                currentGroupSize++;
                    return; 
	    		    }
	    		} catch (ArrayIndexOutOfBoundsException e) {
	    			throw new GroupException();	
	    		}
            }     
	    	System.out.println ("Error adding, goup is full");
	    }


	    public void readFromFile(String filename) {
	    	String myLine = null;
	    	String[] temp;
	    
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
				while ( ( myLine = br.readLine())!=null )
		    	{    
		    	    temp = myLine.split(" ");
		    	    this.addStudent(new Student(temp[1],temp[3],Integer.parseInt(temp[5]),temp[7]));
		    	}
		    	
			} catch (IOException e) {
				System.out.println("Error: " + e);
			}
		}
	    
		public void saveToFile(String fileName) {
			try (PrintWriter pw = new PrintWriter( new FileWriter( new File(fileName)))) {
				for (StudentInterface st : group){
					pw.println(st.toString());
				}
			} catch (IOException e) {
				System.out.println("Error creating " + fileName);
		    }
	    }
}
