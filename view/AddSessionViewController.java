package view;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.Date;
import model.DateTime;
import model.RoomList;
import model.Time;

public class AddSessionViewController
{
  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  @FXML private TextField length;
  @FXML private DatePicker date;
  @FXML private TextField hour;
  @FXML private TextField minute;
  @FXML private ChoiceBox roomList;
  @FXML private TextField course;
  @FXML private Label error;

  public AddSessionViewController()
  {

  }

  public void init(ViewHandler viewHandler, Region root, ScheduleModel model)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    reset();
    course.setPromptText("Ex: SDJ1X");
    length.setPromptText("2");
    hour.setPromptText("8");
    minute.setPromptText("20");
  }

  public void reset()
  {
    error.setText("");
    length.setText("");
    hour.setText("");
    minute.setText("");
    course.setText("");



  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void check()
  {
    String line = String.valueOf(date.getValue());
    String lines[] = line.split("-");
    int year = Integer.parseInt(lines[0].trim());
    int month = Integer.parseInt(lines[1].trim());
    int day = Integer.parseInt(lines[2].trim());


    DateTime dateTime = new DateTime(new Date(day,month,year),new Time(Integer.parseInt(hour.getText()),Integer.parseInt(minute.getText())));
    DateTime endDate = new DateTime(new Date(day,month,year),new Time((Integer.parseInt(hour.getText())) + (Integer.parseInt(length.getText())),Integer.parseInt(minute.getText())));

    RoomList rooms = model.getAvailableRooms(dateTime,endDate);

    roomList.getItems().clear();
    for (int i = 0; i < rooms.getNumberOfRooms(); i ++)
    {
      roomList.getItems().add(rooms.getRoom(i).getRoomNumber());
    }
  }

  @FXML private void next()
  {

    try
    {

    String line = String.valueOf(date.getValue());
    String lines[] = line.split("-");
    int year = Integer.parseInt(lines[0].trim());
    int month = Integer.parseInt(lines[1].trim());
    int day = Integer.parseInt(lines[2].trim());
    String theRoom= roomList.getValue().toString();
    System.out.println(theRoom);
    model.addSession(course.getText(),Integer.parseInt(length.getText()),day,month,year,Integer.parseInt(hour.getText()),Integer.parseInt(minute.getText()),theRoom);
    error.setText("Successfully added");


    }
catch (IllegalArgumentException e)
{
  error.setText(e.getMessage());
}

  }

@FXML private void back()
{
  viewHandler.openView("main");



}







}
