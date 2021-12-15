package model;

import java.util.ArrayList;

public class TeacherList
{
  private ArrayList<Teacher> teacherList;

  public TeacherList()
  {
    this.teacherList = new ArrayList<>();
  }

  public void addTeacher(Teacher teacher)
  {
    if (!(teacherList.contains(teacher)))
    {
      teacherList.add(teacher);
    }
    else
    {
      throw new IllegalArgumentException("Teacher already added");
    }
  }

  public void removeTeacher(Teacher teacher)
  {
    teacherList.remove(teacher);
  }

  public void removeTeacherByID(String teacherID)
  {
    for (int i = 0; i < teacherList.size(); i++)
    {
      if (teacherList.get(i).getTeacherId().equals(teacherID))
      {
        teacherList.remove(i);
        break;
      }
    }
  }

  public int size()
  {
    return teacherList.size();
  }

  public Teacher getTeacher(int index)
  {
    return teacherList.get(index);
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof TeacherList))
    {
      return false;
    }
    TeacherList other = (TeacherList) obj;
    for (int i = 0; i < teacherList.size(); i++)
    {
      if (!(teacherList.get(i).equals(other.getTeacher(i))))
      {
        return false;
      }
    }
    return true;
  }

  public TeacherList copy()
  {
    TeacherList other = new TeacherList();
    for (int i = 0; i < teacherList.size(); i++)
    {
      other.addTeacher(teacherList.get(i));
    }
    return other;
  }

  public boolean contains(Teacher teacher)
  {
    return teacherList.contains(teacher);
  }


  public String toString()
  {
    switch (teacherList.size())
    {
      case 0:
        return "No teachers in this list";
      case 1:
        return "Teacher:\n" + teacherList.get(0);
      default:
        String s = "Teachers in list:\n";
        for (Teacher teacher : teacherList)
        {
          s += teacher + "\n";
        }
        return s;
    }

  }
}