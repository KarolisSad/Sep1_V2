package model;

import java.util.ArrayList;

public class CourseList
{
  private ArrayList<Course> courseList;

  public CourseList()
  {
    this.courseList = new ArrayList<>();
  }

  public void addCourse(Course course)
  {
    courseList.add(course);
  }

  public Course getCourseByName(String courseName)
  {
    for (int i = 0; i < courseList.size(); i++)
    {
      if ((courseList.get(i).getCourseName()+courseList.get(i).getMainClass().getClassName()).equals(courseName))
      {
        return courseList.get(i);
      }
    }

    throw new IllegalArgumentException("Course with name: " + courseName + " not found.");
  }


  public int getNumberOfCourses()
  {
    return courseList.size();
  }

  public Course getCourse(int index)
  {
    return courseList.get(index);
  }

  public String toString()
  {
    String s = "Courses: \n";
    for (Course i : courseList)
    {
      s += i + "\n";
    }
    return s;
  }
  public void removeCourse(Course course)
  {
    courseList.remove(course);
  }

  public void removeCourseByIndex(int index)
  {
    courseList.remove(index);
  }
}