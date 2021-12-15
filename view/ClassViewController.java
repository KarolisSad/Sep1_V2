package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class ClassViewController
{
  @FXML private TableView<ClassViewModel> classTable;
  @FXML private TableColumn<ClassViewModel, String> classColumn;
  @FXML private TableColumn<ClassViewModel, Integer> semesterColumn;

  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  private ClassViewListModel viewModel;


  public ClassViewController()
  {

  }

  public void init(ViewHandler viewHandler, Region root, ScheduleModel model)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewModel = new ClassViewListModel(model);

    classColumn.setCellValueFactory(
        cellData -> cellData.getValue().getClassNameProperty());
    semesterColumn.setCellValueFactory(
        cellData -> cellData.getValue().getSemesterProperty().asObject());

    classTable.setItems(viewModel.getList());

  }

  public void reset()
  {
    viewModel.update();
  }

  public Region getRoot()
  {
    return root;
  }

 @FXML private void addButton()
 {
   viewHandler.openView("addClassView");
 }
  @FXML private void main() { viewHandler.openView("main"); }
  @FXML private void student() { viewHandler.openView("studentView"); }
  @FXML private void teacher() { viewHandler.openView("teacherView"); }
  @FXML private void course() { viewHandler.openView("courseView"); }
  @FXML private void session() {viewHandler.openView("sessionView");}
  @FXML private void room() { viewHandler.openView("roomView"); }

 @FXML void backButton()
 {
   viewHandler.openView("main");
 }
}
