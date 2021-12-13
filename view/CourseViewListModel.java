package view;

import javafx.collections.*;
import mediator.*;
import model.*;

public class CourseViewListModel
{
  private ObservableList<CourseViewModel> list;
  private ScheduleModel model;

  public CourseViewListModel(ScheduleModel model)
  {
    this.model = model;
    this.list = FXCollections.observableArrayList();
    update();
  }

  public ObservableList<CourseViewModel> getList()
  {
    return list;
  }

  public void update()
  {
    list.clear();
    for (int i = 0; i < model.getCourseSize(); i++)
    {
      list.add(new CourseViewModel(model.getCourse(i)));
    }
  }

  public void add(Course course)
  {
    list.add(new CourseViewModel(course));
  }

  public void remove(Course course)
  {
    for (int i = 0; i < list.size(); i++)
    {

      if (list.get(i).getCourseNameProperty().get().equals(course.getCourseName())&&list.get(i).getEctsProperty().get()==course.getEctsPoints()&&list.get(i).getTeacherIdProperty().get().equals(course.getCourseTeacherList().getTeacher(0).getTeacherId()))
      {
        list.remove(i);
        break;
      }
    }
  }
}
