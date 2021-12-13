package model;

public class Class
{
  private String className;
  public StudentList cStudentList;
  String classID;
  private int semester;

  public Class(String classID, int semester)
  {
    this.cStudentList = new StudentList();
    this.classID = classID;

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
        break;
      default:
        throw new IllegalArgumentException(
            "ClassID should be one of the following: X, Y, Z or DK");
    }

    this.className = classNameBuilder;
    this.classID = classID;
    this.semester = semester;
  }

  public String getClassName()
  {
    return className;
  }

  public StudentList getStudentList()
  {
    return cStudentList.copy();
  }

  public int getSemester()
  {
    return semester;
  }

  public String getClassID()
  {
    return classID;
  }

  public int getNumberOfStudents()
  {
    return cStudentList.size();
  }

  public void addStudentToClass(Student student)
  {
    cStudentList.addStudent(student);
  }

  public void removeStudentFromClass(Student student)
  {
    cStudentList.removeStudent(student);
  }

  public void removeStudentFromClass(String studentID)
  {
    for (int i = 0; i < cStudentList.size(); i++)
    {
      if (cStudentList.getStudent(i).getStudentId().equals(studentID))
      {
        cStudentList.removeStudentByIndex(i);
        break;
      }
      else
        throw new IllegalArgumentException("Student not found");
    }
  }

  public String toString()
  {
    String s = "Class: " + getClassName() + " Number of students: "
        + getNumberOfStudents() + "\nStudents:\n";

    for (int i = 0; i < cStudentList.size(); i++)
    {
      s += cStudentList.getStudent(i) + "\n";
    }
    return s;
  }

  public boolean contains(Student student)
  {
    return cStudentList.contains(student);
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Class))
    {
      return false;
    }
    Class other = (Class) obj;
    return className == other.className && cStudentList.equals(
        other.cStudentList);
  }

}
