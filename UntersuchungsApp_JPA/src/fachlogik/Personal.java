package fachlogik;

import javax.persistence.*;

@Entity
@Table(name="u_personal")
@NamedQueries({
	@NamedQuery(name="Personal.findAll", query="SELECT p FROM Personal p"),
	@NamedQuery(name="Personal.18", query="SELECT p FROM Personal p WHERE p.geburtsdatum < :date")
})
public class Personal extends Person
{
	private static final long serialVersionUID = 3729870032057975712L;
	
	@Id
	@SequenceGenerator(name="MySeq", sequenceName="personal_seq", initialValue=1000, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long personalnummer;

    public Personal() {
        super();
    }

    public Personal(long svnr, String geburtsdatum, String vorname, String nachname, Geschlecht geschlecht, String adresse, long personalnummer) {
    	super(svnr, geburtsdatum, vorname, nachname, geschlecht, adresse);
    	this.personalnummer = personalnummer;
    }
	
	public long getPersonalnummer()
	{
		return personalnummer;
	}

	public void setPersonalnummer(long personalnummer)
	{
		this.personalnummer = personalnummer;
	}
}
