package mediator;

import model.*;
import mediator.ScheduleModel;
import mediator.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Class;
import model.StudentList;

public class ScheduleFile
{

  public static StudentList importStudents()
    {
      File file = new File("C:\\Users\\Karolis\\OneDrive - ViaUC\\Desktop\\Imports\\Students.txt");
      StudentList studentList = new StudentList();
      try
      {
        Scanner in = new Scanner(file);
        while (in.hasNext())
        {
          String line = in.nextLine();
          String[] token = line.split(",");
          int semester = Integer.parseInt(token[0].trim());
          String mainClass = token[1].trim();
          String name = token[2].trim();
          int studentID = Integer.parseInt(token[3].trim());
          studentList.addStudent(new Student (name,new StudentID(studentID),mainClass,semester));
        }
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
      return studentList;

    }

  public static ClassList importClasses()
  {
    File file = new File("C:\\Users\\Karolis\\OneDrive - ViaUC\\Desktop\\Imports\\Classes.txt");
    ClassList classList = new ClassList();
    try
    {
      Scanner in = new Scanner(file);
      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        String mainClass = token[0].trim();
        int semester = Integer.parseInt(token[1].trim());
        classList.addClass(new Class(mainClass,semester));
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return classList;

  }


  public static TeacherList importTeachers()
  {
    File file = new File("C:\\Users\\Karolis\\OneDrive - ViaUC\\Desktop\\Imports\\Teachers.txt");
    TeacherList teacherList = new TeacherList();
    try
    {
      Scanner in = new Scanner(file);
      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        String name = token[0].trim();
        String id = token[1].trim();
        teacherList.addTeacher(new Teacher(name,new TeacherID(id)));

      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return teacherList;

  }


  public static RoomList importRooms()
  {
    File file = new File("C:\\Users\\Karolis\\OneDrive - ViaUC\\Desktop\\Imports\\Rooms.txt");
    RoomList roomList = new RoomList();
    try
    {
      Scanner in = new Scanner(file);
      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        String name = token[0].trim();
        int capacity = Integer.parseInt(token[1].trim());
        roomList.addRoom(new Room(capacity,name));



      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return roomList;

  }

  public static CourseList importCourses()
  {
    File file = new File("C:\\Users\\Karolis\\OneDrive - ViaUC\\Desktop\\Imports\\Courses.txt");
    CourseList courseList = new CourseList();
    try
    {
      Scanner in = new Scanner(file);
      while (in.hasNext())
      {
        String line = in.nextLine();
        String[] token = line.split(",");
        int semester = Integer.parseInt(token[0].trim());
        String classLetter = token[1].trim();
        String courseName = token[2].trim();
        String teacherId = token[3].trim();
        int ects = Integer.parseInt(token[4].trim());

        courseList.addCourse(new Course(courseName,ects, new Class(classLetter,semester),new Teacher(teacherId,new TeacherID(teacherId))));



      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    return courseList ;

  }


  }


