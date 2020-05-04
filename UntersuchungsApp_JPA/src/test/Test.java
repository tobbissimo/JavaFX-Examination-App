package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import fachlogik.Geschlecht;
import fachlogik.MagnetResonanz;
import fachlogik.Patient;
import fachlogik.Personal;
import fachlogik.Ultraschall;
import fachlogik.Untersuchung;

public class Test {

	public static void main(String[] args) {
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M.d.yyyy");
		/*
		ArrayList<Patient> patientData = new ArrayList<>();
		ArrayList<Personal> personData = new ArrayList<Personal>();
		ArrayList<Untersuchung> examinationData = new ArrayList<>();
		
		personData.add(new Personal(5678, "01-01-1980", "Hans", "Lang", Geschlecht.MAENNLICH, "1010 Wien, Stephansplatz 1", 12345));
		personData.add(new Personal(8765, "01-01-1980", "Ruth", "Kurz", Geschlecht.WEIBLICH, "1050 Wien, Spengergasse 20", 23456));
		patientData.add(new Patient(1234, "01-01-1980", "Thomas", "Maier", Geschlecht.MAENNLICH, "1010 Wien, Stephansplatz 13", "WGKK"));
	    patientData.add(new Patient(4321, "01-01-1980", "Margit", "Schmidt", Geschlecht.WEIBLICH, "1050 Wien, Spengergasse 27", "BVA"));
	   

		Ultraschall u = new Ultraschall();
		u.setPatient(patientData.get(0));
		u.getPatient().addUntersuchung(u);
		u.setBeginn(LocalDateTime.of(2017, 11, 21, 7, 00).toString());
		u.setEnde("2017-11-21T07:25");
		System.out.println(personData.get(0).getVorname() + " " + personData.get(0).getClass().toString());
		u.addPersonal(personData.get(0));
		examinationData.add(u);

		MagnetResonanz m1 = new MagnetResonanz();
		m1.setPatient(patientData.get(1));
		m1.getPatient().addUntersuchung(m1);
		m1.setKm("Artirem Injektionslösung");
		m1.setMengeKM("3");
		m1.setBeginn("2017-11-21T10:23");
		m1.setEnde("2017-11-21T10:51");
		m1.addPersonal(personData.get(0));
		m1.addPersonal(personData.get(1));
		examinationData.add(m1);
		*/
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("jpaUntersuchungen");
		EntityManager em = emfactory.createEntityManager();
		/*
		em.getTransaction().begin();
		Personal personal = new Personal(5678, "01.01.2004", "Darth", "Vader", Geschlecht.MAENNLICH, "1080 Wien, Albertgasse 1", 34567);
		em.persist(personal);
		patientData.forEach(p -> System.out.println(p.getVorname()));
		personData.forEach(p -> System.out.println(p.getVorname()));
		examinationData.forEach(e -> System.out.println(e.getBezeichnung() + e.getPatient().getVorname()));
		
		patientData.forEach(p -> em.persist(p));
		System.out.println("patients persisted");
		personData.forEach(p -> em.persist(p));
		System.out.println("personal persisted");
		examinationData.forEach(e -> em.persist(e));
		System.out.println("examinations persisted");
		
		em.getTransaction().commit();
		*/
//		
		//TypedQuery<Patient> query1 = em.createNamedQuery("Patient.findAll", Patient.class);
//		List<Patient> patList = query1.getResultList();
//		System.out.println("All Patients");
//		patList.forEach(pat -> System.out.println(pat.getVorname() + " " + pat.getGeschlecht()));
//		
//		TypedQuery<Untersuchung> query2 = em.createNamedQuery("Exam.findAll", Untersuchung.class);
//		List<Untersuchung> uList = query2.getResultList();
//		System.out.println("All Exams");
//		uList.forEach(u -> System.out.println(u.getBezeichnungProperty() + " | " + u.getVorname()));
//		
		TypedQuery<Untersuchung> query3 = em.createNamedQuery("Exam.thisYear", Untersuchung.class);
		query3.setParameter("date", LocalDate.of(LocalDate.now().getYear(), 1, 1).toString());
		List<Untersuchung> uList1 = query3.getResultList();
		System.out.println("Parameter query Exams this year");
		uList1.forEach(u -> System.out.println(u.getBezeichnungProperty() + " | " + u.getVorname()));
		
		System.out.println("----------------------------------------");
		
		TypedQuery<Personal> query4 = em.createNamedQuery("Personal.18", Personal.class);
		//String s = dateFormatter.format(LocalDate.now().minusYears(18)).toString();
		String s = LocalDate.now().minusYears(18).toString();
		//System.out.println(s);
		query4.setParameter("date", s);
		List<Personal> pList = query4.getResultList();
		System.out.println("Parameter query Employees over 18");
		pList.forEach(pers -> System.out.println(pers.getVorname() + " " + pers.getGeburtsdatum()));
	
		em.close();
		emfactory.close();
	}

}
