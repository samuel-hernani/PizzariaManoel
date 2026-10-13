package pizzariamanoel;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private List<Produto> itens;

    
public Pedido(int numeroPedido, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Produto item) {
        this.itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto item : this.itens) {
            total += item.getPreco();
        }
        return total;
    }

    public void fecharPedido() {
        System.out.println("======================================");
        System.out.println("PEDIDO Nº: " + this.numeroPedido);
        System.out.println("--------------------------------------");
        this.cliente.exibirDados();
        System.out.println("--------------------------------------");
        System.out.println("ITENS DO PEDIDO:");
        for (Produto item : this.itens) {
            item.exibirDetalhes();
        }
        System.out.println("--------------------------------------");
        System.out.println("VALOR TOTAL: R$ " + calcularTotal());
        System.out.println("======================================");
    }
}
