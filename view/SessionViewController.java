package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.*;
import model.Class;

import java.util.Optional;

public class SessionViewController
{
  @FXML private TableView<SessionViewModel> sessionTable;
  @FXML private TableColumn<SessionViewModel, String> courseColumn;
  @FXML private TableColumn<SessionViewModel, Integer> lengthColumn;
  @FXML private TableColumn<SessionViewModel, String> startTimeColumn;
  @FXML private TableColumn<SessionViewModel, String> roomColumn;

  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  private SessionViewListModel viewModel;


  public SessionViewController()
  {

  }

  public void init(ViewHandler viewHandler, Region root, ScheduleModel model)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewModel = new SessionViewListModel(model);


    courseColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCourseProperty());
    lengthColumn.setCellValueFactory(
        cellData -> cellData.getValue().getSessionLengthProperty().asObject());
    startTimeColumn.setCellValueFactory(
        cellData -> cellData.getValue().getStartTimeProperty());
    roomColumn.setCellValueFactory(
        cellData -> cellData.getValue().getRoomProperty());

   sessionTable.setItems(viewModel.getList());

  }

  public void reset()
  {
    viewModel.update();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML void addButton()
  {
    viewHandler.openView("addSessionView");
  }

  @FXML void backButton()
  {
    viewHandler.openView("main");
  }



  @FXML private void main() { viewHandler.openView("main"); }
  @FXML private void classView() {viewHandler.openView("class");}
  @FXML private void student() { viewHandler.openView("studentView"); }
  @FXML private void teacher() { viewHandler.openView("teacherView"); }
  @FXML private void course() { viewHandler.openView("courseView"); }
  @FXML private void room() { viewHandler.openView("roomView"); }



  @FXML private void removeButton()
  {
    try
    {


      SessionViewModel selectedItem = sessionTable.getSelectionModel().getSelectedItem();

      boolean remove = confirmation();
      if (remove)
      {
        Session session = new Session(new Course(selectedItem.getCourseProperty().get(),selectedItem.getEctsProperty().get(),new Class(selectedItem.getClassIdProperty().get(),selectedItem.getSemesterProperty().get()),new Teacher(selectedItem.getTeacherNameProperty().get(),new TeacherID(selectedItem.getTeacherIdProperty().get()))),selectedItem.getSessionLengthProperty().get(),new DateTime(new Date(selectedItem.getDayProperty().get(),selectedItem.getMonthProperty().get(),selectedItem.getYearProperty().get()),new Time(selectedItem.getHourProperty().get(),selectedItem.getMinuteProperty().get())),new Room(selectedItem.getRoomCapacityProperty().get(),selectedItem.getRoomNameProperty().get()));
        model.removeSessionBy(selectedItem.getRoomNameProperty().get(),selectedItem.getMonthProperty().get(),selectedItem.getDayProperty().get(),selectedItem.getHourProperty().get(),selectedItem.getMinuteProperty().get());
      //(selectedItem.getCourseNameProperty().get(),selectedItem.getEctsProperty().get(),selectedItem.getClassNameProperty().get(),selectedItem.getTeacherIdProperty().get());
        System.out.println(model.sessionListSize());
        viewModel.remove(session);
        sessionTable.getSelectionModel().clearSelection();
      }
    }
    catch (Exception e)
    {
      System.out.println("Item not found");
    }
    model.exportAll();
  }

  public boolean confirmation()
  {
    int index = sessionTable.getSelectionModel().getFocusedIndex();
    SessionViewModel selectedItem = sessionTable.getItems().get(index);
    if (index < 0 || index >= sessionTable.getItems().size())
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing Course {"+selectedItem.getCourseNameProperty().get()+":" + selectedItem.getStartTimeProperty().get()+"}");
    Optional<ButtonType> result = alert.showAndWait();
    return  (result.isPresent())&&(result.get()==ButtonType.OK);

  }

}
