package pizzariamanoel;

public class Pizza extends Produto {
    private String ingredientes;
    private String tamanho;
    
    public Pizza(String descricao, double preco, String ingredientes, String tamanho) {
        super(descricao, preco);
        this.ingredientes = ingredientes;
        this.tamanho = tamanho;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }
    
    
@Override
    public void exibirDetalhes() {
        System.out.println("Pizza: " + getDescricao() + " (" + this.tamanho + ") - R$ " + getPreco());
        System.out.println("   Ingredientes: " + this.ingredientes);
    }    
    
}
