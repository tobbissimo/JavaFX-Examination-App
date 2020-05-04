package fachlogik;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="u_patient")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient extends Person
{
	private static final long serialVersionUID = 3310359118256555312L;
	
	@Id
	//@SequenceGenerator(name="MySeq", sequenceName="patient_seq", initialValue=1000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String krankenkasse;
	@OneToMany(mappedBy="patient")
	private List<Untersuchung> untersuchungen;

    public Patient() {
        super();
        this.krankenkasse = "n/a";
        this.untersuchungen = new ArrayList<>();
    }

    public Patient(long svnr, String geburtsdatum, String vorname, String nachname, Geschlecht geschlecht, String adresse, String krankenkasse) {
    	super(svnr, geburtsdatum, vorname, nachname, geschlecht, adresse);
    	this.krankenkasse = krankenkasse;
    	this.untersuchungen = new ArrayList<>();
    }
	
	public String getKrankenkasse()
	{
		return krankenkasse;
	}

	public void setKrankenkasse(String krankenkasse)
	{
		this.krankenkasse = krankenkasse;
	}

	public List<Untersuchung> getUntersuchungen()
	{
		return untersuchungen;
	}

	public void setUntersuchungen(List<Untersuchung> untersuchungen)
	{
		this.untersuchungen = untersuchungen;
	}
	
	public void addUntersuchung(Untersuchung u) {
		this.untersuchungen.add(u);
	}
	
	@Override
	public String toString() {
		return this.getNachname()+" "+this.getVorname();
	}
}
