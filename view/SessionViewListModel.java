package view;

import javafx.collections.*;
import mediator.*;
import model.*;

public class SessionViewListModel
{
  private ObservableList<SessionViewModel> list;
  private ScheduleModel model;

  public SessionViewListModel(ScheduleModel model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    update();
  }

  public ObservableList<SessionViewModel> getList()
  {
    return list;
  }

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.sessionListSize(); i++)
    {
      list.add(new SessionViewModel(model.getSession(i)));
    }
  }

  public void add(Session session)
  {
    list.add(new SessionViewModel(session));
  }

  public void remove(Session session)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getHourProperty().get()==session.getStartDateTime().getTime().getHour() &&
      list.get(i).getRoomProperty().get().equals(session.getRoom().getRoomNumber())&&
      list.get(i).getDayProperty().get()==session.getStartDateTime().getDate().getDay())
      {
        list.remove(i);
        break;
      }
    }
  }
}
