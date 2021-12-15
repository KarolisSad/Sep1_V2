package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class AddClassViewController
{
  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  @FXML private TextField semester;
  @FXML private TextField name;
  @FXML private Label error;


  public AddClassViewController()
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
    error.setText("");
    semester.setText("");
    name.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  public void backButton()
  {
    viewHandler.openView("class");
  }

  @FXML private void addClass()
  {
    try
    {

    model.addClass(name.getText(), Integer.parseInt(semester.getText()));
    model.getClassFirst();
    model.sortStudentsInClasses();
      error.setText("Successfully added!");
    }
    catch (IllegalArgumentException e)
    {
      error.setText(e.getMessage());
    }
  }

}
