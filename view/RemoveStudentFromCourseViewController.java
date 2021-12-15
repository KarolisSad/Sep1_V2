package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

import javax.swing.text.Element;

public class RemoveStudentFromCourseViewController
{
  @FXML private

  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  @FXML private TextField course;
  @FXML private TextField studentID;
  @FXML private Label error;


  public RemoveStudentFromCourseViewController()
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
    course.setPromptText("e.g SDJ1X");
    studentID.setPromptText("e.g 315225");

  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void backButton()
  {
    viewHandler.openView("courseView");
  }

  @FXML private void submitButton()
  {
    try
    {
      model.removeStudentFromCourse(course.getText(),studentID.getText());
      error.setText("Successfully removed!");
    }
    catch (IllegalArgumentException e)
    {
      error.setText(e.getMessage());
    }
    model.exportAll();
  }
}
