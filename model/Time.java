package model;

public class Time
{
  private int hour;
  private int minute;

  public Time (int hour, int minute)
  {
    if ((hour > 7) && (hour < 20))
    {
      this.hour = hour;
    }
    else
    {
      throw new IllegalArgumentException(
          "Please enter an hour that is more than 8 and less then 20");
    }

    if(minute < 60 && minute >= 0)
    {
      if (hour == 8 && minute >= 20)
      {
        this.minute = minute;
      }
      else if (hour == 19 && minute <= 35)
      {
        this.minute = minute;
      }
      else
      {
        this.minute = minute;
      }
    }
    else
    {
      throw new IllegalArgumentException(
          "please enter a valid minute between [1-59]");
    }
  }

  public int getHour()
  {
    return hour;
  }

  public int getMinute()
  {
    return minute;
  }

  public boolean isBefore(Time time2)
  {
    return hour < time2.hour || hour == time2.hour && minute < time2.minute
        || hour == time2.hour && minute == time2.minute;
  }


  public Time copy()
  {
    Time other = new Time (hour, minute);
    return other;
  }

  public boolean equals (Object obj)
  {
    if (!(obj instanceof Time))
    {
      return false;
    }
    Time other = (Time)obj;
    return minute == other.minute && hour == other.hour;
  }

  public String toString()
  {
    return String.format("%02d:%02d", hour, minute);
    /*format "%02d:%02d:%02d" means that each value takes 2 "spaces"
      (0 is inserted first if number is only one cipher, AND separates numbers with colon) */
  }
}
