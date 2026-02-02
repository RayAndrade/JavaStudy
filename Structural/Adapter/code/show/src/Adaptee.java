public class Adaptee {
    private String adapteeValue;

    public Adaptee(String adapteeValue) {
        this.adapteeValue = adapteeValue;
    }

    public String SpecificRequest() {
        return "Adaptee.SpecificRequest(): adapteeValue=" + adapteeValue;
    }
    public String getAdapteeValue() {
        return adapteeValue;
    }
    public void setAdapteeValue(String adapteeValue) {
        this.adapteeValue = adapteeValue;
    }
}
