package Model;

public class Visita
{
	// Atributos
	int id;
	int idImovel;
	int idCorretor;
	int idComprador;
	int idLocatario;
	String data;
	String horario;

	// Construtor
	public Visita(int id, int idImovel, int idCorretor, int idComprador, int idLocatario, String data, String horario)
	{
		this.id = id;
		this.idImovel = idImovel;
		this.idCorretor = idCorretor;
		this.idComprador = idComprador;
		this.idLocatario = idLocatario;
		this.data = data;
		this.horario = horario;
	}
	
	public Visita(int idImovel, int idCorretor, int idComprador, int idLocatario, String data, String horario)
	{
		this.idImovel = idImovel;
		this.idCorretor = idCorretor;
		this.idComprador = idComprador;
		this.idLocatario = idLocatario;
		this.data = data;
		this.horario = horario;
	}

	// Getters
	public int getId()
	{
		return id;
	}
	
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
	
	public int getIdLocatario()
	{
		return idLocatario;
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
	public void setId(int id)
	{
		this.id = id;
	}
	
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
	
	public void setIdLocatario(int idLocatario)
	{
		this.idLocatario = idLocatario;
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