public class ProductB1 implements AbstractProductB {
    @Override
    public void interactWith(AbstractProductA a) {
        System.out.print("ProductB1 interacting with ");
        a.interact();
    }
}
