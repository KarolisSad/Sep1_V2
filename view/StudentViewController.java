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

import java.util.Optional;

public class StudentViewController
{
  @FXML private TableView<StudentViewModel> studentTable;
  @FXML private TableColumn<StudentViewModel, String> nameTable;
  @FXML private TableColumn<StudentViewModel, Number> idTable;
  @FXML private TableColumn<StudentViewModel, String> classTable;
  @FXML private TableColumn<StudentViewModel, Number> semesterTable;

  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  private StudentViewListModel viewModel;


  public StudentViewController()
  {

  }

  public void init(ViewHandler viewHandler, Region root, ScheduleModel model)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewModel = new StudentViewListModel(model);

    nameTable.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    idTable.setCellValueFactory(
        cellData -> cellData.getValue().getStudentIDProperty());
    classTable.setCellValueFactory(
        cellData -> cellData.getValue().getClassIDProperty());
    semesterTable.setCellValueFactory(
        cellData -> cellData.getValue().getSemesterProperty());


    studentTable.setItems(viewModel.getList());
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
    viewHandler.openView("addStudentView");
  }
  @FXML void backButton()
  {
    viewHandler.openView("main");
  }
  @FXML private void main() { viewHandler.openView("main"); }
  @FXML private void classView() {viewHandler.openView("class");}
  @FXML private void teacher() { viewHandler.openView("teacherView"); }
  @FXML private void course() { viewHandler.openView("courseView"); }
  @FXML private void room() { viewHandler.openView("roomView"); }
  @FXML private void session() {viewHandler.openView("sessionView");}
  @FXML private void removeButton()
  {
    try
    {


    StudentViewModel selectedItem = studentTable.getSelectionModel().getSelectedItem();

    boolean remove = confirmation();
    if (remove)
    {
      Student student = new Student(selectedItem.getNameProperty().get(),new StudentID(selectedItem.getStudentIDProperty().get()),selectedItem.getClassIDProperty().get(),selectedItem.getSemesterProperty().get());
      System.out.println(model.studentListSize());
      model.removeStudentFromVIA(student.getStudentId());
      System.out.println(model.studentListSize());
      viewModel.remove(student);
      studentTable.getSelectionModel().clearSelection();
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
    int index = studentTable.getSelectionModel().getFocusedIndex();
    StudentViewModel selectedItem = studentTable.getItems().get(index);
    if (index < 0 || index >= studentTable.getItems().size())
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing Student {"+selectedItem.getStudentIDProperty().get()+":" + selectedItem.getNameProperty().get()+"}");
    Optional<ButtonType> result = alert.showAndWait();
    return  (result.isPresent())&&(result.get()==ButtonType.OK);
  }

}
