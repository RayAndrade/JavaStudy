public class Client {
    private Target target;
    public Client(Target target) {
        this.target = target;
    }
    public void Operation() {
        System.out.println(target.Request());
    }
}
