package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class AddCourseViewController
{
  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  @FXML private TextField courseName;
  @FXML private TextField ects;
  @FXML private TextField myClass;
  @FXML private TextField teacher;
  @FXML Label error;



  public AddCourseViewController()
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
    courseName.setText("");
    ects.setText("");
    myClass.setText("");
    teacher.setText("");
    courseName.setPromptText("SDJ");
    ects.setPromptText("5");
    myClass.setPromptText("1X");
    teacher.setPromptText("RIN");

  }

  public Region getRoot()
  {
    return root;
  }

  @FXML public void backButton()
  {
    viewHandler.openView("courseView");
  }

  @FXML private void submit()
  {
    // class get class . get resource relative pass to a class.


    boolean classFound = false;
    for (int i = 0; i< model.getClassListSize();i++)
    {
      if (myClass.getText().equals(model.getClass(i).getClassName()));
      {
        classFound = true;
      }
    }
    if (!(classFound))
    {
      error.setText("Class doesn't exist.");
      throw new IllegalArgumentException("Class doesn't exist.");
    }
    try
    {
    // teacher

    model.addCourse(courseName.getText(),Integer.parseInt(ects.getText()),myClass.getText(),teacher.getText());
      error.setText("Successfully added!");

}
catch (IllegalArgumentException e)
{
  error.setText(e.getMessage());
}
    model.exportAll();
  }




}
