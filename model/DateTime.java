package model;

public class DateTime
{
  private Time time;
  private Date date;

  public DateTime(Date date, Time time)
  {
    this.time = time.copy();
    this.date = date.copy();
  }

  public Time getTime()
  {
    return time;
  }

  public Date getDate()
  {
    return date;
  }

  public String getDateTime()
  {
    return "dATE: " + date.getYear()+ "-" + date.getMonth() + "-" + date.getDay() + "tIME:" + time.getHour() + ":"+ time.getMinute();
  }

  public boolean isBefore(DateTime otherDateTime)
  {
    return getDate().isBefore(otherDateTime.getDate()) || getDate().equals(otherDateTime.getDate()) && getTime().isBefore(
        otherDateTime.getTime());
  }

  public DateTime copy()
  {
    DateTime other = new DateTime(date, time);
    return other;
  }
}
