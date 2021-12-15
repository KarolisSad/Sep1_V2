package view;

import javafx.beans.property.*;
import mediator.*;
import model.*;
import javax.swing.text.View;

public class CourseViewModel
{
  private StringProperty courseNameProperty;
  private IntegerProperty ectsProperty;
  private StringProperty mainClassProperty;
  private StringProperty teacherProperty;
  private IntegerProperty semesterProperty;
  private StringProperty teacherIdProperty;
  private StringProperty classIdProperty;
  private StringProperty classNameProperty;
  private StringProperty finalCourseNameProperty;

  public CourseViewModel(Course course)
  {
    courseNameProperty = new SimpleStringProperty(course.getCourseName());
    ectsProperty = new SimpleIntegerProperty(course.getEctsPoints());
    mainClassProperty = new SimpleStringProperty(course.getMainClass().getClassName());
    teacherProperty = new SimpleStringProperty(course.getCourseTeacherList().getTeacher(0).getName());
    semesterProperty = new SimpleIntegerProperty(course.getMainClass().getSemester());
    teacherIdProperty = new SimpleStringProperty(course.getCourseTeacherList().getTeacher(0).getTeacherId());
    classIdProperty = new SimpleStringProperty(course.getMainClass().getClassID());
    classNameProperty = new SimpleStringProperty(course.getMainClass().getClassName());
    finalCourseNameProperty = new SimpleStringProperty(course.getCourseName()+course.getMainClass().getClassName());

  }

  public StringProperty getCourseNameProperty()
  { return courseNameProperty; }

  public IntegerProperty getEctsProperty()
  { return ectsProperty; }

  public StringProperty getMainClassProperty()
  { return mainClassProperty; }

  public StringProperty getTeacherProperty()
  { return teacherProperty; }

  public IntegerProperty getSemesterProperty()
  { return semesterProperty; }

  public StringProperty getTeacherIdProperty()
  {return teacherIdProperty;}

  public StringProperty getClassIdProperty()
  {return classIdProperty;}

  public StringProperty getClassNameProperty()
  {return classNameProperty;}

  public StringProperty getFinalCourseNameProperty()
  { return finalCourseNameProperty; }
}
