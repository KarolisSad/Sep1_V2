package view;

import javafx.beans.property.*;
import mediator.*;
import model.*;
import model.Class;

import javax.swing.text.View;

public class RoomViewModel
{
  private StringProperty nameProperty;
  private IntegerProperty capacityProperty;

  public RoomViewModel(Room room)
  {
    nameProperty = new SimpleStringProperty(room.getRoomNumber());
    capacityProperty = new SimpleIntegerProperty(room.getCapacity());
  }

  public StringProperty getNameProperty()
  { return nameProperty; }

  public IntegerProperty getCapacityProperty()
  { return capacityProperty; }


}
