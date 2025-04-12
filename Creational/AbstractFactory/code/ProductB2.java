public class ProductB2 implements AbstractProductB  {
    @Override
    public void interactWith(AbstractProductA a) {
        System.out.print("ProductB2 interacting with ");
        a.interact();
    }
}
