package com.dkit.sd2a.ethan.sia;


import java.io.*;
import java.util.*;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager {

    // Store all the Course details.
    // Requires fast access given courseId.



    private HashMap<String,Course> courseMap = new HashMap<>();
    private final static Scanner kb = new Scanner(System.in);

    public CourseManager() {

        courseMap = new HashMap<>();

    }

    public Course getCourse(String courseId)
    {


        Course course = courseMap.get(courseId);
        if(course==null)
        {
            return null;
        }
        return new Course(course);

    }

    public Course getCourseInput()
    {
        System.out.print("Please input the courseId of the course you want to find: ");
        String courseId = kb.next();

        Course course = courseMap.get(courseId);
        if(course==null)
        {
            return null;
        }
        return new Course(course);

    }

    public void addCourse()
    {

        boolean flag = false;
        boolean flag2 =false;
        boolean flag3 =false;
        boolean flag4 = false;
        String courseID ="";
        String level ="";
        String title ="";
        String institution ="";


        while(flag == false)
        {
            System.out.print("Input the courseId with DK + 3 digits : ");
            courseID = kb.next();
            flag = courseID.matches("^(?=.*[D])(?=.*[K])(?=.*[0-9]*$).{5}");
            if (flag == false) System.out.println("You must input the courseId with DK + 3 digits like 'DK128' !");

        }

        while(flag2==false)
        {
            System.out.print("Input your level here like 'Level 08': ");
            level = kb.next();
            flag2 = level.matches("^(?=.*[L])(?=.*[e])(?=.*[v])(?=.*[e])(?=.*[l])(?=.*[0-9]*$).{8}$");
            if (flag2==false)
            {
                System.out.println("You must input exactly same way like the example above but you can change the numeric range from 06 to 10!");
            }
        }

        while(flag3==false)
        {
            System.out.print("Please enter your the title like 'Computing and Operating system' : ");
            title = kb.next();
            flag3 = title.matches("^[A-Z]{1,}[\\.]{0,1}[A-Za-z\\s]{2,60}$");
            if (flag3 ==false)
            {
                System.out.println("You first word's first letter must be upperCase letter and not longer than 60 characters ");


            }

        }

        while(flag4==false)
        {
            System.out.print("Input the institute name like 'Dundalk Institute of Technology' : ");
            institution = kb.next();
            flag4 = institution.matches("^[A-Z]{1,}[\\.]{0,1}[A-Za-z\\s]{2,100}$");
            if (flag4 ==false) System.out.println("You first word's first letter must be Upper Case and not longer that 100 characters");
        }

        Course c = new Course(courseID,level,title,institution);

        if(c==null)
            throw new IllegalArgumentException();
        else
            courseMap.put(c.getCourseId(),new Course(c));
    }

    public void addCourse(Course c)
    {


        if(c==null)
            throw new IllegalArgumentException();
        else
            courseMap.put(c.getCourseId(),new Course(c));
    }

    public void removeCourse(String courseId)
    {

        Course course = courseMap.get(courseId);
        if(course == null)
            throw new NullPointerException();
        else
            courseMap.remove(courseId);

    }

    public void removeCourseInput()
    {
        System.out.print("Please input the courseId of the course you want to delete: ");
        String courseId = kb.next();

        Course course = courseMap.get(courseId);
        if(course == null)
            throw new NullPointerException();
        else
            courseMap.remove(courseId);

    }

    // return List interface type
    public List<Course> getAllCourse() {

        // to store list of cloned cars
        ArrayList<Course> clonedList = new ArrayList<>(courseMap.size());


        for (Map.Entry<String, Course> entry : courseMap.entrySet()) {
            Course course = entry.getValue();   // get course from map entry
            clonedList.add(new Course(course));// add course clone to the List

        }
        return clonedList;    // return the List

    }

    protected void loadCourseFromFile()
    {
        try(Scanner courseFile = new Scanner(new BufferedReader(new FileReader("course.txt"))))
        {

            String input;
            while (courseFile.hasNextLine())
            {
                input = courseFile.nextLine();
                String [] data = input.split(",");
                String courseId = data[0];
                String level = data[1];
                String title = data[2];
                String institution = data[3];
                Course readInCourse = new Course(courseId,level,title,institution);
                this.courseMap.put(readInCourse.getCourseId(), readInCourse);
                System.out.println();
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println("Could not load student.txt.If this is " +
                    "the first time running the app this might fine");
        }
    }

    public void saveCourseToFile()
    {
        try(BufferedWriter studentFile = new BufferedWriter(new FileWriter("course.txt") ))
        {


            for (Map.Entry<String, Course> entry : courseMap.entrySet())
            {
                Course c = entry.getValue();   // get course from map entry
                studentFile.write(c.getCourseId() +","+c.getLevel()+","+c.getTitle()+","+c.getInstitution());
                studentFile.write("\n");
            }

        }
        catch(IOException ioe)
        {
            System.out.println(Colours.PURPLE + "Could not save students." +Colours.RESET);
        }
    }



}







