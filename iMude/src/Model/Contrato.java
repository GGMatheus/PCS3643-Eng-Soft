package Model;

public class Contrato {
    private int id;
    private String data;
    private String descricao;
 
    public Contrato(int id, String data, String descricao) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
    }
    
    public Contrato(String data, String descricao) {
    	this.data = data;
    	this.descricao = descricao;
    }
    
    public Contrato() {
    	
    }
    
    public Contrato(int id) {
    	this.id = id;
    }
 
    public int getId() { return id; }
    
    public void setId(int id) { this.id = id; }
 
    public void setData(String data) { this.data = data; }
 
    public String getData() { return data; }
    
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getDescricao() { return descricao; }
 
}