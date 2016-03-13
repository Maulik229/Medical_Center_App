import java.io.BufferedReader;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class MedicalCentre {

	static Scanner in =new Scanner(System.in);
	public static Node head;
	public static Node temp;
	public static Node tail;
	
	/* Node declaration with different field*/
	  public static class Node
	   {
	      private String name;
	      private String patient_id;
	      private String address;
	      private int age;
	      private String weight;
	      private String height;
	      private String dob;
	      private String initial_visit;
	      private String last_visit;
	      public Node next;

public Node(String name,String patient_id,String address,int age,String weight,String height,
			String dob,String initial_visit, String last_visit,Node next)
	      {
	         this.name = name;
	         this.patient_id=patient_id;
	         this.address=address;
	         this.age=age;
	         this.weight=weight;
	         this.height=height;
	         this.dob=dob;
	         this.initial_visit=initial_visit;
	         this.last_visit=last_visit;
	         this.next = next;
	      } 

	}
	  /* accessor methods for each field of the node*/
	  public String getName(){
		 
		   return temp.name;
		}
	public static String getId(){
		   return temp.patient_id;
	}
	public static String getAddress(){
		   return temp.address;
	}
	public static String getWeight(){
		   return temp.weight;
	}
	public static String getHeight(){
		   return temp.height;
	}
	public static String getdob(){
		   return temp.dob;
	}
	public static String getInitial(){
		   return temp.initial_visit;
	}
	public static String getLast(){
		   return temp.last_visit;
	} 
   public MedicalCentre()
   {
      head = null;
   }
  
   /* check whether given date is valis or not*/
public static String DateValidation(String date){
		Calendar today= Calendar.getInstance();
		Calendar cal=Calendar.getInstance();
		 String d;
		if(date == null){
			return null;
		}

		DateFormat df = new SimpleDateFormat("MM-dd-yyyy"); //formate should be like this
		df.setLenient(false);

		try {

			Date dt = df.parse(date);
			cal.setTime(dt);
			if(cal.after(today))
				return null;
			DateFormat df1 = DateFormat.getDateInstance(DateFormat.DEFAULT);
		      d = df1.format(dt);
		      
		      //System.out.println(d);
		} catch (ParseException e) {

			//e.printStackTrace();
			return null;
		}
		
		return d;
	}
/* calculating the age from the given date of birth, same way calculating the time from initial visit and last visit*/
   public static int Age(String date){
	   Calendar today =Calendar.getInstance();
	   Calendar dob=Calendar.getInstance();
	   int year;
	   if(date == null){
			return 0;
		}

		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		df.setLenient(false);

		try {

			Date dt = df.parse(date);
			dob.setTime(dt);
			if(dob.after(today))
				return 0;
			 year=today.get(Calendar.YEAR)-dob.get(Calendar.YEAR); // difference from today's date will give the age
			
			 long diff = today.get(Calendar.LONG) - dob.get(Calendar.LONG);
			 System.out.println(diff);
		      
		} catch (ParseException e) {

			//e.printStackTrace();
			return 0;
		}
			   return year;
   }
   /* creating the list for patients*/
   public void add(String name,String patient_id,String address,int age,String weight,String height,
			String dob,String initial_visit, String last_visit){
	  
	   if (head==null){
	   head=new Node(name,patient_id,address,age,weight,height,
					dob,initial_visit, last_visit,null);
	  
	   			tail=head;}
	   else{
		   tail.next=new Node(name,patient_id,address,age,weight,height,
					dob,initial_visit, last_visit,null);
		   
		   tail=tail.next;
	   }
	   
   }
   /* Method to delete patient*/
   public void delete(File SourceFile,String id) throws IOException {
	   File inputFile = SourceFile;   // Your file  
	   File tempFile = new File("text.txt");// temp file
	  // File temp=new File("temp.txt");
	   String Line;
	   BufferedReader reader = null; 
	   BufferedWriter writer=null;
		try {
		
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(tempFile));
			
			  while((Line = reader.readLine()) != null) {

			       if(Line.contains(id))
			           continue;

			       writer.write(Line);
			       writer.append(System.lineSeparator());
			   }
			  writer.flush();
			   reader.close();
			   
			   writer.close();}
			//Delete the original file
	          
  catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		inputFile.delete();

		 // rename temp file back to books.txt
		 if(tempFile.renameTo(inputFile)){
		        System.out.println("Update failed");
		    }else{
		        System.out.println("Update Succesful");
		    }
   }
	  
 /*Calculating the average age*/
   public double ageAverage(File SourceFile) {
	   BufferedReader reader = null; double sum = 0; double average = 0; int count=0;
		try {
			reader = new BufferedReader(new FileReader(SourceFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   String line = null;
		     
	        try {
	        	
	        	    Scanner scan = new Scanner(SourceFile);
	        	    line = scan.nextLine();
	        	        while (scan.hasNextLine()) {
	        	        	
	        	             line = scan.nextLine();
	        	            
	        	            StringTokenizer tokens = new StringTokenizer(line);
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            String t = tokens.nextToken();
	        	            
	        	            sum = sum + Double.parseDouble(t);
	        	          
	        	            count++;
	        	            }
	        	       
	        	        average=sum/count;
	        	    
	        
	          scan.close();
	        } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        }
			return average;

   }	  
   /*Calculating the youngest age*/
   public int youngAge(File SourceFile) {
	   BufferedReader reader = null;  String t2 = null,t1;
		try {
			reader = new BufferedReader(new FileReader(SourceFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   String line = null;
		     
	        try {
	        	
	        	    Scanner scan = new Scanner(SourceFile);
	        	    line = scan.nextLine();
	        	        while (scan.hasNextLine()) {
	        	        	
	        	             line = scan.nextLine();
	        	            
	        	            StringTokenizer tokens = new StringTokenizer(line);
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            t1 = tokens.nextToken();
	        	            t2=t1;
	        	            if(Integer.parseInt(t2)>Integer.parseInt(t1))
	        	            	t2=t1;
	        	            else
	        	            	t2=t2;
	        	           
	        	            }
	        	       
	        	     scan.close();
	        } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        }
	return Integer.parseInt(t2);
	   
	}
	   
   
   /*to write data into .xls file*/
   public  void writeFile(File TargetFile) throws IOException{ 
	   FileWriter fop = null;
	  BufferedReader reader = null;
	try {
		reader = new BufferedReader(new FileReader(TargetFile));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   String line = null;
	     
	      String[] array={"PatientID","Name","Address","DateOfBirth","Age",
	    		  "Weight","Height","Initial Visit date","Last Visit Date"};
	      
	      try {
				fop = new FileWriter(TargetFile,true);
				// if file doesnt exists, then create it
				if (!TargetFile.exists()) {
					TargetFile.createNewFile();
					}
	      } 
			catch (IOException e) {
			e.printStackTrace();
			} 
			
	      while(((line = reader.readLine()) == null)){
				for(int i=0;i<array.length;i++){
				fop.write(array[i]);
				if(i==array.length-1)
					break;
				
				fop.write("     ");
				
				}
				
				break;}

				temp=head;
				while(temp!=null)
				{
					fop.write(temp.patient_id);
					fop.write("          ");
					fop.write(temp.name);
					fop.write("     ");
					fop.write(temp.address);
					fop.write("        ");
					fop.write(temp.dob);
					fop.write("     ");
					fop.write(""+temp.age);
					
					fop.write("      ");
					fop.write(temp.weight);
					fop.write("         ");
					fop.write(temp.height);
					fop.write("       ");
					fop.write(temp.initial_visit);
					fop.write("        ");
					fop.write(temp.last_visit);
					
					temp=temp.next;
				}
				fop.append(System.lineSeparator());
				fop.flush();
				fop.close();
				
   }
   /*read file*/
   public int readFile(File SourceFile,String id) throws IOException {
	   BufferedReader reader = null; String line = null;
		try {
			reader = new BufferedReader(new FileReader(SourceFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
	        try {
	        	
	        	  while((line = reader.readLine()) != null) {

				       if(line.contains(id))
				           return 0;
				       
	        	        }
	        	    
	        
	          reader.close();
	        } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        }
			return 1;
			
	      }
	   
   public int info(File SourceFile,String id) throws IOException{
	   BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(SourceFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   String line = null;
		     //boolean a=false;
	        try {
	        	
	        	 
	        	    while((line = reader.readLine()) != null) {
	        	    		
					       if(line.contains(id)){
	System.out.println("PatientID      Name     Address    "+ "  DateOfBirth   Age   Weight    Height    Initial Visit date     Last Visit Date");  
					    System.out.println(line);
					           return 1;}
	        	            }
	        	    
	        	        
	          in.close();
	          reader.close();
	        } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        }
	
		
			return 0;
		
   }
   public void notification(File SourceFile,String id) throws IOException{
	   BufferedReader reader = null;  String t2 = null,t1,t3,t4;
	   Scanner scan=null;
	   StringBuilder s1 = null;
       StringBuilder s2 = null;
		try {
			reader = new BufferedReader(new FileReader(SourceFile));
			scan=new Scanner(SourceFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   String line = null;
		   StringTokenizer tokens = null;
	        
	        	        while (scan.hasNext()==true) {
	        	        	 
	        	        	line = scan.nextLine();
	        	              tokens = new StringTokenizer(line);
	        	             String t= tokens.nextToken();
	        	            
	        	             if(t.equals(id)==true)
	        	             {
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            tokens.nextToken();
	        	            t1 =tokens.nextToken();
	        	            t2 = tokens.nextToken();
	        	            t3 =tokens.nextToken();
	        	            t4 = tokens.nextToken();
	        	            
	        	            StringBuilder sb1 = new StringBuilder(t1);
	        	            StringBuilder sb2 = new StringBuilder(t2);
	        	            StringBuilder sb3 = new StringBuilder(t3);
	        	            StringBuilder sb4 = new StringBuilder(t4);
	        	             s1 = sb1.append(sb2);
	        	             s2 = sb3.append(sb4);
	        	             System.out.println("The Patient first visited on "+ s1+ " and last visited on " +s2);
	        	             }
	        	           
	        	            }
	        	        reader.close();
   }
   
   /*printing the file content*/
   public void printFile(File SourceFile) {
	   BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(SourceFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   //String line = null;
		     
	        try {
	        	
	        	    Scanner scan = new Scanner(SourceFile);
	        	        while (scan.hasNextLine()) {
	        	        	
	        	            String line = scan.nextLine();
	        	            System.out.println(line);
	        	        }
	        	    
	        
	          scan.close();
	        } catch (FileNotFoundException e) {
	          e.printStackTrace();
	        }
			
			
	      }
	   
	      
	   
   
  public String toString(){
	  temp=head;
	  while(temp!=null){
	  System.out.println("Name: "+ getName());
	  System.out.println("Patient ID: "+getId());
	  System.out.println("Age: "+temp.age);
	  System.out.println("Address: "+ getAddress());
	  System.out.println("Weight: "+ getWeight());
	  System.out.println("Height: "+ getHeight());
	  System.out.println("Date of Birth: "+ getdob());
	  System.out.println("Initial Visit Date: "+ getInitial());
	  System.out.println("Last Visit Date: "+ getLast());
	  temp=temp.next;}
	return null;
	
}
  
  
  /*Main Mwthod*/
public static void main(String[] args) throws IOException{
	
	if(args.length!=1){
		System.out.println("Usage: SourceFile");
		System.exit(0);}
	
	File SourceFile=new File(args[0]);

	  	MedicalCentre medical=new MedicalCentre();
	  	Scanner in =new Scanner(System.in); int age;int a = 0;String id = null;
	  	 while(true){
	  	      System.out.println("Please PRESS"+ "\n" + "1. Patients List"+ "\n" +"2. Add a new Patient" 
  		  			+ "\n" + "3. Show Information for a Patient"+ "\n"+ "4. Delete a Patient" + 
  		  			"\n"+ "5. Average age of all the Patients"+ "\n" + "6. Information of the Youngest Patient"+ 
  		  			"\n" +"7. Patient Notification" + "\n" +"8. Quit");
	  	      System.out.println("");
	  	      System.out.println("Please Enter choice:");
	  		int choice = in.nextInt();
	  		switch(choice){
	  		case 1:
	  		medical.printFile(SourceFile);
	  		break;
	  		case 2:
	  	do{medical.writeFile(SourceFile);
	  System.out.println("Enter the id");
	   id=in.next();
	  a=medical.readFile(SourceFile,id);
	 if(a==0)
	 System.out.println("The patient with this ID already exist. Try again!!");
	 }while(a==0);
	  System.out.println("Enter the name");
	  String name=in.next();
	  //in.nextLine();
	  System.out.println("Enter the address");
	  String add=in.next();
	 
	  System.out.println("Enter the weight");
	  String weight=in.next();
	
	  System.out.println("Enter the height");
	  String height=in.next();

	  String d1,d2,d3;
	  do{
	  System.out.println("Enter the DOB");
	  String date=in.next();
	  d1=MedicalCentre.DateValidation(date);
	   if(d1==null)
			  System.out.println("Please Enter the valid Date.");
	   age=MedicalCentre.Age(date);
	   }while(d1==null);
	  
	  do{
		  System.out.println("Enter the Initial Visit date");
		  String date=in.next();
		d2=MedicalCentre.DateValidation(date);
		
		if(d2==null||(d2.compareTo(d1)<0))
				  System.out.println("Please Enter the valid Date.");
		   }while(d2==null||(d2.compareTo(d1)<0));
	  
	  	do{
		  System.out.println("Enter the Last Visit Date");
		  String date=in.next();
		   d3=MedicalCentre.DateValidation(date);
		   if(d3==null|| (d3.compareTo(d2)<0))
				  System.out.println("Please Enter the valid Date.");
		   }while(d3==null||(d3.compareTo(d2)<0));
	 
	  medical.add(name,id,add,age,weight,height,d1,d2,d3);
	 System.out.println("Do you want to Save Patient as a Record?");
	 String ans = in.next();
     if (ans.equalsIgnoreCase("y"))
     medical.writeFile(SourceFile);
     break;
     
     
	  		case 3:int b=0;
	  			do{
	  			System.out.println("Enter the Patient ID for Info: ");
	  			String id2=in.next();
	  			b=medical.info(SourceFile,id2);
	  			
	  			if(b==0)
	  				System.out.println("No Patient with this ID exist.");
	  			}while(b==0);
	  			break;
	  		case 4:
	  			System.out.println("Enter the patient ID to remove: ");
	  			String id1=in.next();
	  			medical.delete(SourceFile,id1);
	  			break;
	  		case 5:
	  			double avg=medical.ageAverage(SourceFile);
	  			System.out.println("The Average age of Patients: "+ avg);
	  			break;
	  		case 6:
	  			int young=medical.youngAge(SourceFile);
	  			System.out.println("The Youngest Patient's age is: "+ young);
	  			break;
	  		case 7:int c=1;String id3;
	  			do{
	  			System.out.println("Enter the patient ID ");
	  			 id3=in.next();
	  			c=medical.readFile(SourceFile,id3);
	  			if(c==1)
  				System.out.println("No Patient with this ID exist.");
  				}while(c==1);
  				medical.notification(SourceFile, id3);
  				break;
	  			
	  		case 8:
	  			System.exit(0);
	  		}
}}}
