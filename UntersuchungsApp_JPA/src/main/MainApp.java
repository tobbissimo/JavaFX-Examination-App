package main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fachlogik.Geschlecht;
import fachlogik.MagnetResonanz;
import fachlogik.Patient;
import fachlogik.Person;
import fachlogik.Personal;
import fachlogik.Ultraschall;
import fachlogik.Untersuchung;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import persistence.ExaminationDao;
import view.ExaminationEditDialogController;
import view.ExaminationOverviewController;
import view.PersonEditDialogController;

public class MainApp extends Application {

    public Stage primaryStage;
    private ExaminationOverviewController examinationsController;
    private ObservableList<Patient> patientData;
    private ObservableList<Personal> personData;
    private ObservableList<Untersuchung> examinationData;
    private ExaminationDao exDao;

	public MainApp() {
		
		exDao = new ExaminationDao();
		try {
			examinationData = FXCollections.observableArrayList(exDao.allActualExaminations());
			patientData = FXCollections.observableArrayList(exDao.allPatients());
			personData = FXCollections.observableArrayList(exDao.allOldPersons());
			exDao.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public ObservableList<Patient> getPatientData() {
		return patientData;
	}
	
	public ExaminationDao getExaminationDao() {
		return exDao;
	}

	public ObservableList<Personal> getPersonData() {
	    return personData;
	}

	public ObservableList<Untersuchung> getExaminationData() {
	    return examinationData;
	}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UntersuchungsApp");

        showExaminationOverview();
    }

    @Override
    public void stop(){
    	this.primaryStage.close();
    }

    public void showExaminationOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ExaminationOverview.fxml"));
            TabPane examinationOverview = (TabPane) loader.load();

            examinationsController = loader.getController();
            examinationsController.setMainApp(this);

            primaryStage.setScene(new Scene(examinationOverview));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showPersonEditDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Person editieren");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showExaminationEditDialog(Untersuchung exam) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ExaminationEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Untersuchung editieren");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ExaminationEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            controller.setExamination(exam);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

	public ExaminationOverviewController getExaminationsController()
	{
		return examinationsController;
	}

}