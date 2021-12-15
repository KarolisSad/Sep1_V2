package view;

import javafx.collections.*;
import mediator.*;
import model.*;

public class TeacherViewListModel
{
  private ObservableList<TeacherViewModel> list;
  private ScheduleModel model;

  public TeacherViewListModel(ScheduleModel model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    update();
  }

  public ObservableList<TeacherViewModel> getList()
  {
    return list;
  }

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getTeacherSize(); i++)
    {
      list.add(new TeacherViewModel(model.getTeacher(i)));
    }
  }

  public void add(Teacher teacher)
  {
    list.add(new TeacherViewModel(teacher));
  }

  public void remove(Teacher teacher)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getNameProperty().get().equals(teacher.getName())&&list.get(i).getTeacherIDProperty().get().equals(teacher.getTeacherId()))
      {
        list.remove(i);
        break;
      }
    }
  }
}
