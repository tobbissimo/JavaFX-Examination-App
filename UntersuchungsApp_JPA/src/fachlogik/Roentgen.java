package fachlogik;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
//@DiscriminatorValue(value="RT")
public class Roentgen extends Untersuchung
{
	private static final long serialVersionUID = -1959902302147180595L;
	private static BigDecimal PreisProMinute = new BigDecimal("2.0");
	private static BigDecimal PreisProBild = new BigDecimal("5.0");
	private int anzahlBilder;

	public Roentgen()
	{
		super();
		this.setBezeichnung("RT");
	}

	public int getAnzahlBilder()
	{
		return anzahlBilder;
	}

	public void setAnzahlBilder(int anzahlBilder)
	{
		this.anzahlBilder = anzahlBilder;
	}

	public static BigDecimal getPreisProMinute()
	{
		return PreisProMinute;
	}

	public static BigDecimal getPreisProBild()
	{
		return PreisProBild;
	}
}
