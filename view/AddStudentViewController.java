package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import javax.swing.text.Element;

public class AddStudentViewController
{
  @FXML private 

  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  @FXML private TextField name;
  @FXML private TextField id;
  @FXML private TextField semester;
  @FXML private TextField myClass;
  @FXML private Label error;


  public AddStudentViewController()
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
    name.setText("");
    id.setText("");
    semester.setText("");
    myClass.setText("");
    name.setPromptText("e.g. John");
    id.setPromptText("e.g 315225");
    semester.setPromptText("1");
    myClass.setPromptText("X");
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void backButton()
  {
    viewHandler.openView("studentView");
  }
  @FXML private void submitButton()
  {
    try
    {
      model.addStudent(name.getText(),Integer.parseInt(id.getText()), myClass.getText(), Integer.parseInt(semester.getText()));
      model.sortStudentsInClasses();
      error.setText("Successfully added!");
    }
    catch (IllegalArgumentException e)
    {
      error.setText(e.getMessage());
    }
    model.exportAll();

  }


}
