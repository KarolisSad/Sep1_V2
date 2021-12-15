package model;

public class Student
{
  private String name;
  private StudentID studentID;
  private String className;
  public int semester;
  private String classID;

  public Student(String name, StudentID studentID, String classID, int semester)
  {
    if (name.equals(""))
    {
      throw new IllegalArgumentException("Name should not be empty.");
    }
    else
    {
      this.name = name;
    }
    if (studentID == null)
    {
      throw new NullPointerException("Student ID should not be null");
    }
    else
    {
      this.studentID = studentID;
    }

    String classNameBuilder = "";
    switch (semester)
    {
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
        classNameBuilder += semester;
        this.semester =semester;
        break;
      default:
        throw new IllegalArgumentException(
            "Semester should be an integer between 1-7.");
    }

    switch (classID)
    {
      case "x":
        classNameBuilder += "X";
        break;
      case "y":
        classNameBuilder += "Y";
        break;
      case "z":
        classNameBuilder += "Z";
        break;
      case "Y":
      case "X":
      case "Z":
      case "DK":
        classNameBuilder += classID;
        this.classID = classID;
        break;
      default:
        throw new IllegalArgumentException(
            "ClassID should be one of the following: X, Y, Z or DK");
    }

    this.className = classNameBuilder;

  }

  public String getStudentId()
  {
    return studentID.getIdAsString();
  }

  public String getClassName()
  {
    return className;
  }

  // TESTING

  public String toString()
  {
    return "Name: " + name + ", StudentID: " + getStudentId();
  }

  public String getName()
  {
    return name;
  }

  public int getSemester()
  {
    return semester;
  }

  public String getClassID()
  {
    return classID;
  }
}