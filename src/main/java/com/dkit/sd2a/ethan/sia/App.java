package com.dkit.sd2a.ethan.sia;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start()
    {

        // load students
        StudentManager studentManager = new StudentManager();
        studentManager.loadStudentFromFile();

        // load courses
        CourseManager courseManager= new CourseManager();
        courseManager.loadCourseFromFile();

        //load course choices
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);
        mgr.loadCourseChoiceFromFile();

        //Run Menu
        doMainMenuLoop(studentManager,courseManager,mgr);

       //Save Course choices to file
        mgr.saveCourseChoicesToFile();

    }

    private static void doMainMenuLoop(StudentManager studentManager, CourseManager courseManager,CourseChoicesManager mgr)
    {

        Scanner kb = new Scanner(System.in);
        boolean loop = true;
        MainMenu menuOption;
        int option;
        while(loop)
        {
            printMainMenu();
            try
            {
                option = kb.nextInt();
                kb.nextLine();
                menuOption = MainMenu.values()[option];
                switch(menuOption)
                {
                    case STUDENT_MENU:
                        doStudentMenuLoop(mgr);
                        break;
                    case ADMIN_MENU:
                        doAdminMenuLoop(studentManager,courseManager);
                        break;
                    case EXIT:
                        loop = false;
                        break;

                }
            }
            catch(InputMismatchException | IndexOutOfBoundsException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
    }

    private static void printMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i = 0; i < MainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + MainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option (enter 0 to cancel):>");
    }

    private static void doStudentMenuLoop( CourseChoicesManager mgr)
    {

        Scanner kb = new Scanner(System.in);
        boolean loop = true;
        StudentMenu menuOption;
        int option;
        while(loop)
        {
            printStudentMenu();
            try
            {
                option = kb.nextInt();
                kb.nextLine();
                menuOption = StudentMenu.values()[option];
                switch(menuOption)
                {
                    case LOGIN:
                        System.out.println(mgr.login());
                        break;
                    case DISPLAY_A_COURSE:
                        System.out.println(mgr.getCourseDetailsInput());
                        break;
                    case DISPLAY_ALL_COURSE:
                        System.out.println(mgr.getAllCourses());
                        break;
                    case DISPLAY_CURRENT_CHOICES:
                        System.out.println(mgr.getStudentChoicesInput());
                        break;
                    case UPDATE_CHOICES:
                        mgr.updateChoicesInput();
                        break;
                    case LOGOUT:
                        loop = false;
                        break;

                }
            }
            catch(InputMismatchException | IndexOutOfBoundsException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
    }

    private static void printStudentMenu()
    {
        System.out.println("\n Options to select:");
        for(int i = 0; i < StudentMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + StudentMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option (enter 0 to cancel):>");
    }

    private static void doAdminMenuLoop(StudentManager studentManager, CourseManager courseManager)
    {

        Scanner kb = new Scanner(System.in);
        boolean loop = true;
        AdminMenu menuOption;
        int option;
        while(loop)
        {
            printAdminMenu();
            try
            {
                option = kb.nextInt();
                kb.nextLine();
                menuOption = AdminMenu.values()[option];
                switch(menuOption)
                {
                    case ADD_COURSE:
                        courseManager.addCourse();
                        break;
                    case DELETE_COURSE:
                        courseManager.removeCourseInput();
                        break;
                    case DISPLAY_ALL_COURSE:
                        System.out.println(courseManager.getAllCourse());
                        break;
                    case DISPLAY_A_COURSE_WITH_COURSEID:
                        Course c = courseManager.getCourseInput();
                        System.out.println(c);
                        break;
                    case ADD_STUDENT:
                        studentManager.addStudent();
                        break;
                    case DELETE_STUDENT:
                        studentManager.removeStudentInput();
                        break;
                    case DISPLAY_A_STUDENT:
                        Student s = studentManager.getStudentInput();
                        System.out.println(s);
                        break;
                    case SAVE_AND_EXIT:
                        studentManager.saveStudentToFile();
                        courseManager.saveCourseToFile();
                        loop = false;
                        break;

                }
            }
            catch(InputMismatchException | IndexOutOfBoundsException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
    }

    private static void printAdminMenu()
    {
        System.out.println("\n Options to select:");
        for(int i = 0; i < AdminMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + AdminMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option (enter 0 to cancel):>");
    }


}
