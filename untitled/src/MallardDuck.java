public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWigs();
    }

    @Override
    public void display() {
        System.out.println("I am a MallardDuck");
    }
}
