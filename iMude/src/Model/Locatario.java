package Model;

public class Locatario
{
	// Atributos
	int id;
	String nome;
	String email;
	String CPF;

	// Construtor
	public Locatario(int id, String nome, String email, String CPF)
	{
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.CPF = CPF;
	}
	
	public Locatario(String nome, String email, String CPF)
	{
		this.nome = nome;
		this.email = email;
		this.CPF = CPF;
	}

	// Getters
	public int getId()
	{
		return id;
	}

	public String getNome()
	{
		return nome;
	}

	public String getEmail()
	{
		return email;
	}

	public String getCPF()
	{
		return CPF;
	}

	// Setters
	public void setId(int id)
	{
		this.id = id;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setCPF(String CPF)
	{
		this.CPF = CPF;
	}
}