package model;

import java.util.ArrayList;

public class RoomList
{
  private ArrayList<Room> roomList;

  public RoomList()
  {
    this.roomList = new ArrayList<>();
  }

  public void addRoom(Room room)
  {
    roomList.add(room);
  }

  public void removeRoom(int index)
  {
    roomList.remove(index);
  }

  public Room getRoom(int index)
  {
    if (roomList.size() >= index + 1)
    {
      return roomList.get(index);
    }
    else
    {
      throw new ArrayIndexOutOfBoundsException(
          "Index out of bounds for RoomList. Entered index: " + index
              + ", highest possible index: " + (getNumberOfRooms() - 1));
    }
  }

  public Room getRoomByRoomNumber(String roomNumber)
  {
    for (int i = 0; i < roomList.size(); i++)
    {
      if (roomList.get(i).getRoomNumber().equals(roomNumber))
      {
        return roomList.get(i);
      }
    }

    throw new IllegalArgumentException("Room with name: " + roomNumber + " not found.");
  }

  public int getNumberOfRooms()
  {
    return roomList.size();
  }

  public boolean contains(Room room)
  {
    return roomList.contains(room);
  }

  public String toString()
  {
    String s = "";
    for (Room i : roomList)
    {
      s += i + "\n";
    }
    return s;
  }
}
