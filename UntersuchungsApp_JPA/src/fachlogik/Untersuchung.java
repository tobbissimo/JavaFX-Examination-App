package fachlogik;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.FIELD)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="Type", discriminatorType = DiscriminatorType.STRING)
@Table(name="u_untersuchung")
@NamedQueries({
	@NamedQuery(name="Exam.findAll", query="SELECT u FROM Untersuchung u"),
	@NamedQuery(name="Exam.thisYear", query="SELECT u FROM Untersuchung u WHERE u.beginn >= :date")
})
public class Untersuchung implements Serializable
{
	private static final long serialVersionUID = -5026338515249205325L;
	
	@Id
	//@SequenceGenerator(name="MySeq", sequenceName="untersuchung_seq", initialValue=1000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int uid;
	@Transient
	private StringProperty bezeichnung;
	@Transient
    private ObjectProperty<LocalDateTime> beginn;
	@Transient
    private ObjectProperty<LocalDateTime> ende;
	@ManyToOne
	@JoinColumn(name="patient")
	private Patient patient;
	@Transient
	private StringProperty vorname;
	@Transient
	private StringProperty nachname;
	private String kontrastmittel;
	private BigDecimal mengeKM;
	@ManyToMany
	@JoinTable(name="u_emp_exam", 
		joinColumns= {@JoinColumn(name="uid")}, 
		inverseJoinColumns= {@JoinColumn(name="personalnummer")})
	private List<Personal> personal;
	
	public Untersuchung()
	{
		beginn = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
		ende = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now());
		vorname = new SimpleStringProperty();
		nachname = new SimpleStringProperty();
		personal = new ArrayList<>();
	}

	public BigDecimal dauer() 
	{
		return new BigDecimal(Duration.between(beginn.get(), ende.get()).toMinutes());
	}

//	public LocalDateTime getBeginn()
//	{
//		return beginn.get();
//	}
//
//	public LocalDateTime getEnde()
//	{
//		return ende.get();
//	}
//
//	public void setBeginn(LocalDateTime beginn)
//	{
//		this.beginn.set(beginn);
//	}
//
//	public void setEnde(LocalDateTime ende)
//	{
//		this.ende.set(ende);
//	}

	// Implementation SQLite:
	public int getId()
	{
		return uid;
	}
	
	public void setId(int uid)
	{
		this.uid = uid;
	}
	
	@Access(AccessType.PROPERTY)
	@Column(name="Beginn")
	public String getBeginn()
	{
		return beginn.get().toString();
	}

	@Access(AccessType.PROPERTY)
	@Column(name="Ende")
	public String getEnde()
	{
		return ende.get().toString()	;
	}

	public void setBeginn(String beginn)
	{
		this.beginn.set(LocalDateTime.parse(beginn, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	public void setEnde(String ende)
	{
		this.ende.set(LocalDateTime.parse(ende, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}

	public ObjectProperty<LocalDateTime> getBeginnProperty()
	{
		return beginn;
	}

	public ObjectProperty<LocalDateTime> getEndeProperty()
	{
		return ende;
	}

	public void setKm(String km)
	{
		this.kontrastmittel = km;
	}

	public Patient getPatient()
	{
		return patient;
	}

	public void setPatient(Patient patient)
	{
		this.patient = patient;
		vorname.set(patient.getVorname());
		nachname.set(patient.getNachname());
	}

	@Access(AccessType.PROPERTY)
	@Column(name="Vorname")
	public String getVorname() {
		return vorname.get();
	}
	
	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}
	public StringProperty getVornameProperty() {
		return vorname;
	}
	
	@Access(AccessType.PROPERTY)
	@Column(name="Nachname")
	public String getNachname() {
		return nachname.get();
	}
	
	public void setNachname(String nachname) {
		this.nachname.set(nachname);
	}
	
	public StringProperty getNachnameProperty() {
		return nachname;
	}

	public BigDecimal getMengeKM()
	{
		return mengeKM;
	}

	public void setMengeKM(String mengeKM)
	{
		this.mengeKM = new BigDecimal(mengeKM);
	}

	public String getKm()
	{
		return kontrastmittel;
	}

	@Access(AccessType.PROPERTY)
	@Column(name="Bezeichnung")
	public String getBezeichnung()
	{
		return bezeichnung.get();
	}

	public StringProperty getBezeichnungProperty()
	{
		return bezeichnung;
	}

	public void setBezeichnung(String name)
	{
		this.bezeichnung = new SimpleStringProperty(name);
	}

	public List<Personal> getPersonal() {
		return personal;
	}

	public void setPersonal(List<Personal> personal) {
		this.personal = personal;
	}
	
	public void addPersonal(Personal p) {
		this.personal.add(p);
	}
}
