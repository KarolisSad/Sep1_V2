package view;

import javafx.collections.*;
import mediator.*;
import model.*;

public class StudentViewListModel
{
  private ObservableList<StudentViewModel> list;
  private ScheduleModel model;

  public StudentViewListModel(ScheduleModel model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    update();
  }

  public ObservableList<StudentViewModel> getList()
  {
    return list;
  }

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.studentListSize(); i++)
    {
      list.add(new StudentViewModel(model.getStudent(i)));
    }
  }

  public void add(Student student)
  {
    list.add(new StudentViewModel(student));
  }

  public void remove(Student student)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getStudentIDProperty().get() == Integer.parseInt(
          student.getStudentId()))
      {
        list.remove(i);
        break;
      }
    }
  }
}
