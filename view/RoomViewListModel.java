package view;

import javafx.collections.*;
import mediator.*;
import model.*;
import model.Class;

public class RoomViewListModel
{
  private ObservableList<RoomViewModel> list;
  private ScheduleModel model;

  public RoomViewListModel(ScheduleModel model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    update();
  }

  public ObservableList<RoomViewModel> getList()
  {
    return list;
  }

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getRoomListSize(); i++)
    {
      list.add(new RoomViewModel(model.getRoom(i)));
    }
  }

  public void add(Room room)
  {
    list.add(new RoomViewModel(room));
  }

  public void remove(Room room)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getNameProperty().equals(room.getRoomNumber()));
      {
        list.remove(i);
        break;
      }
    }
  }
}
