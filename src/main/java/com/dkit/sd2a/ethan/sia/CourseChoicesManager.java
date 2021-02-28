package com.dkit.sd2a.ethan.sia;

/**
 *D00225319
 *Ethan Alexandro Yiik Hee Sia
 */

import java.io.*;
import java.util.*;

public class CourseChoicesManager {
    private static Scanner kb = new Scanner(System.in);



    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;


    private HashMap<Integer,List<String>> selectedChoices = new HashMap<>();


    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;

    }

    public Student getStudentDetails(int caoNumber)
    {
        if(studentManager.getStudent(caoNumber)==null)
        {
            return null;
        }

        return studentManager.getStudent(caoNumber);
    }

    public Course getCourseDetails(String courseId)
    {
        if(courseManager.getCourse(courseId)==null)
        {
            return null;
        }

        return courseManager.getCourse(courseId);
    }

    public Course getCourseDetailsInput()
    {
        System.out.print("Please input the courseId of the course you want to find: ");
        String courseId = kb.next();

        if(courseManager.getCourse(courseId)==null)
        {
            return null;
        }

        return courseManager.getCourse(courseId);
    }

    public List<String> getStudentChoices(int caoNumber)
    {
        if(selectedChoices == null)
        {
            return null;
        }

        return selectedChoices.get(caoNumber);
    }

    public List<String> getStudentChoicesInput()
    {
        System.out.print("Please input your caoNumber: ");
        int caoNumber = kb.nextInt();

        if(selectedChoices == null)
        {
            return null;
        }

        return selectedChoices.get(caoNumber);
    }

    public void updateChoices(int caoNumber,List<String> choices)
    {

         selectedChoices.put(caoNumber,choices);
    }

    public void updateChoicesInput()
    {
        String ans = "y";
        String courseId = "";
        boolean flag = false;
        List<String> choices = new ArrayList<>();
        System.out.print("Please input your caoNumber: ");
        int caoNumber = kb.nextInt();
        while(ans.equals("y"))
        {

            System.out.print("Input the courseId with DK + 3 digits : ");
            courseId = kb.next();

                choices.add(courseId);
                System.out.println("Do you want to add another course? Please enter 'y' if you want to add another again.");
                ans=kb.next();


        }


        selectedChoices.put(caoNumber,choices);
    }

    public List<Course> getAllCourses()
    {
       return courseManager.getAllCourse();
    }

    boolean login()
    {
        int input =0;
        String dateBirth ="";
        String password ="";

        System.out.print("Input your caoNumber with 6 digits: ");
        input = kb.nextInt();

        System.out.print("Input your birth of date like '1900-01-01' : ");
        dateBirth = kb.next();

        System.out.print("Please enter your password like '12345Ab#' : ");
        password = kb.next();

        try
        {
            if(studentManager.getStudent(input).getCaoNumber() == input && studentManager.getStudent(input).getDayOfBirth().equals(dateBirth )&& studentManager.getStudent(input).getPassword().equals(password))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(NullPointerException e)
        {
            return false;
        }


    }

    boolean login(int input,String dateBirth,String password)
    {

        try
        {
            if(studentManager.getStudent(input).getCaoNumber() == input && studentManager.getStudent(input).getDayOfBirth().equals(dateBirth )&& studentManager.getStudent(input).getPassword().equals(password))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(NullPointerException e)
        {
            return false;
        }


    }

    protected void loadCourseChoiceFromFile()
    {
        try(Scanner courseChoiceFile = new Scanner(new BufferedReader(new FileReader("courseChoice.txt"))))
        {

            String input;
            while (courseChoiceFile.hasNextLine())
            {
                input = courseChoiceFile.nextLine();
                String [] data = input.split(",");
                int caoNumber = Integer.parseInt(data[0]);
                List<String> choices = new ArrayList<>();
                for (int i = 1; i < data.length; i++)  //continue to end of data line
                {
                    choices.add((data[i]));
                }

                this.selectedChoices.put(caoNumber,choices);

            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println("Could not load booking.If this is " +
                    "the first time running the app this might fine");
        }
    }

    public void saveCourseChoicesToFile()
    {
        try(BufferedWriter courseChoiceFile = new BufferedWriter(new FileWriter("courseChoice.txt") ))
        {


            for (Map.Entry<Integer, List<String>> entry : selectedChoices.entrySet())
            {
                courseChoiceFile.write(entry.getKey()+","+entry.getValue());
                courseChoiceFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.PURPLE + "Could not save students." +Colours.RESET);
        }
    }


}
