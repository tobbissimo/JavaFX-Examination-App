package persistence;

import java.io.Closeable;
import java.util.List;

import fachlogik.Patient;
import fachlogik.Personal;
import fachlogik.Untersuchung;

public interface IExaminationDao extends Closeable {

	// Auslesen von allen Untersuchungen
	public List<Untersuchung> allExaminations();
	
	// Auslesen von allen Untersuchungen des aktuellen Jahres
	public List<Untersuchung> allActualExaminations();
	
	// Einf�gen einer neuen Untersuchung
	public void newExamination(Untersuchung u);
	
	// �ndern einer Untersuchung
	public void editExamination(Untersuchung u);
	
	// L�schen einer Untersuchung
	public void deleteExamination(Untersuchung u);

	// Auslesen von allen Patienten
	public List<Patient> allPatients();
	
	// Einf�gen eines neuen Patienten
	public void newPatient(Patient p);
	
	// �ndern eines Patienten
	public void editPatient(Patient p);
	
	// L�schen eines Patienten
	public void deletePatient(Patient p);

	// Auslesen des gesamten Personals
	public List<Personal> allPersons();
	
	// Auslesen des vollj�hrigen Personals
	public List<Personal> allOldPersons();
	
	// Einf�gen von neuem Personal
	public void newPerson(Personal p);
	
	// �ndern von Personal
	public void editPerson(Personal p);
	
	// L�schen von Personal
	public void deletePerson(Personal p);

}
