package model;

import java.util.ArrayList;

public class ClassList
{
  private ArrayList<Class> classList;

  public ClassList()
  {
    classList = new ArrayList<>();
  }

  public  void addClass(Class classToAdd)
  {
    classList.add(classToAdd);
  }

  public Class getClassByIndex(int index)
  {
    return classList.get(index);
  }

  public int size()
  {
    return classList.size();
  }

  public boolean contains(Class containsClass)
  {
    return classList.contains(containsClass);
  }

  public String toString()
  {
    String s = "Classes:\n";
    for (int i = 0; i < classList.size(); i++)
    {
      s += classList.get(i) + "\n";
    }
    return s;
  }
}
