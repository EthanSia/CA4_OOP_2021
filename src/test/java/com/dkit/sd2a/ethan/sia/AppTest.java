package com.dkit.sd2a.ethan.sia;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testStudentClone()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        assert(s1 != s2);

//        assert(s1.equals(s2));
//        s2.setCaoNumber(99991111);
//        assert(s1.equals(s2));
    }

    @Test
    public void testAddStudent()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        assertNotNull(null,studentManager.getStudent(412533));

    }

    @Test
    public void testRemoveStudent()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        System.out.println(studentManager.getAllStudent());
        studentManager.removeStudent(412533);
        assertNull(null,studentManager.getStudent(412533));
    }

    @Test
    public void testGetStudent()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(455622);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);
        System.out.println(studentManager.getStudent(455622));

    }

    @Test
    public void testGetAllStudent()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(455622);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);
        System.out.println(studentManager.getAllStudent());

    }

    public void testCourseClone()
    {
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        assert(c1 != c2);

//        assert(s1.equals(s2));
//        s2.setCaoNumber(99991111);
//        assert(s1.equals(s2));
    }

    @Test
    public void testAddCourse()
    {
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        assertNotNull(null,courseManager.getCourse("DK123"));

    }

    @Test
    public void testRemoveCourse()
    {

        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        System.out.println(courseManager.getAllCourse());
        courseManager.removeCourse("DK123");
        assertNull(null,courseManager.getCourse("DK123"));
        System.out.println(courseManager.getAllCourse());
    }

    @Test
    public void testGetCourse()
    {
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        System.out.println(courseManager.getCourse("DK130"));

    }

    @Test
    public void testGetAllCourse()
    {
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        System.out.println(courseManager.getAllCourse());

    }

    @Test
    public void testGetStudentDetails()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(455622);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager,courseManager);

        assertNotNull(null,mgr.getStudentDetails(455622));

    }

    @Test
    public void testGetCourseDetails()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(455622);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager,courseManager);

        assertNotNull(null,mgr.getCourseDetails("DK123"));

    }

    @Test
    public void testGetStudentChoices()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(455622);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        Course c3 = new Course("DK132","Level 09","Computing","Dundalk Institute of Technology");
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager,courseManager);
        List<String>choiceList = new ArrayList<>();
        choiceList.add("DK123");
        choiceList.add("DK130");
        mgr.updateChoices(455622,choiceList);
        System.out.println(mgr.getStudentChoices(455622));

    }

    @Test
    public void testGetAllCourses()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(455622);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        Course c3 = new Course("DK132","Level 09","Computing","Dundalk Institute of Technology");
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager,courseManager);
        System.out.println(mgr.getAllCourses());

    }

    @Test
    public void testLogin()
    {
        Student s1 = new Student(412533,"02/12/1998","123456","jo@dkit.ie");
        Student s2 = new Student(s1);
        s2.setCaoNumber(455622);
        StudentManager studentManager = new StudentManager();
        studentManager.addStudent(s1);
        studentManager.addStudent(s2);
        Course c1 = new Course("DK123","Level 08","Computing","Dundalk Institute of Technology");
        Course c2 = new Course(c1);
        Course c3 = new Course("DK132","Level 09","Computing","Dundalk Institute of Technology");
        c2.setCourseId("DK130");
        c2.setLevel("Level 10");
        CourseManager courseManager = new CourseManager();
        courseManager.addCourse(c1);
        courseManager.addCourse(c2);
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager,courseManager);
        boolean expected = true;
        boolean actual = mgr.login(412533,"02/12/1998","123456");
        assertEquals(expected,actual);

    }


}
