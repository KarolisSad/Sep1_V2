package view;

import javafx.beans.property.*;
import mediator.*;
import model.*;
import model.Class;

import javax.swing.text.View;

public class SessionViewModel
{
  private StringProperty courseProperty;
  private IntegerProperty sessionLengthProperty;
  private StringProperty startTimeProperty;
  private StringProperty roomProperty;
  private StringProperty roomNameProperty;
  private IntegerProperty roomCapacityProperty;
  private IntegerProperty yearProperty;
  private IntegerProperty monthProperty;
  private IntegerProperty dayProperty;
  private IntegerProperty hourProperty;
  private IntegerProperty minuteProperty;

  private StringProperty courseNameProperty;
  private IntegerProperty ectsProperty;
  private StringProperty classIdProperty;
  private IntegerProperty semesterProperty;
  private StringProperty teacherNameProperty;
  private StringProperty teacherIdProperty;

  public SessionViewModel(Session session)
  {
    courseProperty = new SimpleStringProperty(session.getCourse().getCourseName() + session.getCourse().getMainClass().getClassName());
    sessionLengthProperty = new SimpleIntegerProperty(session.getSessionLength());
    startTimeProperty = new SimpleStringProperty(session.getStartDateTime().getTime().toString());
    roomProperty = new SimpleStringProperty(session.getRoom().getRoomNumber());
    roomCapacityProperty = new SimpleIntegerProperty(session.getRoom().getCapacity());
    roomNameProperty = new SimpleStringProperty(session.getRoom().getRoomNumber());
    yearProperty = new SimpleIntegerProperty(session.getStartDateTime().getDate().getYear());
    monthProperty = new SimpleIntegerProperty(session.getStartDateTime().getDate().getMonth());
    dayProperty = new SimpleIntegerProperty(session.getStartDateTime().getDate().getDay());
    hourProperty = new SimpleIntegerProperty(session.getStartDateTime().getTime().getHour());
    minuteProperty = new SimpleIntegerProperty(session.getStartDateTime().getTime().getMinute());

    courseNameProperty = new SimpleStringProperty(session.getCourse().getCourseName());
    ectsProperty = new SimpleIntegerProperty(session.getCourse().getEctsPoints());
    classIdProperty = new SimpleStringProperty(session.getCourse().getMainClass().getClassID());
    semesterProperty = new SimpleIntegerProperty(session.getCourse().getMainClass().getSemester());
    teacherNameProperty = new SimpleStringProperty(session.getCourse().getCourseTeacherList().getTeacher(0).getName());
    teacherIdProperty = new SimpleStringProperty(session.getCourse().getCourseTeacherList().getTeacher(0).getTeacherId());




  }
  public StringProperty getCourseProperty()
  { return courseProperty; }

  public IntegerProperty getSessionLengthProperty()
  { return sessionLengthProperty; }

  public StringProperty getStartTimeProperty()
  { return startTimeProperty; }

  public StringProperty getRoomProperty()
  { return roomProperty; }

  public StringProperty getRoomNameProperty()
  {return roomNameProperty;}

  public IntegerProperty getRoomCapacityProperty()
  {return sessionLengthProperty;}

  public IntegerProperty getYearProperty()
  {return yearProperty;}

  public IntegerProperty getMonthProperty()
  {return monthProperty;}

  public IntegerProperty getDayProperty()
  {return dayProperty;}

  public IntegerProperty getHourProperty()
  {return hourProperty;}

  public IntegerProperty getMinuteProperty()
  {return minuteProperty;}


  public StringProperty getTeacherNameProperty()
  {return teacherNameProperty;}

  public IntegerProperty  getEctsProperty()
  {return ectsProperty;}

  public StringProperty getClassIdProperty()
  {return classIdProperty;}

  public IntegerProperty getSemesterProperty()
  {return semesterProperty;}

  public StringProperty getCourseNameProperty()
  {return getCourseProperty();}

  public StringProperty getTeacherIdProperty()
  {return teacherIdProperty;}

}
