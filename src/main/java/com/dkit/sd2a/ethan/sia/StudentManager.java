package com.dkit.sd2a.ethan.sia;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.io.*;
import java.util.*;

public class StudentManager {

    private HashMap<Integer,Student> studentMap = new HashMap<>();
    private static Scanner kb = new Scanner(System.in);



    public StudentManager() {

        studentMap = new HashMap<>();
        // Hardcode some values to get started

        // later, load from text file "students.dat" and populate studentsMap
    }



    public Student getStudent( int caoNumber)
    {
        Student student = studentMap.get(caoNumber);
        if(student == null)
        {
            return null;
        }
        return new Student(student);
    }

    public Student getStudentInput()
    {
        System.out.print("Please input the caoNumber of the student you want to find: ");
        int caoNumber = kb.nextInt();

        Student student = studentMap.get(caoNumber);
        return new Student(student);

    }

    public void addStudent(Student student)
    {

        if(student == null)
            throw new IllegalArgumentException();
        else if(isRegistered(student.getCaoNumber()))
            System.out.println("This student is exist");
        else
            studentMap.put(student.getCaoNumber(),new Student(student));

    }



    public void addStudent()
    {
        boolean flag = false;
        boolean flag2 =false;
        boolean flag3 =false;
        boolean flag4 = false;
        int input =0;
        String dateBirth ="";
        String password ="";
        String email ="";


            while(flag == false)
            {
                    System.out.print("Input your caoNumber with 6 digits: ");
                    input = kb.nextInt();
                    String inputTS = String.valueOf(input);
                    flag = inputTS.matches("\\d{6}");
                    if (flag == false) System.out.println("You must input 6 digits!");

            }

            while(flag2==false)
            {
                System.out.print("Input your birth of date like '1900-01-01' : ");
                dateBirth = kb.next();
                flag2 = dateBirth.matches("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
                if (flag2==false) System.out.println("You must input valid years and date!");
            }

            while(flag3==false)
            {
                System.out.print("Please enter your password like '12345Ab#' : ");
                password = kb.next();
                flag3 = password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
                if (flag3 ==false) System.out.println("You password must contain at least 8 chars include at least one digit "+
                        "one lower alpha char and one upper alpha char " +
                        "one char within a set of special chars (@#%$^ etc.) "+
                        "and also cannot contain space, tab, etc.!");

            }

            while(flag4==false)
            {
                System.out.print("Input your email like 'abc@gmail.com' : ");
                email = kb.next();
                flag4 = email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
                if (flag4 ==false) System.out.println("You must input valid email address!");
            }

            Student student = new Student(input,dateBirth,password,email);



        if(student == null)
            throw new IllegalArgumentException();
        else if(isRegistered(student.getCaoNumber()))
            System.out.println("This student is exist");
        else
            studentMap.put(student.getCaoNumber(),new Student(student));

    }

    public void removeStudent(int caoNumber)
    {

        Student student = studentMap.get(caoNumber);
        if(student == null)
            throw new NullPointerException();
        else
            studentMap.remove(caoNumber);

    }


    public void removeStudentInput()
    {
        System.out.print("Please input the caoNumber of the student you want to remove. ");
        int caoNumber = kb.nextInt();

        Student student = studentMap.get(caoNumber);
        if(student == null)
            throw new NullPointerException();
        else
        studentMap.remove(caoNumber);


    }

    // return List interface type
    public List<Student> getAllStudent() {

        // to store list of cloned cars
        ArrayList<Student> clonedList = new ArrayList<>(studentMap.size());


        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            Student student = entry.getValue();   // get course from map entry
            clonedList.add(new Student(student));       // add course clone to the List
        }
        return clonedList;    // return the List

    }

   public boolean isRegistered( int caoNumber)
   {
       for (Map.Entry<Integer, Student> entry : studentMap.entrySet())
       {
           Student student = entry.getValue();   // get course from map entry
           if (student.getCaoNumber() == (caoNumber) )
           {
               return true;
           }
       }

       return false;
   }

protected void loadStudentFromFile()
{
    try(Scanner studentFile = new Scanner(new BufferedReader(new FileReader("student.txt"))))
    {

        String input;
        while (studentFile.hasNextLine())
        {
            input = studentFile.nextLine();
            String [] data = input.split(",");
            int caoNumber = Integer.parseInt(data[0]);
            String dateOfBirth = data[1];
            String password = data[2];
            String email = data[3];
            Student readInStudent = new Student(caoNumber,dateOfBirth,password,email);
            this.studentMap.put(readInStudent.getCaoNumber(), readInStudent);
        }
    }
    catch(FileNotFoundException fne)
    {
        System.out.println("Could not load student.txt.If this is " +
                "the first time running the app this might fine");
    }
}

    public void saveStudentToFile()
    {
        try(BufferedWriter studentFile = new BufferedWriter(new FileWriter("student.txt") ))
        {
            ArrayList<Student> clonedList = new ArrayList<>(studentMap.size());


            for (Map.Entry<Integer, Student> entry : studentMap.entrySet())
            {
                Student s = entry.getValue();   // get course from map entry
                studentFile.write(s.getCaoNumber() +","+s.getDayOfBirth()+","+s.getPassword()+","+s.getEmail());
                studentFile.write("\n");
            }

        }
        catch(IOException ioe)
        {
            System.out.println(Colours.PURPLE + "Could not save students." +Colours.RESET);
        }
    }

}

