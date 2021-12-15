package mediator;

import model.*;
import mediator.ScheduleFile;
import model.Class;
import model.Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ScheduleModelManager implements ScheduleModel
{
  private RoomList roomList;
  private SessionList sessionList;
  private CourseList courseList;
  private TeacherList teacherList;
  private StudentList studentList;
  private ClassList classList;

  public ScheduleModelManager()
  {
    this.roomList = new RoomList();
    this.sessionList = new SessionList();
    this.courseList = new CourseList();
    this.teacherList = new TeacherList();
    this.studentList = new StudentList();
    this.classList = new ClassList();
    // imports
    importingClasses();
    importingStudentList();
    sortStudentsInClasses();
    roomImports();
    teacherImports();
    courseImports();
    importingSessionList();


  }

  public void addRoom(int capacity, String roomNumber)
  {
    for (int i = 0; i < roomList.getNumberOfRooms(); i++)
    {
      if (roomList.getRoom(i).getRoomNumber().equals(roomNumber))
      {
        throw new IllegalArgumentException(
            "Room with same name already added: " + roomList.getRoom(i));
      }
    }

    roomList.addRoom(new Room(capacity, roomNumber));
  }

  public model.Class getClassByID(String className)
  {
    for (int i = 0; i < classList.size(); i++)
    {
      if (classList.getClassByIndex(i).getClassName().equals(className))
      {
        return classList.getClassByIndex(i);
      }
    }
    throw new IllegalArgumentException("Class not found.");
  }

  public Teacher getTeacherByID(String teacherID)
  {
    for (int i = 0; i < teacherList.size(); i++)
    {
      if (teacherList.getTeacher(i).getTeacherId().equals(teacherID))
      {
        return teacherList.getTeacher(i);
      }
    }
    throw new IllegalArgumentException("Teacher not found");
  }

  public void addCourse(String name, int ECTS, String mainClass,
      String firstTeacherID)
  {
    model.Class classToAdd = getClassByID(mainClass);
    Teacher teacherToAdd = getTeacherByID(firstTeacherID);


    for (int i = 0; i < courseList.getNumberOfCourses(); i++)
    {
      if (courseList.getCourse(i).getCourseName()
          .equals(name + classToAdd.getClassID()))
      {
        throw new IllegalArgumentException(
            "Course with same name already added.");
      }
    }
    courseList.addCourse(new Course(name, ECTS, classToAdd, teacherToAdd));

  }

  public void addTeacher(String name, String teacherID)
  {
    for (int i = 0; i < teacherList.size(); i++)
    {
      if (teacherList.getTeacher(i).getTeacherId().equals(teacherID))
      {
        throw new IllegalArgumentException(
            "Teacher with same ID already added: " + teacherList.getTeacher(i));
      }
    }

    teacherList.addTeacher(new Teacher(name, new TeacherID(teacherID)));
  }

  public void addStudent(String name, int studentID, String classID,
      int semester)
  {
    for (int i = 0; i < studentList.size(); i++)
    {
      if (studentList.getStudent(i).getStudentId().equals("" + studentID))
      {
        throw new IllegalArgumentException(
            "Student with same ID already added: " + studentList.getStudent(i));
      }
    }
    studentList.addStudent(
        new Student(name, new StudentID(studentID), classID, semester));
  }

  public void addSession(String courseName, int sessionLength, int dateDay,
      int dateMonth, int dateYear, int timeHour, int timeMinute,
      String roomNumber)
  {
    DateTime sessionStartTime = new DateTime(
        new Date(dateDay, dateMonth, dateYear), new Time(timeHour, timeMinute));
    DateTime sessionEndTime = new DateTime(
        new Date(dateDay, dateMonth, dateYear),
        new Time(timeHour + sessionLength, timeMinute));

    if (isRoomAvailable(roomList.getRoomByRoomNumber(roomNumber),
        sessionStartTime, sessionEndTime) && isMainClassStudentsAvailable(
        courseList.getCourseByName(courseName).getMainClass(), sessionStartTime,
        sessionEndTime) && isOtherCourseStudentsAvailable(
        courseList.getCourseByName(courseName), sessionStartTime,
        sessionEndTime) && isTeacherAvailable(
        courseList.getCourseByName(courseName), sessionStartTime,
        sessionEndTime))
    {
      sessionList.addSession(
          new Session(courseList.getCourseByName(courseName), sessionLength,
              sessionStartTime, roomList.getRoomByRoomNumber(roomNumber)));
    }
  }

  public boolean isRoomAvailable(Room room, DateTime startTime,
      DateTime endTime)
  {
    for (int i = 0; i < sessionList.getNumberOfSessions(); i++)
    {
      if (sessionList.getSession(i).getRoom().getRoomNumber()
          .equals(room.getRoomNumber()) && sessionList.getSession(i)
          .getStartDateTime()
          .getDate() // if room is the same AND date is the same
          .equals(startTime.getDate()))
      {
        if (startTime.getTime()
            .isBefore(sessionList.getSession(i).getEndDateTime().getTime())
            // if starttime of new session is before endTime of any session is list
            && !(endTime.getTime()
            .isBefore(sessionList.getSession(i).getStartDateTime().getTime())))
        //if endtime is NOT before starttime of any session in list
        {
          throw new IllegalArgumentException(
              "Room already in use for selected time. Room used by: "
                  + sessionList.getSession(i));
        }
      }
    }
    return true;
  }

  public boolean isMainClassStudentsAvailable(model.Class mainClass,
      DateTime startTime, DateTime endTime)
  {
    for (int i = 0; i < sessionList.getNumberOfSessions(); i++)
    {
      if (sessionList.getSession(i).getCourse().getMainClass().equals(mainClass)
          && sessionList.getSession(i).getStartDateTime().getDate()
          .equals(startTime.getDate()))
      {
        if (startTime.getTime()
            .isBefore(sessionList.getSession(i).getEndDateTime().getTime())
            && !(endTime.getTime()
            .isBefore(sessionList.getSession(i).getStartDateTime().getTime())))
        {
          throw new IllegalArgumentException(
              "Main Class students not available for sessions. Cause: "
                  + sessionList.getSession(i));
        }
      }
    }
    return true;
  }

  public boolean isTeacherAvailable(Course course, DateTime startTime,
      DateTime endTime)
  {
    for (int i = 0; i < course.getCourseTeacherList().size(); i++)
    {
      for (int j = 0; j < sessionList.getNumberOfSessions(); j++)
        if (sessionList.getSession(j).getCourse().getCourseTeacherList().contains(course.getCourseTeacherList().getTeacher(i)) && startTime.getDate()
            .equals(sessionList.getSession(j).getStartDateTime().getDate()))
        {
          if (startTime.getTime()
              .isBefore(sessionList.getSession(j).getEndDateTime().getTime())
              && !(endTime.getTime().isBefore(
              sessionList.getSession(j).getStartDateTime().getTime())))
          {
            throw new IllegalArgumentException(
                "Teacher " + course.getCourseTeacherList().getTeacher(i)
                    + " not available due to: " + sessionList.getSession(j));
          }
        }
    }
    return true;
  }

  public boolean isOtherCourseStudentsAvailable(Course course,
      DateTime startTime, DateTime endTime)
  {
    StudentList studentsNotInMainClass = new StudentList();
    if (course.getCourseStudentList()
        .equals(course.getMainClass().cStudentList))
    {
      return true;
    }
    else
    {
      for (int i = 0; i < course.getCourseSize(); i++)
      {
        for (int j = 0; j < course.getMainClass().getNumberOfStudents(); j++)
        {
          if (!(course.getCourseStudentList().getStudent(i)
              .equals(course.getMainClass().getStudentList().getStudent(j))))
          {
            studentsNotInMainClass.addStudent(
                course.getCourseStudentList().getStudent(i));
          }
        }
      }
      for (int i = 0; i < studentsNotInMainClass.size(); i++)
      {
        for (int j = 0; j < classList.size(); j++)
        {
          if (studentsNotInMainClass.getStudent(i).getClassName()
              .equals(classList.getClassByIndex(j).getClassName()))
          {
            if (!(isMainClassStudentsAvailable(classList.getClassByIndex(j),
                startTime, endTime)))
            {
              throw new IllegalArgumentException("otherStudentError");
            }
          }
        }
      }
    }
    return true;
  }

  public void removeStudentFromCourse(String courseName, String studentID)
  {
    int l = 0;
    for (int i = 0; i < courseList.getNumberOfCourses(); i++)
    {
      if (courseList.getCourse(i).getCourseName().equals(courseName))
      {
        for (int j = 0; j < courseList.getCourse(i).getCourseSize(); j++)
        {
          if (courseList.getCourse(i).getCourseStudentList().getStudent(j)
              .getStudentId().equals(studentID))
          {
            courseList.getCourse(i).getCourseStudentList()
                .removeStudentByIndex(j);
            l = 1;
            break;
          }
        }
      }
    }
      if (l == 0)
      {
        throw new IllegalArgumentException("Student couldn't be found!");
      }
  }

  public void removeStudentFromVIA(String studentID)
  {
    for (int i = 0; i < studentList.size(); i++)
    {
      if (studentID.equals(studentList.getStudent(i).getStudentId()))
      {
        studentList.removeStudent(studentList.getStudent(i));
      }

    }

    for (int i = 0; i < courseList.getNumberOfCourses(); i++)
    {
      for (int j = 0; j < courseList.getCourse(i).getCourseSize(); j++)
      {
        if (courseList.getCourse(i).getCourseStudentList().getStudent(j)
            .getStudentId().equals(studentID))
        {
          courseList.getCourse(i).removeStudentFromCourse(
              courseList.getCourse(i).getCourseStudentList().getStudent(j));
        }
      }
    }
  }

  public void addClass(String classID, int semester)
  {
    String classNameChecker = semester + classID;
    for (int i = 0; i < classList.size(); i++)
    {
      if (classList.getClassByIndex(i).getClassName().equals(classNameChecker))
      {
        throw new IllegalArgumentException(
            "Class with same ID already added: " + classList.getClassByIndex(
                i));
      }
    }
    classList.addClass(new Class(classID, semester));
  }

  public void sortStudentsInClasses()
  {
    for (int i = 0; i < studentList.size(); i++)
    {
      for (int j = 0; j < classList.size(); j++)
      {
        if (studentList.getStudent(i).getClassName()
            .equals(classList.getClassByIndex(j).getClassName())&&// don't add student if he's already in a list (class)
            !(classList.getClassByIndex(j).contains(studentList.getStudent(i))))
        {
          classList.getClassByIndex(j)
              .addStudentToClass(studentList.getStudent(i));
        }
      }
    }

  }


  public RoomList getRoomsList()
  {
    return roomList;
  }

  public StudentList getStudentList()
  {
    return studentList;
  }

  public ClassList getClassList()
  {
    return classList;
  }

  public TeacherList getTeacherList()
  {
    return teacherList;
  }

  public CourseList getCourseList()
  {
    return courseList;
  }

  public SessionList getSessionList()
  {
    return sessionList;
  }

  public RoomList getAvailableRooms(DateTime startTime, DateTime endTime)
  {
    RoomList availableRooms = new RoomList();
    for (int i = 0; i < roomList.getNumberOfRooms(); i++)
    {
      if (isRoomAvailable(roomList.getRoom(i), startTime, endTime))
      {
        availableRooms.addRoom(roomList.getRoom(i));
      }
    }

    return availableRooms;

  }

  // For Testing

  public void getClassFirst()
  {
    System.out.println(classList.size() +" My print");
  }

  public int studentListSize(){
    return studentList.size();
  }

  public Student getStudent(int index)
  {
    return studentList.getStudent(index);

  }

  public int sessionListSize()
  {
    return sessionList.getNumberOfSessions();
  }

  public Session getSession(int index)
  {
    return sessionList.getSession(index);
  }

  public void importingStudentList()
  {
    StudentList newList = ScheduleFile.importStudents();
    for (int i = 0; i < newList.size(); i++)
    {
      studentList.addStudent(newList.getStudent(i));
    }
  }

  public void importingSessionList()
  {
    SessionList newList = ScheduleFile.importSessions();
    for (int i = 0; i < newList.getNumberOfSessions(); i++)
    {
      sessionList.addSession(newList.getSession(i));
    }
  }

  public model.Class getClass(int index)
  {
    return classList.getClassByIndex(index);
  }

  public int getClassListSize()
  {
    return classList.size();
  }

  public void importingClasses()
  {
    ClassList newList = ScheduleFile.importClasses();
    for (int i = 0; i < newList.size(); i++)
    {
      classList.addClass(newList.getClassByIndex(i));
    }
  }

  public Teacher getTeacher(int index)
  {
    return teacherList.getTeacher(index);
  }

  public int getTeacherSize()
  {
    return teacherList.size();
  }

  public void teacherImports()
  {
    TeacherList newList = ScheduleFile.importTeachers();
    for (int i = 0; i < newList.size(); i++)
    {
      teacherList.addTeacher(newList.getTeacher(i));
    }
  }

  public int getCourseSize()
  {
   return courseList.getNumberOfCourses();
  }

  public Course getCourse(int index)
  {
    return courseList.getCourse(index);
  }

  public Room getRoom(int index)
  {
    return roomList.getRoom(index);
  }

  public int getRoomListSize()
  {
    return roomList.getNumberOfRooms();
  }

  public void roomImports()
  {
    RoomList newList = ScheduleFile.importRooms();
    for (int i = 0; i < newList.getNumberOfRooms(); i++)
    {
      roomList.addRoom(newList.getRoom(i));
    }
  }

  public void courseImports()
  {
    boolean foundTeacher = false;
    boolean classFound = false;
    CourseList newList = ScheduleFile.importCourses();
    CourseList notAbleToAddCourses = new CourseList();
    for (int i = 0; i < newList.getNumberOfCourses(); i++)
    {
      int indexTeacher=0;
      int indexClass=0;
      for (int j = 0; j<teacherList.size(); j++)
      {
        if (newList.getCourse(i).getCourseTeacherList().getTeacher(0).getTeacherId().equals(teacherList.getTeacher(j).getTeacherId()))
        {
          foundTeacher = true;
          indexTeacher = j;

        }
      }
      for (int z = 0; z< classList.size();z++)
      {
        if (newList.getCourse(i).getMainClass().getSemester() == classList.getClassByIndex(z).getSemester() &&
        newList.getCourse(i).getMainClass().getClassID().equals(classList.getClassByIndex(z).getClassID()))
        {
          classFound = true;
          indexClass = z;

        }
      }



      if (foundTeacher && classFound)
      {
        courseList.addCourse(new Course(newList.getCourse(i).getCourseName(),newList.getCourse(i).getEctsPoints(),classList.getClassByIndex(indexClass),teacherList.getTeacher(indexTeacher)));
        System.out.println("List of students in course: " +courseList.getCourse(courseList.getNumberOfCourses()-1).getCourseStudentList().size());
      //  courseList.addCourse(newList.getCourse(i));
        foundTeacher = false;
        classFound =false;
      }
      else {
        notAbleToAddCourses.addCourse(newList.getCourse(i));
      }
    }
    //  Gives list for courses that couldn't be added
    System.out.println("Courses that couldn't be added:");
    for (int i = 0; i< notAbleToAddCourses.getNumberOfCourses();i++)
    {
      System.out.println(notAbleToAddCourses.getCourse(i).getCourseName()+notAbleToAddCourses.getCourse(i).getMainClass().getSemester()+notAbleToAddCourses.getCourse(i).getMainClass().getClassID());
    }
  }

  public void removeStudent(Student student)
  {
    studentList.removeStudent(student);
  }

  public void removeCourse(Course course)
  {
    courseList.removeCourse(course);
  }

  public void removeCourseBy(String courseName, int ects, String className, String teacherId)
  {
    for (int i = 0; i<courseList.getNumberOfCourses(); i ++)
    {
      if (courseList.getCourse(i).getCourseName().equals(courseName) && courseList.getCourse(i).getEctsPoints()== ects&& courseList.getCourse(i).getMainClass().getClassName().equals(className)&& courseList.getCourse(i).getCourseTeacherList().getTeacher(0).getTeacherId().equals(teacherId))
      {
        courseList.removeCourseByIndex(i);
      }
    }
  }

  public void removeTeacher(Teacher teacher)
  {
    teacherList.removeTeacher(teacher);
  }

  public void removeSessionBy(String roomName, int month, int day, int hour, int minute)
  {
    for (int i = 0; i<sessionList.getNumberOfSessions(); i++)
    {
      if (sessionList.getSession(i).getRoom().getRoomNumber().equals(roomName)&& sessionList.getSession(i).getStartDateTime().getDate().getMonth()==month&&
      sessionList.getSession(i).getStartDateTime().getDate().getDay()==day&&
      sessionList.getSession(i).getStartDateTime().getTime().getHour()==hour&&
      sessionList.getSession(i).getStartDateTime().getTime().getMinute()==minute)
      {
        sessionList.removeSessionByIndex(i);
      }
    }
  }


  public void addStudentToCourse(String studentID, String courseName)
  {
    int nr = 0;
    for (int i = 0; i<studentList.size();i++)
    {
      if (studentList.getStudent(i).getStudentId().equals(studentID))
      {
       for (int j = 0; j < courseList.getNumberOfCourses() ;j++)
       {
         if (courseList.getCourse(j).getCourseName().equals(courseName) && courseList.getCourse(j).getCourseStudentList().containsById(Integer.parseInt(studentID)))
         {
           courseList.getCourse(j).addStudentToCourse(studentList.getStudent(i));
           nr = 1;
           break;
         }
       }
      }
    }
    if (nr == 0)
    {
      throw new IllegalArgumentException("Student is already in this course!");
    }

  }

  public void exportAll()
  {
    exportClass();
    exportStudents();
    exportRooms();
    exportTeachers();
    exportCourses();
    exportSessions();
  }

  public void exportClass()
  {
    File file = new File("Imports/classes.txt");
    try
    {
      PrintWriter out = new PrintWriter(file);
      for (int i = 0 ; i < classList.size(); i++)
      {
        out.print(classList.getClassByIndex(i).getClassID());
        out.print(",");
        out.print(classList.getClassByIndex(i).getSemester());
        out.println();
      }
      out.flush();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      System.out.println(file.getAbsolutePath());
    }
  }

  public void exportStudents()
  {
    File file = new File("Imports/student.txt");
    try
    {
      PrintWriter out = new PrintWriter(file);
      for (int i = 0 ; i < studentList.size(); i++)
      {
        out.print(studentList.getStudent(i).getSemester());
        out.print(",");
        out.print(studentList.getStudent(i).getClassID());
        out.print(",");
        out.print(studentList.getStudent(i).getName());
        out.print(",");
        out.print(studentList.getStudent(i).getStudentId());
        out.println();
      }
      out.flush();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      System.out.println(file.getAbsolutePath());
    }
  }

  public void exportRooms()
  {
    File file = new File("Imports/rooms.txt");
    try
    {
      PrintWriter out = new PrintWriter(file);
      for (int i = 0 ; i < roomList.getNumberOfRooms(); i++)
      {
        out.print(roomList.getRoom(i).getRoomNumber());
        out.print(",");
        out.print(roomList.getRoom(i).getCapacity());
        out.println();
      }
      out.flush();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      System.out.println(file.getAbsolutePath());
    }
  }


  public void exportTeachers()
  {
    File file = new File("Imports/teachers.txt");
    try
    {
      PrintWriter out = new PrintWriter(file);
      for (int i = 0 ; i < teacherList.size(); i++)
      {
        out.print(teacherList.getTeacher(i).getName());
        out.print(",");
        out.print(teacherList.getTeacher(i).getTeacherId());
        out.println();
      }
      out.flush();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      System.out.println(file.getAbsolutePath());
    }
  }


  public void exportCourses()
  {
    File file = new File("Imports/courses.txt");
    try
    {
      PrintWriter out = new PrintWriter(file);
      for (int i = 0 ; i < courseList.getNumberOfCourses(); i++)
      {
        out.print(courseList.getCourse(i).getMainClass().getSemester());
        out.print(",");
        out.print(courseList.getCourse(i).getMainClass().getClassID());
        out.print(",");
        out.print(courseList.getCourse(i).getCourseName());
        out.print(",");
        out.print(courseList.getCourse(i).getCourseTeacherList().getTeacher(0).getName());
        out.print(",");
        out.print(courseList.getCourse(i).getEctsPoints());
        out.println();
      }
      out.flush();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      System.out.println(file.getAbsolutePath());
    }
  }

  public void exportSessions()
  {
    File file = new File("Imports/sessions.txt");
    try
    {
      PrintWriter out = new PrintWriter(file);
      for (int i = 0 ; i < sessionList.getNumberOfSessions(); i++)
      {
        out.print(sessionList.getSession(i).getCourse().getCourseName());
        out.print(",");
        out.print(sessionList.getSession(i).getSessionLength());
        out.print(",");
        out.print(sessionList.getSession(i).getStartDateTime().getDate().getDay());
        out.print(",");
        out.print(sessionList.getSession(i).getStartDateTime().getDate().getMonth());
        out.print(",");
        out.print(sessionList.getSession(i).getStartDateTime().getDate().getYear());
        out.print(",");
        out.print(sessionList.getSession(i).getStartDateTime().getTime().getHour());
        out.print(",");
        out.print(sessionList.getSession(i).getStartDateTime().getTime().getMinute());
        out.print(",");
        out.print(sessionList.getSession(i).getRoom().getRoomNumber());
        out.print(",");
        out.print(sessionList.getSession(i).getRoom().getCapacity());
        out.print(",");
        out.print(sessionList.getSession(i).getCourse().getEctsPoints());
        out.print(",");
        out.print(sessionList.getSession(i).getCourse().getMainClass().getClassID());
        out.print(",");
        out.print(sessionList.getSession(i).getCourse().getMainClass().getSemester());
        out.print(",");
        out.print(sessionList.getSession(i).getCourse().getCourseTeacherList().getTeacher(0).getName());
        out.print(",");
        out.print(sessionList.getSession(i).getCourse().getCourseTeacherList().getTeacher(0).getTeacherId());
        out.println();


      }
      out.flush();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    finally
    {
      System.out.println(file.getAbsolutePath());
    }
  }








}
