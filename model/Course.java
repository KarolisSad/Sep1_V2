package model;

public class Course
{
  private String courseName;
  private int ectsPoints;
  private TeacherList courseTeacherList;
  private StudentList courseStudentList;
  private Class mainClass;

  public Course(String courseName, int ectsPoints, Class mainClass,
      Teacher teacher)
  {
    if (ectsPoints == 5 || ectsPoints == 10)
    {
      this.ectsPoints = ectsPoints;
    }
    else
    {
      throw new IllegalArgumentException("ECTS points should be 5 or 10");
    }

    if (mainClass == null)//class id , semester
    {
      throw new NullPointerException("ERROR CLASS");
    }
    else
    {
      this.mainClass = mainClass;
    }

    if (courseName.equals(""))
    {
      throw new IllegalArgumentException("Course Name should not be empty.");
    }
    else
    {
      this.courseName = courseName;
    }

    this.courseTeacherList = new TeacherList();
    this.courseStudentList = mainClass.getStudentList();

    if (teacher == null)
    {
      throw new NullPointerException(
          "Error Teacher - course needs at least one teacher");
    }
    else
    {
      this.courseTeacherList.addTeacher(teacher);
    }

  }

  public String getCourseName()
  {
    return courseName;
  }

  public StudentList getCourseStudentList()
  {
    return courseStudentList;
  }

  public TeacherList getCourseTeacherList()
  {
    return courseTeacherList;
  }

  public int getCourseSize()
  {
    return courseStudentList.size();
  }

  public void addStudentToCourse(Student student)
  {
    courseStudentList.addStudent(student);
  }

  public Class getMainClass()
  {
    return mainClass;
  }

  public void removeStudentFromCourse(Student student)
  {
    courseStudentList.removeStudent(student);
  }

  public void addTeacherToCourse(Teacher teacher)
  {
    courseTeacherList.addTeacher(teacher);
  }

  public void removeTeacherFromCourse(Teacher teacher)
  {
    if (courseTeacherList.size() > 1)
    {
      courseTeacherList.removeTeacher(teacher);
    }
    else
    {
      throw new IllegalArgumentException(
          "Course needs to have a minimum of 1 teacher. Add a new teacher before removing.");
    }
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Course))
    {
      return false;
    }
    Course other = (Course) obj;
    return courseName.equals(other.courseName) && ectsPoints == other.ectsPoints
        && courseTeacherList.equals(other.courseTeacherList)
        && courseStudentList.equals(other.courseStudentList);
  }

  public String toString()
  {
    return mainClass.getSemester() + courseName;
  }

  public int getEctsPoints()
  {
    return ectsPoints;
  }

}
