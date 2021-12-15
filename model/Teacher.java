package model;

public class Teacher
{
  private String name;
  private TeacherID teacherID;

  public Teacher(String name, TeacherID teacherID)
  {
    if (name == null)
    {
      throw new IllegalArgumentException("Name field can't be empty.");
    }
    else {this.name = name;}

    if (teacherID == null)
    {
      throw new IllegalArgumentException("Teacher must have ID");
    }
    else {this.teacherID = teacherID.copy();}

  }

  public String getTeacherId()
  {
    return teacherID.getIdAsString();
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Teacher))
    {return false;}
    Teacher other = (Teacher) obj;
    return name.equals(other.name) && getTeacherId().equals(other.getTeacherId());
  }

  public String getName()
  {
    return name;
  }


  public String toString()
  {
    return "Name: " + name + ", Teacher ID: " + getTeacherId();
  }
}
