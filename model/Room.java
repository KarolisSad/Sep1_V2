package model;

public class Room
{
  private int capacity;
  private String roomNumber;

  public Room(int capacity, String roomNumber)
  {
    this.capacity = capacity;
    this.roomNumber = roomNumber;
  }


  public int getCapacity()
  {
    return capacity;
  }

  public String getRoomNumber()
  {
    return roomNumber;
  }

  public Room copy()
  {
    Room other = new Room(capacity, roomNumber);
    return other;
  }


  public String toString()
  {
    return "Room Number: " + roomNumber + ", Capacity: " + capacity;
  }
}
