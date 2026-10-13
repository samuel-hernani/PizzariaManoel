package pizzariamanoel;

public class Bebida extends Produto{
    private String volume;
    
    public Bebida(String descricao, double preco, String volume) {
        super(descricao, preco);
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
    
    @Override
    public void exibirDetalhes() {
        System.out.println("Bebida: " + getDescricao() + " (" + this.volume + ") - R$ " + getPreco());
    }
}
