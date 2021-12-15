package mediator;

import model.*;

public interface ScheduleModel
{
  public void addClass(String classID, int semester);

  public void getClassFirst();

  public void addStudent(String name, int studentID, String classID,
      int semester);

  public void addTeacher(String name, String teacherID);

  public void addCourse(String name, int ECTS, String mainClass,
      String firstTeacherID);

  public void addSession(String courseName, int sessionLength, int dateDay,
      int dateMonth, int dateYear, int timeHour, int timeMinute,
      String roomNumber);

  public RoomList getAvailableRooms(DateTime startTime, DateTime endTime);

  public void addRoom(int capacity, String roomNumber);

  public int studentListSize();

  public Student getStudent(int index);

  public int sessionListSize();

  public Session getSession(int index);

  public model.Class getClass(int index);

  public int getClassListSize();

  public Teacher getTeacher(int index);

  public int getTeacherSize();

  public int getCourseSize();

  public Course getCourse(int index);

  public Room getRoom(int index);

  public int getRoomListSize();

  public void sortStudentsInClasses();

  public void removeStudent(Student student);

  public void removeStudentFromVIA(String studentID);

  public void removeCourse(Course course);

  public void removeCourseBy(String courseName, int ects, String className, String teacherId);

  public void removeTeacher(Teacher teacher);

  public void removeSessionBy(String roomName, int month, int day, int hour, int minute);

  // newsly added

  public void addStudentToCourse(String studentID, String courseName);

  public void removeStudentFromCourse(String courseName, String studentID);

  public void exportAll();


}
