package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mediator.ScheduleModel;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;


public class RoomViewController
{
  @FXML private TableView<RoomViewModel> roomTable;
  @FXML private TableColumn<RoomViewModel, String> nameColumn;
  @FXML private TableColumn<RoomViewModel, Integer> capacityColumn;

  Region root;
  private ViewHandler viewHandler;
  private ScheduleModel model;
  private RoomViewListModel viewModel;


  public RoomViewController()
  {

  }

  public void init(ViewHandler viewHandler, Region root, ScheduleModel model)
  {
    this.model = model;
    this.viewHandler = viewHandler;
    this.root = root;
    this.viewModel = new RoomViewListModel(model);

    nameColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNameProperty());
    capacityColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCapacityProperty().asObject());

    roomTable.setItems(viewModel.getList());

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
    viewHandler.openView("addRoomView");
  }


  @FXML private void main() { viewHandler.openView("main"); }
  @FXML private void classView() {viewHandler.openView("class");}
  @FXML private void student() { viewHandler.openView("studentView"); }
  @FXML private void teacher() { viewHandler.openView("teacherView"); }
  @FXML private void course() { viewHandler.openView("courseView"); }
  @FXML private void session() {viewHandler.openView("sessionView");}

  @FXML void backButton()
  {
    viewHandler.openView("main");
  }
}
