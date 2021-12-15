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

  @FXML private void classButton()
  {
    viewHandler.openView("class");
  }

  @FXML private void studentButton()
  {
    viewHandler.openView("studentView");
  }

  @FXML private void teacherButton()
  {
    viewHandler.openView("teacherView");
  }

  @FXML private void courseButton()
  {
    viewHandler.openView("courseView");
  }

  @FXML private void sessionButton()
  {
    viewHandler.openView("sessionView");
  }

  @FXML private void room()
  {
    viewHandler.openView("roomView");
  }

  @FXML private void export()
  {
    model.exportAll();

  };

}
