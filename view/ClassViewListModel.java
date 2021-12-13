package view;

import javafx.collections.*;
import mediator.*;
import model.*;
import model.Class;

public class ClassViewListModel
{
  private ObservableList<ClassViewModel> list;
  private ScheduleModel model;

  public ClassViewListModel(ScheduleModel model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    update();
  }

  public ObservableList<ClassViewModel> getList()
  {
    return list;
  }

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getClassListSize(); i++)
    {
      list.add(new ClassViewModel(model.getClass(i)));
    }
  }

  public void add(Class myClass)
  {
    list.add(new ClassViewModel(myClass));
  }

  public void remove(Class myClass)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getClassNameProperty().equals(myClass.getClassID()) && list.get(i).getSemesterProperty().equals(myClass.getSemester()));
      {
        list.remove(i);
        break;
      }
    }
  }
}
