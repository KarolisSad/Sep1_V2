package model;

public class Session
{
  private Course course;
  private int sessionLength;
  private DateTime startTime;
  private DateTime endTime;
  private Room room;

  public Session(Course course, int sessionLength, DateTime startTime,
      Room room)
  {
    this.course = course;

    setSessionLength(sessionLength);
    setStartTime(startTime);
    setRoom(room);
  }

  public Course getCourse()
  {
    return course;
  }

  public int getSessionLength()
  {
    return sessionLength;
  }

  public DateTime getStartDateTime()
  {
    return startTime;
  }

  public DateTime getEndDateTime()
  {
    return endTime;
  }

  public Room getRoom()
  {
    return room.copy();
  }

  public void setRoom(Room room)
  {
    if (room == null)
    {
      throw new IllegalArgumentException("Room should not be null.");
    }

    else
    {
      if (room.getCapacity() >= course.getCourseSize())
      {
        this.room = room;
      }

      else
      {
        throw new IllegalArgumentException(
            "Room too small to hold current course");
      }
    }
  }

  public void setStartTime(DateTime newStartTime)
  {
    this.startTime = newStartTime.copy();
    this.endTime = new DateTime(startTime.getDate(),
        new Time(startTime.getTime().getHour() + sessionLength,
            startTime.getTime().getMinute()));
  }

  public void setSessionLength(int sessionLength)
  {
    if (sessionLength == 2 || sessionLength == 3 || sessionLength == 4)
    {
      this.sessionLength = sessionLength;
    }
    else
    {
      throw new IllegalArgumentException(
          "Length Error - length should be 2, 3 or 4");
    }
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Session))
    {
      return false;
    }
    Session other = (Session) obj;
    return course.equals(other.course) && sessionLength == other.sessionLength
        && startTime.equals(other.startTime) && room.equals(other.room);
  }

  public String toString()
  {
    return "Course: " + course.getCourseName() + ", Date: " + startTime.getDate() + ", Starting: "
        + startTime.getTime() + ", Ending: " + endTime.getTime() + ", Room: " + room;
  }
}

