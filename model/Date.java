package model;

public class Date
{
  private int day;
  private int month;
  private int year;

  public Date(int day, int month, int year)
  {
    if (year >= 2021)
    {
      this.year = year;
    }

    if (month > 0 && month <= 12)
    {
      this.month = month;
    }

    if ((day > 0) && (day <= 31))
    {
      if (day == 29 && month == 2 && isLeapYear())
      {
        this.day = day;
      }
      else if (day == 28 && month == 2)
      {
        this.day = day;
      }
      else if (day == 31)
      {
        if ((month == 2) || (month == 4) || (month == 6) || (month == 9) || (
            month == 11))
        {
          this.day = day;
        }
      }
      else
      {
        this.day = day;
      }
    }
    else
    {
      throw new IllegalArgumentException("Please enter a valid day");
    }
  }

  public int getDay()
  {
    return day;
  }

  public int getMonth()
  {
    return month;
  }

  public boolean isLeapYear()
  {
    return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
  }

  public int getYear()
  {
    return year;
  }


  public Date copy()
  {
    Date other = new Date(day, month, year);
    return other;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Date))
    {
      return false;
    }
    Date other = (Date)obj;
    return day == other.day && month == other.month && year == other.year;
  }
  public String toString()
  {
    return String.format("%04d-%02d-%02d", year, month, day);
    /*format "%02d:%02d:%02d" means that each value takes 2 "spaces"
      (0 is inserted first if number is only one cipher, AND separates numbers with colon) */
  }

  public boolean isBefore(Date date)
  {
    return (this.year < date.year || (this.year == date.year &&
        (this.month < date.month || (this.month == date.month &&
            (this.day < date.day)))));
  }

}
