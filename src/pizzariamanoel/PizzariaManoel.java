package pizzariamanoel;

public class PizzariaManoel {

    public static void main(String[] args) {
        // 1. Cadastrando um Cliente (RF01)
        Cliente cliente1 = new Cliente("Samuel Silva", "(11) 99999-8888", "Rua das Pizzas, 123");

        // 2. Criando itens do Cardápio usando Herança (RF02 e RN02)
        Pizza pizza1 = new Pizza("Calabresa", 45.00, "Mussarela, calabresa e cebola", "Grande");
        Pizza pizza2 = new Pizza("Frango com Catupiry", 50.00, "Frango desfiado e catupiry original", "Grande");
        Bebida bebida1 = new Bebida("Coca-Cola", 12.00, "Garrafa 2L");

        // 3. Registrando o Pedido e adicionando os itens na lista (RF03 e RN01)
        Pedido pedido1 = new Pedido(101, cliente1);
        pedido1.adicionarItem(pizza1);
        pedido1.adicionarItem(pizza2);
        pedido1.adicionarItem(bebida1);

        // 4. Exibindo o resumo e calculando o total automaticamente (RN03)
        pedido1.fecharPedido();
    }
    
}
