package view;

import javafx.beans.property.*;
import mediator.*;
import model.*;
import javax.swing.text.View;

public class TeacherViewModel
{
  private StringProperty nameProperty;
  private StringProperty teacherIDProperty;

  public TeacherViewModel(Teacher teacher)
  {
    nameProperty = new SimpleStringProperty(teacher.getName());
    teacherIDProperty = new SimpleStringProperty(teacher.getTeacherId());
  }

  public StringProperty getNameProperty()
  { return nameProperty; }

  public StringProperty getTeacherIDProperty()
  { return teacherIDProperty; }


}
