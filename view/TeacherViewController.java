package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import model.Student;
import model.StudentID;
import model.Teacher;
import model.TeacherID;

import java.util.Optional;

public class TeacherViewController
{
  @FXML private TableView<TeacherViewModel> teacherTable;
  @FXML private TableColumn<TeacherViewModel, String> nameColumn;
  @FXML private TableColumn<TeacherViewModel, String> idColumn;


  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  private TeacherViewListModel viewModel;


  public TeacherViewController()
  {

  }

  public void init(ViewHandler viewHandler, Region root, ScheduleModel model)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewModel = new TeacherViewListModel(model);

    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    idColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTeacherIDProperty());


    teacherTable.setItems(viewModel.getList());

  }

  public void reset()
  {
    viewModel.update();
  }

  public Region getRoot()
  {
    return root;
  }

  public void addButton()
  {
    viewHandler.openView("addTeacherView");
  }


  @FXML  void main() { viewHandler.openView("main"); }
  @FXML  void classView() {viewHandler.openView("class");}
  @FXML  void student() { viewHandler.openView("studentView"); }
  @FXML  void course() { viewHandler.openView("courseView"); }
  @FXML  void room() { viewHandler.openView("roomView"); }
  @FXML void session() {viewHandler.openView("sessionView");}


  @FXML void backButton()
  {
    viewHandler.openView("main");
  }

  @FXML private void removeButton()
  {
    try
    {


      TeacherViewModel selectedItem = teacherTable.getSelectionModel().getSelectedItem();

      boolean remove = confirmation();
      if (remove)
      {
        Teacher teacher = new Teacher(selectedItem.getNameProperty().get(),new TeacherID(selectedItem.getTeacherIDProperty().get()));
        model.removeTeacher(teacher);
        viewModel.remove(teacher);
        teacherTable.getSelectionModel().clearSelection();
      }
    }
    catch (Exception e)
    {
      System.out.println("Item not found");
    }

  }

  public boolean confirmation()
  {
    int index = teacherTable.getSelectionModel().getFocusedIndex();
    TeacherViewModel selectedItem = teacherTable.getItems().get(index);
    if (index < 0 || index >= teacherTable.getItems().size())
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing Student {"+selectedItem.getNameProperty().get()+":" + selectedItem.getTeacherIDProperty().get()+"}");
    Optional<ButtonType> result = alert.showAndWait();
    return  (result.isPresent())&&(result.get()==ButtonType.OK);

  }

}
