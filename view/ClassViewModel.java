package view;

import javafx.beans.property.*;
import mediator.*;
import model.*;
import model.Class;

import javax.swing.text.View;

public class ClassViewModel
{
  private StringProperty classNameProperty;
  private IntegerProperty semesterProperty;

  public ClassViewModel(Class myClass)
  {
    classNameProperty = new SimpleStringProperty(myClass.getClassID());
    semesterProperty = new SimpleIntegerProperty(myClass.getSemester());
  }

  public StringProperty getClassNameProperty()
  { return classNameProperty; }

  public IntegerProperty getSemesterProperty()
  { return semesterProperty; }


}
