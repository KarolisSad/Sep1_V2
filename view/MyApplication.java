package view;

import javafx.application.Application;
import javafx.stage.Stage;
import mediator.ScheduleModel;
import mediator.ScheduleModelManager;

public class MyApplication extends Application
{
   public void start(Stage stage)
  {
    ScheduleModel scheduleModel = new ScheduleModelManager();
    ViewHandler view = new ViewHandler(scheduleModel);
    view.start(stage);
  }
}
