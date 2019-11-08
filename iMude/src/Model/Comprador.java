public class Comprador
{
	// Atributos
	int id;
	string nome;
	string email;
	string CPF;

	// Construtor
	public Comprador(int id, string nome, string email, string CPF)
	{
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.CPF = CPF;
	}

	// Getters
	public getId()
	{
		return id;
	}

	public getNome()
	{
		return nome;
	}

	public getEmail()
	{
		return email;
	}

	public getCPF()
	{
		return CPF;
	}

	// Setters
	public setId(int id)
	{
		this.id = id
	}

	public setNome(int nome)
	{
		this.nome = nome
	}

	public setEmail(int email)
	{
		this.email = email
	}

	public setCPF(int CPF)
	{
		this.CPF = CPF
	}
}