package view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class AddRoomViewController
{
  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  @FXML private TextField room;
  @FXML private TextField capacity;
  @FXML private Label error;


  public AddRoomViewController()
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
    room.setText("");
    capacity.setText("");
    error.setText("");

  }

  public Region getRoot()
  {
    return root;
  }

  public void backButton()
  {
    viewHandler.openView("main");
  }

  @FXML private void addRoom()
  {
    try
    {
      model.addRoom(Integer.parseInt(capacity.getText()), room.getText());
      error.setText("Successfully added!");
    }
   catch (IllegalArgumentException e)
   {
     error.setText(e.getMessage());
   }

  }

}
