package model;

/**
 * A class representing Student ID, which is used as an identifier for students.
 *
 * @author Christian Hougaard Pedersen, Karolis and Justina
 * @Version 1.0 - December 2021
 */
public class StudentID implements ViaID
  {
    private int studentId;

    /**
     * One-argument constructor.
     * Takes integer Student ID given as argument.
     * If Student ID given in arguments is less than 1, an exception will be thrown.
     *
     * @see Student for uses.
     *
     * @param studentId
     *            The Student ID
     */
    public StudentID(int studentId)
    {
      if (studentId > 0)
      {
        this.studentId = studentId;
      }

      else
      {
        throw new IllegalArgumentException("Student ID should not be less than 1");
      }
    }

    /**
     * A method returning the Student ID as a String for use in constructing Student.
     * Since the instance variable taken as an argument in the constructor is an integer, it is converted to String, and returned.
     *
     * @return a String containing the Student ID.
     */
    public String getIdAsString()
    {
      return ""+studentId;
    }


    /**
     * A Copy method.
     * Since Student ID implements the VIA ID interface,
     * which has composition-relationships with the Student class
     * this method is needed to take a copy of the VIA-ID when you return it.
     *
     * @return a copy of the Student ID the method is used on.
     */
    public StudentID copy()
    {
      StudentID other = new StudentID(studentId);
      return other;
    }


  }