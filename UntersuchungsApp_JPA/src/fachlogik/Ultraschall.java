package fachlogik;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
//@DiscriminatorValue(value="US")
public class Ultraschall extends Untersuchung
{
	private static final long serialVersionUID = -4287711088641507341L;
	private static BigDecimal PreisProMinute = new BigDecimal("2.0");

	public Ultraschall()
	{
		super();
		this.setBezeichnung("US");
	}

	public static BigDecimal getPreisProMinute()
	{
		return PreisProMinute;
	}
}
