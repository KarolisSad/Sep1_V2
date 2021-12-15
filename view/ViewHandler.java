package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import mediator.ScheduleModel;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private ScheduleModel scheduleModel;
  private MainViewController mainViewController;
  private ClassViewController classViewController;
  private AddClassViewController addClassViewController;
  private StudentViewController studentViewController;
  private AddStudentViewController addStudentViewController;
  private TeacherViewController teacherViewController;
  private AddTeacherViewController addTeacherViewController;
  private CourseViewController courseViewController;
  private AddCourseViewController addCourseViewController;
  private SessionViewController sessionViewController;
  private AddSessionViewController addSessionViewController;
  private RoomViewController roomViewController;
  private AddRoomViewController addRoomViewController;
  private AddStudentToCourseViewController addStudentToCourseViewController;
  private RemoveStudentFromCourseViewController removeStudentFromCourseViewController;

  public ViewHandler(ScheduleModel scheduleModel)
  {
    this.scheduleModel = scheduleModel;
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("main");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "main":
        root = loadMainView("MainView.fxml");
        break;
      case "class":
        root = classView("ClassView.fxml");
        break;
      case "addClassView":
        root = addClassView("AddClassView.fxml");
        break;
      case "studentView":
        root = studentView("StudentView.fxml");
        break;
      case "addStudentView":
        root = addStudentView("AddStudentView.fxml");
        break;
      case "teacherView":
        root = teacherView("TeacherView.fxml");
        break;
      case "addTeacherView":
        root = addTeacherView("AddTeacherView.fxml");
        break;
      case "courseView":
        root = courseView("CourseView.fxml");
        break;
      case "addCourseView":
        root = addCourseView("AddCourseView.fxml");
        break;
      case "sessionView":
        root = sessionView("SessionView.fxml");
        break;
      case "addSessionView":
        root = addSessionView("AddSessionView.fxml");
        break;
      case "roomView":
        root = roomView("RoomView.fxml");
        break;
      case "addRoomView":
        root = addRoomView("AddRoomView.fxml");
        break;
      case "addStudentToCourseView":
        root = addStudentToCourse("AddStudentToCourse.fxml");
        break;
      case "removeStudentFromCourse":
        root = removeStudentFromCourse("RemoveStudentFromCourse.fxml");
      }

    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadMainView(String fxmlFile)
  {
    if (mainViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        mainViewController = loader.getController();
        mainViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      mainViewController.reset();
    }
    return mainViewController.getRoot();
  }

  private Region classView(String fxmlFile)
  {
    if (classViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        classViewController = loader.getController();
        classViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      classViewController.reset();
    }
    return classViewController.getRoot();
  }

  private Region addClassView(String fxmlFile)
  {
    if (addClassViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addClassViewController = loader.getController();
        addClassViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      addClassViewController.reset();
    }
    return addClassViewController.getRoot();
  }

  private Region studentView(String fxmlFile)
  {
    if (studentViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        studentViewController = loader.getController();
        studentViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      studentViewController.reset();
    }
    return studentViewController.getRoot();
  }

  private Region addStudentView(String fxmlFile)
  {
    if (addStudentViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addStudentViewController = loader.getController();
        addStudentViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      addStudentViewController.reset();
    }
    return addStudentViewController.getRoot();
  }

  private Region teacherView(String fxmlFile)
  {
    if (teacherViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        teacherViewController = loader.getController();
        teacherViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      teacherViewController.reset();
    }
    return teacherViewController.getRoot();
  }

  private Region addTeacherView(String fxmlFile)
  {
    if (addTeacherViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addTeacherViewController = loader.getController();
        addTeacherViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      addTeacherViewController.reset();
    }
    return addTeacherViewController.getRoot();
  }

  private Region courseView(String fxmlFile)
  {
    if (courseViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        courseViewController = loader.getController();
        courseViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      courseViewController.reset();
    }
    return courseViewController.getRoot();
  }

  private Region addCourseView(String fxmlFile)
  {
    if (addCourseViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addCourseViewController = loader.getController();
        addCourseViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      addCourseViewController.reset();
    }
    return addCourseViewController.getRoot();
  }

  private Region sessionView(String fxmlFile)
  {
    if (sessionViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        sessionViewController = loader.getController();
        sessionViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      sessionViewController.reset();
    }
    return sessionViewController.getRoot();
  }

  private Region addSessionView(String fxmlFile)
  {
    if (addSessionViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addSessionViewController = loader.getController();
        addSessionViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      addSessionViewController.reset();
    }
    return addSessionViewController.getRoot();
  }

  private Region roomView(String fxmlFile)
  {
    if (roomViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        roomViewController = loader.getController();
        roomViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      roomViewController.reset();
    }
    return roomViewController.getRoot();
  }

  private Region addRoomView(String fxmlFile)
  {
    if (addRoomViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addRoomViewController = loader.getController();
        addRoomViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      addRoomViewController.reset();
    }
    return addRoomViewController.getRoot();
  }


  private Region addStudentToCourse(String fxmlFile)
  {
    if (addStudentToCourseViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        addStudentToCourseViewController = loader.getController();
        addStudentToCourseViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      addStudentToCourseViewController.reset();
    }
    return addStudentToCourseViewController.getRoot();
  }


  private Region removeStudentFromCourse(String fxmlFile)
  {
    if (removeStudentFromCourseViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        removeStudentFromCourseViewController = loader.getController();
        removeStudentFromCourseViewController.init(this, root, scheduleModel);
      }

      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else {
      removeStudentFromCourseViewController.reset();
    }
    return removeStudentFromCourseViewController.getRoot();
  }

}
