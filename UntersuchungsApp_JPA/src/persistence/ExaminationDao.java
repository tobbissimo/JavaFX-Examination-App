package persistence;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fachlogik.Patient;
import fachlogik.Personal;
import fachlogik.Untersuchung;
import javafx.collections.FXCollections;

public class ExaminationDao implements IExaminationDao{

	private EntityManagerFactory ef;
	private EntityManager em;
	
	public ExaminationDao() {
		this.ef = Persistence.createEntityManagerFactory("jpaUntersuchungen");
		this.em = ef.createEntityManager();
	}
	
	@Override
	public void close() throws IOException {
		
	}

	@Override
	public List<Untersuchung> allExaminations() {
		TypedQuery<Untersuchung> query = em.createNamedQuery("Exam.findAll", Untersuchung.class);
		List<Untersuchung> exams = query.getResultList();
		return exams;
	}

	@Override
	public List<Untersuchung> allActualExaminations() {
		TypedQuery<Untersuchung> query = em.createNamedQuery("Exam.thisYear", Untersuchung.class);
		query.setParameter("date", LocalDate.of(LocalDate.now().getYear(), 1, 1).toString());
		List<Untersuchung> exams = query.getResultList();
		return exams;
	}

	@Override
	public void newExamination(Untersuchung u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	@Override
	public void editExamination(Untersuchung u) {
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
		
	}

	@Override
	public void deleteExamination(Untersuchung u) {
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		
	}

	@Override
	public List<Patient> allPatients() {
		TypedQuery<Patient> query = em.createNamedQuery("Patient.findAll", Patient.class);
		List<Patient> patients = query.getResultList();
		return patients;
	}

	@Override
	public void newPatient(Patient p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		
	}

	@Override
	public void editPatient(Patient p) {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}

	@Override
	public void deletePatient(Patient p) {
		if(!em.getTransaction().isActive())
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}

	@Override
	public List<Personal> allPersons() {
		TypedQuery<Personal> query = em.createNamedQuery("Personal.findAll", Personal.class);
		List<Personal> personal = query.getResultList();
		return personal;
	}

	@Override
	public List<Personal> allOldPersons() {
		TypedQuery<Personal> query = em.createNamedQuery("Personal.18", Personal.class);
		String s = LocalDate.now().minusYears(18).toString();
		//System.out.println(s);
		query.setParameter("date", s);
		List<Personal> personal = query.getResultList();
		return query.getResultList();
	}

	@Override
	public void newPerson(Personal p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}

	@Override
	public void editPerson(Personal p) {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}

	@Override
	public void deletePerson(Personal p) {
		if(!em.getTransaction().isActive())
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}

}
