package model;

import java.util.ArrayList;

public class StudentList
{
  private ArrayList<Student> studentList;

  public StudentList()
  {
    this.studentList = new ArrayList<>();
  }

  public void addStudent(Student student)
  {
    studentList.add(student);
  }

  public void removeStudent(Student student)
  {
    if (studentList.contains(student))
    {
      studentList.remove(student);
    }
    else
    {
      throw new IllegalArgumentException("Student not in selected list: ");
    }
  }

  public void removeStudentByIndex(int index)
  {
    studentList.remove(index);
  }

  public void removeStudentByID(String studentID)
  {
    for (int i = 0; i < studentList.size(); i++)
    {
      if (studentList.get(i).getStudentId().equals(studentID))
      {
        studentList.remove(i);
        break;
      }
    }
     throw new IllegalArgumentException("Student not found");
  }

  public int size()
  {
    return studentList.size();
  }

  public Student getStudent(int index)
  {
    return studentList.get(index);
  }

  public StudentList copy()
  {
    StudentList other = new StudentList();
    for (int i = 0; i < studentList.size(); i++)
    {
      other.addStudent(studentList.get(i));
    }
    return other;
  }

  public boolean contains(Student student)
  {
    return studentList.contains(student);
  }

  public boolean containsById(int id)
  {
    for (int i = 0;i< studentList.size();i++)
    {
      if (Integer.parseInt(studentList.get(i).getStudentId()) == id)
      {
        return false;
      }
    }
    return true;
  }

  // TESTING

  public String toString()
  {
    String s = "Students in list:\n";
    for (int i = 0; i < studentList.size(); i++)
    {
      s += studentList.get(i) + "\n";
    }
    return s;
  }
}

