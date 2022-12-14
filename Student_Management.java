import java.util.*;  
import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.*;
  
//---------------------------------------Definition of AppendableObjectOutputStream---------------------------------------

class AppendableObjectOutputStream extends ObjectOutputStream {

    private boolean append;
    private boolean initialized;
    private DataOutputStream dout;

    protected AppendableObjectOutputStream(boolean append) throws IOException, SecurityException {
        super();
        this.append = append;
        this.initialized = true;
    }

    public AppendableObjectOutputStream(OutputStream out, boolean append) throws IOException {
        super(out);
        this.append = append;
        this.initialized = true;
        this.dout = new DataOutputStream(out);
        this.writeStreamHeader();
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        if (!this.initialized || this.append) return;
        if (dout != null) {
            dout.writeShort(STREAM_MAGIC);
            dout.writeShort(STREAM_VERSION);
        }
    }

}

//---------------------------------------Definition of Students--------------------------------------


class Student implements Serializable
{

	String name, gender, program, user, pass, confirmps;				 //Data Members
	int id, admyear,acdyear, contactno, sem, noofsub , flag = 0;
	String sub[] = new String[20];			                             //Array of Strings
	int mark[] = new int[20];											 //Array of Integers	
	char grade[] = new char[20];										 //Array of Characters

                                                                         //Methods
	void displaysub()													 //Displays Subject Details
	{
		System.out.println("\t|       Subject          Marks    Grade       |");
		System.out.println("\t+---------------------------------------------+");
		for(int i = 0 ; i<noofsub ; i++)
		{
	   		System.out.println(String.format("\t|       "+"%-17s"+"%-9d"+"%-8c    |",sub[i],mark[i],grade[i]));
	   	}
	   	System.out.println(String.format("\t+---------------------------------------------+"));
	}

	void addsub()														 //Get Subject Details
	{
			Scanner sc = new Scanner(System.in);
			
			System.out.print("\nEnter the number of Subjects: ");
			noofsub = Integer.parseInt(sc.nextLine());				//

			System.out.println("Enter the Subject Name and mark for "+noofsub+" subjects");

			for(int i = 0; i<noofsub ; i++)
			{
				System.out.print("\nEnter the Name and mark for Subject ");
				System.out.println(i+1);

				sub[i] = sc.nextLine();
				mark[i] = Integer.parseInt(sc.nextLine());
			}

										//Business Cycle Calculate the Grade based on the Mark Achieved
			for(int i=0; i<noofsub;i++)
				if (mark[i] >= 90 && mark[i] <=100)
					grade[i] = 'A';
				else if (mark[i] >= 80 && mark[i] < 90)
					grade[i] = 'B';
				else if (mark[i] >= 70 && mark[i] < 80)
					grade[i] = 'C';
				else if (mark[i] >= 60 && mark[i] < 70)
					grade[i] = 'D'; 
				else if (mark[i] >= 50 && mark[i] < 60)
					grade[i] = 'E';
				else 
					grade[i] = 'F';
	}
	

	void display(int isAdmin)									//Display Student Details
	{
		System.out.println(String.format("\t+=============================================+"));
		System.out.println(String.format("\t|                                             |"));
		System.out.println(String.format("\t|               Student Details               |"));
		System.out.println(String.format("\t|                                             |"));
		System.out.println(String.format("\t+---------------------------------------------+"));
		System.out.println(String.format("\t|\tId: %-32d  |",id));
		System.out.println(String.format("\t|\tName: %-30s  |",name));
		System.out.println(String.format("\t|\tGender: %-28s  |",gender));
		System.out.println(String.format("\t|\tContact Number: %-20d  |",contactno));
		System.out.println(String.format("\t|\tProgram: %-27s  |",program));
		System.out.println(String.format("\t|\tAdmission Year: %-20s  |",admyear));
		System.out.println(String.format("\t|\tAcademic Year: %-21s  |",acdyear));
		System.out.println(String.format("\t|\tCurrent Semester: %-18s  |",sem));
		System.out.println(String.format("\t+---------------------------------------------+"));
		if(isAdmin == 1)
		{
			System.out.println(String.format("\t|\tUsername: %-26s  |",user));
			System.out.println(String.format("\t|\tPassword: %-26s  |",pass));
			System.out.println(String.format("\t+---------------------------------------------+"));
			displaysub();
		}
		
	}

	void displaygradesheet()											//Displaying Grade Sheet
	{
		System.out.println("\t+=============================================+");
		System.out.println("\t|                                             |");
		System.out.println("\t|                 Grade Sheet                 |");
		System.out.println("\t|                                             |");
		System.out.println("\t+=============================================+");
		displaysub();
	}

	void addstudent()										    //Get Student Details from admin
	{
				Scanner sc = new Scanner(System.in);
				int flag = 0;
				System.out.println("\nEnter the Username: ");
				user = sc.nextLine();

				do
				{
					System.out.println("\nEnter the Password: ");
					pass = sc.nextLine();

					System.out.println("\nConfirm Password: ");
					confirmps = sc.nextLine();

					if(pass.equals(confirmps))
					{
						flag = 1;
					}
					else
					{
						System.out.println("Passwords do not match ... Enter again!");
					}

				}while(flag == 0);
																		
				System.out.println("\nEnter the Student name: ");
				name = sc.nextLine();

				System.out.println("\nEnter the Student's gender: ");
				gender = sc.nextLine(); 

				System.out.println("\nEnter the Program taken: ");
				program = sc.nextLine(); 

				System.out.println("\nEnter the Student id: ");
				id = sc.nextInt();

				System.out.println("\nEnter the Student's contact number: ");
				contactno = sc.nextInt();

				System.out.println("\nEnter the Student admission year: ");
				admyear = sc.nextInt();

				System.out.println("\nEnter the Student academic year: ");
				acdyear = sc.nextInt();

				System.out.println("\nEnter the Semester: ");
				sem = sc.nextInt();

				flag = 1;
	}
	
}

//---------------------------------------Definition of Menus---------------------------------------

class Menus 
{
	String username,password; 					

    public void adminlogin()					 //Admin Login Administration
    {
    	int flag = 0;
	    username = "Admin123";
	    password = "mahedubai";
	    Student s = new Student();

	    Scanner input1 = new Scanner(System.in);
	    System.out.print("\n\tEnter Username: ");
	    s.user = input1.next();

	    Scanner input2 = new Scanner(System.in);
	    System.out.print("\tEnter Password: ");
	    s.pass = input2.next();

	    if (s.user.equals(username) && s.pass.equals(password))
	    {
	    	flag = 1;
	        System.out.println("\n\tSuccessfully Logged In!");
	        System.out.println("\t===============================================");
	        adminmenu();
	    }

	    else if(flag == 0)
	    {
		    System.out.println("\n\tInvalid Username & Password!");
		    System.out.println("\t===============================================");
   		} 
	}

	public void studentlogin()					//Student Login Authentication
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n\tEnter Username: ");
		username = sc.nextLine();

		System.out.print("\tEnter Password: ");
		password = sc.nextLine(); 

		File f = new File("Students.dat");
		int flag = 0,i=1;
			try 
			{		
				FileInputStream fis = new FileInputStream(f);
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        Student s1 = (Student) ois.readObject();

		        while(s1!=null)						//Read Records
		        {
		        	try
		        	{
			            if(s1.user.equals(username) && s1.pass.equals(password))
			            {
			            	System.out.println("\n\tSuccessfully Logged In!");			
			            	flag = 1;
					        studentmenu(username,password); 
			            }
						s1 = (Student) ois.readObject();
	        		
					}
					catch(EOFException e)    			//Reached End of File
					{
						break; 
					}
				}
				ois.close();

				if(flag == 0)
					System.out.println("\n\tInvalid Username or Password...");
					System.out.println("\t===============================================");
		    }

		    catch(FileNotFoundException e)
		    {
		    	e.printStackTrace();
		    }

		    catch(ClassNotFoundException e)
		    {
		    	e.printStackTrace();
		    }

		    catch(IOException e)
		    {
		    	//e.printStackTrace();
		    }
	}
    
    public static void addStudentsToFile()					//For Admin 
	{
		Student s1[] =  new Student[20],s;
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the number of student records you want to enter: ");
		int n = sc.nextInt();

		for(int i = 0; i<n ; i++)
		{
			System.out.println("----------------------------------------------");
			System.out.println("Enter the details of Student "+(i+1));
			s1[i] = new Student();
			s1[i].addstudent();
			s1[i].addsub();
		}
		
		int i=0;

		File f = new File("Students.dat");							
		try 
		{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
			while(s1[i]!=null)							//Writing Records to the File
			{

				s = s1[i];
				oos.writeObject(s);
		            	            
		        System.out.println("Record "+(i+1)+" Successfully Added... ");
		        i++;
	    	}
	    	System.out.println("----------------------------------------------");
            oos.close();
            
        }
        
        catch (IOException e) 
        {
            System.out.println("Error initializing stream");
        }
    }


    public static void appendStudents()			//Appends multiple records into the Binary File
	{
		
		Student s1[] =  new Student[20],s;
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the number of student records you want to enter: ");
		int n = sc.nextInt();

		for(int i = 0; i<n ; i++)
		{
			System.out.println("\nEnter the details of Student "+(i+1));
			System.out.println("----------------------------------------------");
			s1[i] = new Student();
			s1[i].addstudent();
			s1[i].addsub();
		}

		int i=0;

		File f = new File("Students.dat");
		boolean append = f.exists();  // if file exists then append, otherwise create new

		try 
		{
	        FileOutputStream fos = new FileOutputStream(f,append);
		    AppendableObjectOutputStream oos = new AppendableObjectOutputStream(fos,append);
			
			while(s1[i]!=null)						//Appending Records to the file
			{

				s = s1[i];
				oos.writeObject(s);
		            	            
		        System.out.println("Record "+(i+1)+" Successfully Added... ");
		        i++;
	    	}
	    	oos.close();
	    }
        
        catch (IOException e) 
        {
            System.out.println("Error initializing stream");
        }
        catch(Exception e)
        {

        }    	
    }

    public static void displayStud()
	{
		File f = new File("Students.dat");
		int flag = 0;

			try 
			{		
				FileInputStream fis = new FileInputStream(f);
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        Student s1 = (Student) ois.readObject();

		        while(s1 != null)			//Reading all Records from the Binary File	
		        {
		            s1.display(1);			//Printing all Records 

					s1 = (Student) ois.readObject();
					flag = 1;
				}
				ois.close();

				if(flag == 0)
					System.out.println("\n\tFile does not exist...");
		    }


		    catch(FileNotFoundException e)
		    {
		    	System.out.println("\n\tFile does not exist...");
		    	//e.printStackTrace();
		    }

		    catch(ClassNotFoundException e)
		    {
		    	System.out.println("\n\nClass not found...");
		    	//e.printStackTrace();
		    }

		    catch(IOException e)
		    {
		    	//e.printStackTrace();
		    }

		    catch(Exception e)
	        {
	        	
	        }
	}

	public static void searchStud(String user, String pass, int isStudent)			//O for Admin 1 for student Indicator
	{
		File f = new File("Students.dat");
		int flag = 0;
			try 
			{
				Scanner sc = new Scanner(System.in);

				FileInputStream fis = new FileInputStream(f);
		        ObjectInputStream ois = new ObjectInputStream(fis);
				
				if(isStudent == 0)					//For Admin
				{

					System.out.println("\t+=============================================+");
					System.out.println("\t|                                             |");
					System.out.println("\t|               Search a Record               |");
					System.out.println("\t|                                             |");
					System.out.println("\t+=============================================+");


		        	System.out.print("\tEnter the id of the Student you want to Search: ");
		        	int id = sc.nextInt();
		        	System.out.println("");

		        	Student s1 = (Student) ois.readObject();

		        	while(s1 != null)
			        {
			        	try
			        	{

				            if(s1.id == id)
				            {	
				            	System.out.println("\n\tRecord Found...");

				            	s1.display(1);
				            	System.out.println("\n");
								flag = 1;
								break;
							}
							s1 = (Student) ois.readObject();
			        	}

						catch(EOFException e)    			//Reached End of File
						{
							//e.printStackTrace();
							break; 
						}
					}

					if(flag == 0)
					{	
						System.out.println("\t----------------------------------------------");
						System.out.println("\n\tRecord not Found...");
					}
				}

				if(isStudent == 1)				//For Student
				{

					System.out.println("\t+=============================================+");
					System.out.println("\t|                                             |");
					System.out.println("\t|               View Your Record              |");
					System.out.println("\t|                                             |");
					System.out.println("\t+=============================================+");

					System.out.print("\tPlease confirm your Student id to view your Details: ");
		        	int id = sc.nextInt();
		        	System.out.println("");

		        	Student s1 = (Student) ois.readObject();

		        	while(s1 != null)
			        {
			        	try
			        	{

				            if(s1.id == id && s1.user.equals(user) && s1.pass.equals(pass))		
				            						//Validate entered student user, pass with student user, pass from File
				            {	
				            	s1.display(0);
				            	System.out.println("\n");
								flag = 1;
								break;
							}
							s1 = (Student) ois.readObject();
			        	}

							catch(EOFException e)    			//Reached End of File
							{
								break; 
							}
					}

					if(flag == 0)
					{	
						System.out.println("\t----------------------------------------------");
						System.out.println("\n\t2 Step Authentication (ID) Failed ......");
					}
				}
		        		       
				ois.close();

		    }

		    catch(FileNotFoundException e)
		    {
		    	System.out.println("No Records Present...");
		    	//e.printStackTrace();
		    }

		    catch(ClassNotFoundException e)
		    {
		    	//e.printStackTrace();
		    }

		    catch(IOException e)
		    {
		    	//e.printStackTrace();
		    }

		    catch(Exception e)
		    {
		    	
		    }
	}	

	public void deleteStud()					//Asks the Admin for the ID And Deletes the Appropriate Record from the Binary File
	{	
		File oldFile = new File("Students.dat");
		int i = 0,flag = 0;
		char choice = 'N';

		do
		{	
			try 
			{									
				i = 0;	
												
				Scanner sc = new Scanner(System.in);

				System.out.println("\t+=============================================+");
				System.out.println("\t|                                             |");
				System.out.println("\t|               Delete a Record               |");
				System.out.println("\t|                                             |");
				System.out.println("\t+=============================================+");
				

				FileInputStream fis = new FileInputStream(oldFile);
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        Student s = (Student) ois.readObject();	

		        Student s1[] = new Student[20];   		//ARRAY OF OBJECTS of class Student
		        
		        System.out.print("\tEnter the id of the Student you want to delete: ");
		        int id = sc.nextInt();

		        while(s != null)			 	//Reading Loop to Store our Records into the Array of Students
		        {	
					try
					{			
						if(s.id != id)
						{
							s1[i] = s;	
							i++;
						}
						else
							flag = 1;
						s = (Student) ois.readObject();
						
					}
					catch(EOFException e)
				    {
				    	break;
				    }
				}
				ois.close();
				oldFile.delete();

				if(flag ==1)
			 	{

				    File newFile = new File("Students.dat");
			 		FileOutputStream fos = new FileOutputStream(newFile);
			        ObjectOutputStream oos = new ObjectOutputStream(fos);

					i=0;

			    	while(s1[i]!=null)					//Write Loop to Write our Records from the Array of Students 
					{
						try
						{
							s = s1[i];
							oos.writeObject(s);         
					        i++;						
						}
						catch(EOFException e)
					    {
					    	break;
					    }
			    	}
			    	oos.close();
					System.out.println("\t+---------------------------------------------+");
					System.out.println("\n\tRecord successfully Deleted!!");
			 	}

			 	else
			 	{
			 		System.out.println("\t+---------------------------------------------+");
			 		System.out.println("Record with ID : "+id+" not found... ");
			 	}

			 	System.out.print("Do you want to Delete another record (Y/N) : ");
				choice = sc.next().charAt(0);

		    }

		    catch(FileNotFoundException e)
		    {
		    	System.out.println("No Records Present...");
		    	//.printStackTrace();
		    }

		    catch(ClassNotFoundException e)
		    {
		    	//e.printStackTrace();
		    }

		    catch(IOException e)
		    {
		    	System.out.println("Input Mismatch...");
		    	//e.printStackTrace();
		    }	

		    catch(Exception e)
		    {
		    	
		    }		


		}while(choice == 'Y' || choice == 'y');
		    			
	}

	public void modifyStud()
	{
		File oldFile = new File("Students.dat");
		int i = 0,flag = 0;
		char choice = 'N';
		
		do
		{	
			try 
			{									
				i = 0;
				Scanner sc = new Scanner(System.in);

					System.out.println("\t+=============================================+");
					System.out.println("\t|                                             |");
					System.out.println("\t|               Edit a Record                 |");
					System.out.println("\t|                                             |");
					System.out.println("\t+=============================================+");
					

					FileInputStream fis = new FileInputStream(oldFile);
			        ObjectInputStream ois = new ObjectInputStream(fis);
			        Student s = (Student) ois.readObject();	

			        Student s1[] = new Student[20]; 		//ARRAY OF OBJECTS of class Student
			        
			        System.out.print("\tEnter the id of the Student you want to edit: ");
			        int id = sc.nextInt();					//102	
			        System.out.println("");

			        
	 				while(s != null)			 			//Reading Loop to Store our Records into the Array of Students
			        {	
						try
						{			
							s1[i] = new Student();		
							if(s.id != id)					//NOT EQUAL TO ID
							{
								s1[i] = s;	
								//s1[i].display(1);			//Printing all the record having S1.id not EQUAL TO ID
							}	
							else if(s.id == id)   			//EQUAL TO ID
							{	
								s1[i].addstudent();			//New data
								s1[i].addsub();	

								flag = 1;		
							}

							i++;
							s = (Student) ois.readObject();	
							
						}
						catch(EOFException e)
					    {
					    	break;
					    }
					}

					ois.close();
					oldFile.delete();

					if(flag ==1)
				 	{
				 		i = 0;

					    File newFile = new File("Students.dat");		//Overwrites and creates a Blank Students.dat
				 		FileOutputStream fos = new FileOutputStream(newFile);
				        ObjectOutputStream oos = new ObjectOutputStream(fos);
						
				    	while(s1[i]!=null)					//Write Loop to Write our Records from the Array of Students 
						{
							try
							{
								s = s1[i];
								oos.writeObject(s);         
						        i++;						
							}
							catch(EOFException e)
						    {
						    	break;
						    }
				    	}
				    	oos.close();
				    	System.out.println("\t+---------------------------------------------+");
						System.out.println("\tRecord successfully Edited!!");
						
				 	}

					if(flag == 0)
					{
						System.out.println("\t+---------------------------------------------+");
						System.out.println("Record with ID : "+id+" not found... ");
					}
					System.out.print("\nDo you want to edit another record (Y/N) : ");
					choice = sc.next().charAt(0);

			    }

			    catch(FileNotFoundException e)
			    {
			    	System.out.println("\t+---------------------------------------------+");
			    	System.out.println("No Records Present...");
			    	//e.printStackTrace();
			    }

			    catch(ClassNotFoundException e)
			    {
			    	//e.printStackTrace();
			    }

			    catch(IOException e)
			    {
			    	//e.printStackTrace();
			    }

			    catch(Exception e)
			    {
			    	
			    }

			}while(choice == 'Y' || choice == 'y');
	}	
		        
	public static void gradeSheet(String user, String pass)			//Displaying Grade Sheet Business Cycle Part 2
	{
		File f = new File("Students.dat");
		int flag = 0;
			try 
			{
				Scanner sc = new Scanner(System.in);
				FileInputStream fis = new FileInputStream(f);
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        Student s1 = (Student) ois.readObject();

		        System.out.print("\tPlease confirm your Student id to view your Grade Sheet: ");
		        int id = sc.nextInt();
		        System.out.println("");

		        while(s1 != null)
			    {
			    	try
			    	{
			            if(s1.id == id && s1.user.equals(user) && s1.pass.equals(pass))		//2 Step Authentication
			            {	
			            	s1.displaygradesheet();
			            	System.out.println("\n");
							flag = 1;
							break;
						}
						s1 = (Student) ois.readObject();
					}
					catch(EOFException e)
					{
						//e.printStackTrace();
						break;
					}

				}

				ois.close();

				if(flag == 0)
				{	
						System.out.println("\t----------------------------------------------");
						System.out.println("\n\t2 Step Authentication (ID) Failed ......");
				}
		    }

		    catch(FileNotFoundException e)
		    {
		    	e.printStackTrace();
		    }

		    catch(ClassNotFoundException e)
		    {
		    	e.printStackTrace();
		    }

		    catch(IOException e)
		    {
		    	//e.printStackTrace();
		    }
	}

	void adminmenu()						//Displayed after Login
	{
		
		Scanner sc = new Scanner(System.in);

		int flag = 0,choice;
		while(flag == 0)
		{
			try
			{

				System.out.println("");
				System.out.println("\t+=============================================+");
				System.out.println("\t|                                             |");
				System.out.println("\t|               Welcome Admin                 |"); 
			    System.out.println("\t|                                             |");
			    System.out.println("\t+=============================================+");
			    System.out.println("\t|                                             |");          
			    System.out.println("\t|   1--->Create new Student Record            |");
			    System.out.println("\t|   2--->Add Student Records                  |");
			    System.out.println("\t|   3--->Display all Students                 |");
			    System.out.println("\t|   4--->Search a Student Record based on id  |");
			    System.out.println("\t|   5--->Edit a Student Record based on id    |");
			    System.out.println("\t|   6--->Delete a Student Record based on id  |");
			    System.out.println("\t|   7--->Exit to main menu                    |");
			    System.out.println("\t|                                             |");
				System.out.println("\t+=============================================+");
			    System.out.print("\tEnter your Choice: ");
			    choice = sc.nextInt();
			    System.out.println("");

			    switch(choice)
			    {
			    	case 1: addStudentsToFile();
			    			break;
					case 2: appendStudents();
			    			break;
			    	case 3: displayStud();
			    			break;
			    	case 4: searchStud("","",0);					
			    			break;
			    	case 5: modifyStud();
			    			break;
			    	case 6: deleteStud();
			    			break;
			   	    case 7: return;
			    	default:System.out.println("\nInvalid choice ...");
			    }
			}	

			catch(Exception e)
		    {
		    	System.out.println("\t----------------------------------------------");
		    	System.out.println("\tInvalid Input...");
		    	break;
		    }
		}
	}

	void studentmenu(String username,String password) 		//Displayed after Login
	{
		int flag=0,choice;
		Scanner sc = new Scanner(System.in);
		while(flag == 0)
		{
			try
			{ 
				System.out.println("");
				System.out.println("\t+=============================================+");
				System.out.println("\t|                                             |");
				System.out.println("\t|               Welcome Student               |"); 
			    System.out.println("\t|                                             |");
			    System.out.println("\t+=============================================+");
			    System.out.println("\t|                                             |");
			    System.out.println("\t|         1--->Display Student Record         |");              
			    System.out.println("\t|         2--->Display Grade Sheet            |");
			    System.out.println("\t|         3--->Exit to Main Menu              |");
			    System.out.println("\t|                                             |");
			    System.out.println("\t+=============================================+");
			    System.out.print("\tEnter your Choice: ");
			    choice = sc.nextInt();
			    System.out.println("");

			    switch(choice)
			    {
			    	case 1: searchStud(username, password, 1);
			    			break;
			    	case 2: gradeSheet(username, password);
			    			break;
			   	    case 3: return;
			    	default:System.out.println("\nInvalid choice ...");
			    }
			}

			catch(Exception e)
		    {
		    	System.out.println("\t----------------------------------------------");
		    	System.out.println("\tInvalid Input...");
		    	break;
		    }
		}
	}

	void mainmenu()						//Welcome Screen Displayed on Startup
	{
		Scanner sc = new Scanner(System.in);

		int flag = 0, choice;
		
		System.out.println("\n");
		System.out.println("\t\t      _________________________________");
		System.out.println("\t\t     /                                /|");
		System.out.println("\t\t    /                                / |");
		System.out.println("\t\t   /                                /  |");
		System.out.println("\t\t  /                                /   |");
		System.out.println("\t\t /________________________________/    |");
		System.out.println("\t\t |      ___________________      |    /|");
		System.out.println("\t\t |     |     WELCOME TO    |     |   / |");
		System.out.println("\t\t |     |      STUDENT      |     |  /  |");
		System.out.println("\t\t |     |     MANAGEMENT    |     | /   |");
		System.out.println("\t\t |     |___________________|     |/   /|");
		System.out.println("\t\t |_______________________________|   / /");
		System.out.println("\t\t |   _______    ___    _______   |  / /");
		System.out.println("\t\t |  |       |  | | |  |       |  | / /");
		System.out.println("\t\t |  |_______|  |-|-|  |_______|  |/ /");
		System.out.println("\t\t |_____________|_|_|_____________/ /");
		System.out.println("\t\t/_____________/_/_/_____________/ /");
		System.out.println("\t\t|_______________________________|/");
		

		while(flag == 0)
		{
			try
			{
				System.out.println("");
				System.out.println("\t+=============================================+");
				System.out.println("\t|                                             |");
				System.out.println("\t|                 Main Menu                   |"); 
			    System.out.println("\t|                                             |");
			    System.out.println("\t+=============================================+");
			    System.out.println("\t|                                             |");
			    System.out.println("\t|             1--->Admin Login                |");
			    System.out.println("\t|             2--->Student Login              |");
			    System.out.println("\t|             3--->Exit                       |");
			    System.out.println("\t|                                             |");
			    System.out.println("\t+=============================================+");
			    System.out.print("\tPlease select your Role: ");
			    choice = sc.nextInt();

			    switch(choice)
			    {
			    	case 1: adminlogin();	
			    			break;
			    	case 2: studentlogin();
			    			break;
			    	case 3:
						    System.out.println("\n\t+=============================================+");
							System.out.println("\t|+-------------------------------------------+|");
							System.out.println("\t||+=========================================+||");
							System.out.println("\t|||                                         |||");
							System.out.println("\t|||                                         |||");
							System.out.println("\t|||               THANK YOU !!              |||"); 
							System.out.println("\t|||                                         |||");
						    System.out.println("\t|||                                         |||");
						    System.out.println("\t||+=========================================+||");
						    System.out.println("\t|+-------------------------------------------+|");
						    System.out.println("\t+=============================================+");
						    
			    			flag = 1;
			    			break;
			    	default:System.out.println("\n\t----------------------------------------------");
			    			System.out.println("\nInvalid choice ...");
			    }
			}

			catch(Exception e)
		    {	
		    	System.out.println("\t----------------------------------------------");
		    	System.out.println("\tInvalid Input...");
		    	mainmenu()
;		    }	
		}
	}
}

//---------------------------------------Driver Class---------------------------------------
class Student_Management
{
	public static void main(String ar[])
	{
		Menus m = new Menus();
		m.mainmenu();			
		}
}
//---------------------------------------End of Program---------------------------------------
