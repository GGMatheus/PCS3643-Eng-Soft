package Model;

public class Visita_Compra
{
	// Atributos
	int idImovel;
	int idCorretor;
	int idComprador;
	String data;
	String horario;

	// Construtor
	public Visita_Compra(int idImovel, int idCorretor, int idComprador, String data, String horario)
	{
		this.idImovel = idImovel;
		this.idCorretor = idCorretor;
		this.idComprador = idComprador;
		this.data = data;
		this.horario = horario;
	}

	// Getters
	public int getIdImovel()
	{
		return idImovel;
	}

	public int getIdCorretor()
	{
		return idCorretor;
	}

	public int getIdComprador()
	{
		return idComprador;
	}

	public String getData()
	{
		return data;
	}

	public String getHorario()
	{
		return horario;
	}

	// Setters
	public void setIdImovel(int idImovel)
	{
		this.idImovel = idImovel;
	}

	public void setIdCorretor(int idCorretor)
	{
		this.idCorretor = idCorretor;
	}

	public void setIdComprador(int idComprador)
	{
		this.idComprador = idComprador;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public void horario(String horario)
	{
		this.horario = horario;
	}
}