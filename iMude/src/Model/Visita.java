public class Visita_Compra
{
	// Atributos
	int idImovel;
	int idCorretor;
	int idComprador;
	string data;
	string horario;

	// Construtor
	public Vendedor(int idImovel, int idCorretor, int IdComprador, string data, string horario)
	{
		this.idImovel = idImovel;
		this.idCorretor = idCorretor;
		this.idComprador = idComprador;
		this.data = data;
		this.horario = horario;
	}

	// Getters
	public getIdImovel()
	{
		return idImovel;
	}

	public getIdCorretor()
	{
		return idCorretor;
	}

	public getIdComprador()
	{
		return idComprador;
	}

	public getData()
	{
		return data;
	}

	public getHorario()
	{
		return horario;
	}

	// Setters
	public setIdImovel(int idImovel)
	{
		this.idImovel = idImovel;
	}

	public setIdCorretor(int idCorretor)
	{
		this.idCorretor = idCorretor;
	}

	public setIdComprador(int idComprador)
	{
		this.idComprador = idComprador;
	}

	public setData(string data)
	{
		this.data = data;
	}

	public horario(string horario)
	{
		this.horario = horario;
	}
}