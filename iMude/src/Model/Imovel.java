package Model;

public class Imovel
{
	// Atributos
	int id;
	float preco;
	String endereco;
	String status;
	String descricao;
	String foto;
	int data;
	
	// Construtor
	public Imovel(int id, float preco, String endereco, String status, String descricao, String foto, int data)
	{
		this.id = id;
		this.preco = preco;
		this.endereco = endereco;
		this.status = status;
		this.descricao = descricao;
		this.foto = foto;
		this.data = data;
	}
	
	public Imovel(float preco, String endereco, String status, String descricao, String foto, int data)
	{
		this.preco = preco;
		this.endereco = endereco;
		this.status = status;
		this.descricao = descricao;
		this.foto = foto;
		this.data = data;
	}

	// Getters
	public int getId()
	{
		return id;
	}

	public float getPreco()
	{
		return preco;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public String getStatus()
	{
		return status;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public String getFoto()
	{
		return foto;
	}
	
	public int getData()
	{
		return data;
	}

	// Setters
	public void setId(int id)
	{
		this.id = id;
	}

	public void setPreco(float preco)
	{
		this.preco = preco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public void setFoto(String foto)
	{
		this.foto = foto;
	}
	
	public void setData(int data)
	{
		this.data = data;
	}
}