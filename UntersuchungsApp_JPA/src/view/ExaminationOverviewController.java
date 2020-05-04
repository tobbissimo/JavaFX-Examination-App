package view;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import fachlogik.ComputerTomographie;
import fachlogik.Endoskopie;
import fachlogik.MagnetResonanz;
import fachlogik.Patient;
import fachlogik.Personal;
import fachlogik.Roentgen;
import fachlogik.Ultraschall;
import fachlogik.Untersuchung;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import main.MainApp;

public class ExaminationOverviewController {
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> firstNameColumnP;
    @FXML
    private TableColumn<Patient, String> lastNameColumnP;
    
    @FXML
    private TableView<Personal> personTable;
    @FXML
    private TableColumn<Personal, String> firstNameColumn;
    @FXML
    private TableColumn<Personal, String> lastNameColumn;
    
    @FXML
    private TableView<Untersuchung> examinationTable;
    @FXML
    private TableColumn<Untersuchung, String> typeColumn;
    @FXML
    private TableColumn<Untersuchung, String> eFirstNameColumn;
    @FXML
    private TableColumn<Untersuchung, String> eLastNameColumn;
    @FXML
    private TableColumn<Untersuchung, LocalDateTime> startColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label svnrLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private Label firstNameLabelP;
    @FXML
    private Label lastNameLabelP;
    @FXML
    private Label addressLabelP;
    @FXML
    private Label svnrLabelP;
    @FXML
    private Label genderLabelP;
    @FXML
    private Label birthdayLabelP;

    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label patientLabel;
    @FXML
    private Label mediumLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label staffLabel;

    private MainApp mainApp;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM hh:mm");
    
    @FXML
    private void initialize() {
        firstNameColumnP.setCellValueFactory(cellData -> cellData.getValue().getVornameProperty());
        lastNameColumnP.setCellValueFactory(cellData -> cellData.getValue().getNachnameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getVornameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNachnameProperty());

        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getBezeichnungProperty());
        eFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getVornameProperty());
        eLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNachnameProperty());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().getBeginnProperty());
        startColumn.setCellFactory(cell -> new TableCell<Untersuchung, LocalDateTime>() {
        	@Override
        	protected void updateItem(LocalDateTime item, boolean empty) {
        		super.updateItem(item, empty);
        		if (empty)
        			setText(null);
        		else
        			setText(String.format(item.format(formatter)));
        	}
        });
        
        showPatientDetails(null);
        showPersonDetails(null);
        showExaminationDetails(null);
        
        patientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPatientDetails(newValue));
		patientTable.setPlaceholder(new Label("Keine Patienten vorhanden"));
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
		personTable.setPlaceholder(new Label("Keine Personen vorhanden"));
        examinationTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showExaminationDetails(newValue));
        examinationTable.setPlaceholder(new Label("Keine Untersuchungen vorhanden"));
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        patientTable.setItems(mainApp.getPatientData());
        patientTable.getSelectionModel().selectFirst();
        personTable.setItems(mainApp.getPersonData());
        personTable.getSelectionModel().selectFirst();
        examinationTable.setItems(mainApp.getExaminationData());
        examinationTable.getSelectionModel().selectFirst();
    }

    private void showPatientDetails(Patient patient) {
        if (patient != null) {
            firstNameLabelP.setText(patient.getVorname());
            lastNameLabelP.setText(patient.getNachname());
            addressLabelP.setText(patient.getAdresse());
            genderLabelP.setText(patient.getGeschlecht().toString());
            svnrLabelP.setText(Long.toString(patient.getSvnr()));
            birthdayLabelP.setText(dateFormatter.format(patient.getGeburtsdatum()));
            //birthdayLabelP.setText(patient.getGeburtsdatum().toString());
        } else {
            firstNameLabelP.setText("");
            lastNameLabelP.setText("");
            addressLabelP.setText("");
            genderLabelP.setText("");
            svnrLabelP.setText("");
            birthdayLabelP.setText("");
        }
    }

    private void showPersonDetails(Personal person) {
        if (person != null) {
            firstNameLabel.setText(person.getVorname());
            lastNameLabel.setText(person.getNachname());
            addressLabel.setText(person.getAdresse());
            genderLabel.setText(person.getGeschlecht().toString());
            svnrLabel.setText(Long.toString(person.getSvnr()));
            birthdayLabel.setText(dateFormatter.format(person.getGeburtsdatum()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            addressLabel.setText("");
            genderLabel.setText("");
            svnrLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    private void showExaminationDetails(Untersuchung examination) {
        if (examination != null) {
        	//startLabel.setText(examination.getBeginn().format(formatter));
        	//endLabel.setText(examination.getEnde().format(formatter));
        	// Implementation SQLite:
        	startLabel.setText(dateTimeFormatter.format(LocalDateTime.parse(examination.getBeginn(), DateTimeFormatter.ISO_DATE_TIME)));
        	endLabel.setText(dateTimeFormatter.format(LocalDateTime.parse(examination.getEnde(), DateTimeFormatter.ISO_DATE_TIME)));
        	typeLabel.setText(examination.getBezeichnung());
        	patientLabel.setText(examination.getPatient().getNachname()+" "+examination.getPatient().getVorname());
        	mediumLabel.setText(examination.getKm());
        	if (examination.getMengeKM()!=null)
        		quantityLabel.setText(examination.getMengeKM().toString());
        	else
            	quantityLabel.setText("");
        	StringJoiner joiner = new StringJoiner(", ", "", "");
            if (examination.getPersonal() != null)
            	for (Personal p:examination.getPersonal())
            		joiner.add(p.toString());
            staffLabel.setText(joiner.toString());
        } else {
        	startLabel.setText("");
        	endLabel.setText("");
        	typeLabel.setText("");
        	patientLabel.setText("");
        	mediumLabel.setText("");
        	quantityLabel.setText("");
        	staffLabel.setText("");
        }
    }

    @FXML
    void handleNewPatient() {
        Patient tempPatient = new Patient(0, "2000-01-01", null, null, null, null, null);
        boolean okClicked = mainApp.showPersonEditDialog(tempPatient);
        if (okClicked) {
        	try {
        		mainApp.getPatientData().add(tempPatient);
        		mainApp.getExaminationDao().newPatient(tempPatient);
				mainApp.getExaminationDao().close();
				patientTable.getSelectionModel().select(tempPatient);
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }

    @FXML
    void handleEditPatient() {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPatient);
            if (okClicked) {
            	try {
            		mainApp.getExaminationDao().editPatient(selectedPatient);
					mainApp.getExaminationDao().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                showPatientDetails(selectedPatient);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Patient Selected");
            alert.setContentText("Please select a patient in the table.");

            alert.showAndWait();
        }
    }    
    
    @FXML
    void handleDeletePatient() {
        int selectedIndex = patientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	try {
        		mainApp.getExaminationDao().deletePatient(patientTable.getItems().get(selectedIndex));
				mainApp.getExaminationDao().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            patientTable.getItems().remove(selectedIndex);
           
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Patient Selected");
            alert.setContentText("Please select a patient in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    void handleNewPerson() {
        Personal tempPerson = new Personal(0, "2000-01-01", null, null, null, null, 0);
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
        	try {
        		mainApp.getPersonData().add(tempPerson);
        		mainApp.getExaminationDao().newPerson(tempPerson);
    			personTable.getSelectionModel().select(tempPerson);
				mainApp.getExaminationDao().close();
				//New Personal younger than 18 will show up in the Table. That could of course be easily solved with an If case but I didn't think it made sense.
				//Personal that is younger than 18 could then only be added to the database and not to the PersonData List.
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }

    @FXML
    void handleEditPerson() {
        Personal selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
            	try {
            		mainApp.getExaminationDao().editPerson(selectedPerson);
					mainApp.getExaminationDao().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }    
    
    @FXML
    void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
			try {
				mainApp.getExaminationDao().deletePerson(personTable.getItems().get(selectedIndex));
				mainApp.getExaminationDao().close();
				personTable.getItems().remove(selectedIndex);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    void handleNewExaminationCT() {
        ComputerTomographie tempExam = new ComputerTomographie();
        boolean okClicked = mainApp.showExaminationEditDialog(tempExam);
        if (okClicked) {
        	try {
        		mainApp.getExaminationDao().newExamination(tempExam);
				mainApp.getExaminationDao().close();
				mainApp.getExaminationData().add(tempExam);
	 			examinationTable.getSelectionModel().select(tempExam);
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }

    @FXML
    void handleNewExaminationEN() {
    	Endoskopie tempExam = new Endoskopie();
        boolean okClicked = mainApp.showExaminationEditDialog(tempExam);
        if (okClicked) {
        	try {
        		mainApp.getExaminationDao().newExamination(tempExam);
				mainApp.getExaminationDao().close();
				mainApp.getExaminationData().add(tempExam);
	 			examinationTable.getSelectionModel().select(tempExam);
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }

    @FXML
    void handleNewExaminationMR() {
    	MagnetResonanz tempExam = new MagnetResonanz();
        boolean okClicked = mainApp.showExaminationEditDialog(tempExam);
        if (okClicked) {
        	try {
        		mainApp.getExaminationDao().newExamination(tempExam);
				mainApp.getExaminationDao().close();
				mainApp.getExaminationData().add(tempExam);
	 			examinationTable.getSelectionModel().select(tempExam);
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }

    @FXML
    void handleNewExaminationRT() {
    	Roentgen tempExam = new Roentgen();
        boolean okClicked = mainApp.showExaminationEditDialog(tempExam);
        if (okClicked) {
        	try {
        		mainApp.getExaminationDao().newExamination(tempExam);
				mainApp.getExaminationDao().close();
				mainApp.getExaminationData().add(tempExam);
	 			examinationTable.getSelectionModel().select(tempExam);
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }

    @FXML
    void handleNewExaminationUS() {
    	Ultraschall tempExam = new Ultraschall();
        boolean okClicked = mainApp.showExaminationEditDialog(tempExam);
        if (okClicked) {
        	try {
        		mainApp.getExaminationDao().newExamination(tempExam);
				mainApp.getExaminationDao().close();
				mainApp.getExaminationData().add(tempExam);
	 			examinationTable.getSelectionModel().select(tempExam);
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }

    @FXML
    void handleEditExamination() {
        Untersuchung selectedExam = examinationTable.getSelectionModel().getSelectedItem();
        if (selectedExam != null) {
            boolean okClicked = mainApp.showExaminationEditDialog(selectedExam);
            if (okClicked) {
            	try {
            		mainApp.getExaminationDao().editExamination(selectedExam);
					mainApp.getExaminationDao().close();
	                showExaminationDetails(selectedExam);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No examination Selected");
            alert.setContentText("Please select a examination in the table.");

            alert.showAndWait();
        }
    }    
    
    @FXML
    void handleDeleteExamination() {
        int selectedIndex = examinationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	try {
        		mainApp.getExaminationDao().deleteExamination(examinationTable.getItems().get(selectedIndex));
				mainApp.getExaminationDao().close();
	            examinationTable.getItems().remove(selectedIndex);
			} catch (IOException e) {
				e.printStackTrace();
			}
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No examination Selected");
            alert.setContentText("Please select a examination in the table.");

            alert.showAndWait();
        }
    }
}
