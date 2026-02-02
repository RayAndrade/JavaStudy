public class Adapter  implements Target{
    private Adaptee adaptee;

    private String prefix;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
        this.prefix = "[Adapter default prefix] ";
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    @Override
    public String Request() {
        String adapteeResult = adaptee.SpecificRequest();
        return prefix + "Translated -> " + adapteeResult;
    }
}
