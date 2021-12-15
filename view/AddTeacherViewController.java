package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class AddTeacherViewController
{
  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  @FXML private TextField idAdd;
  @FXML private TextField nameAdd;
  @FXML private Label error;


  public AddTeacherViewController()
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
    idAdd.setText("");
    nameAdd.setText("");

  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void backButton()
  {
    viewHandler.openView("teacherView");
  }

  @FXML private void addTeacher()
  {
    try
    {
      model.addTeacher(nameAdd.getText(),idAdd.getText());
      error.setText("Successfully added!");
    }
    catch (IllegalArgumentException e)
    {
      error.setText(e.getMessage());
    }
    model.exportAll();
  }




}
