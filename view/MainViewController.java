package view;

import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class MainViewController
{
  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;


  public MainViewController()
  {

  }

  public void init(ViewHandler viewHandler, Region root, ScheduleModel model)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    reset();
  }

  public void reset()
  {

  }

  public Region getRoot()
  {
    return root;
  }

  @FXML void classButton()
  {
    viewHandler.openView("class");
  }

  @FXML void studentButton()
  {
    viewHandler.openView("studentView");
  }

  @FXML void teacherButton()
  {
    viewHandler.openView("teacherView");
  }

  @FXML void courseButton()
  {
    viewHandler.openView("courseView");
  }

  @FXML void sessionButton()
  {
    viewHandler.openView("sessionView");
  }

  @FXML void room()
  {
    viewHandler.openView("roomView");
  }

}
