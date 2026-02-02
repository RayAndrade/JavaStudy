//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Adaptee adaptee = new Adaptee("ORIGINAL_ADAPTEE_VALUE");
    Adapter adapter = new Adapter(adaptee);

    Client client = new Client(adapter);

    System.out.println("=== First call (default adapter prefix) ===");
    client.Operation();

    System.out.println("Adaptee internal value (baseline): " + adaptee.getAdapteeValue());
    adapter.setPrefix("[Adapter UPDATED prefix] ");

    System.out.println("\n=== Second call (adapter prefix changed) ===");
    client.Operation();
    System.out.println("Adaptee internal value (should be unchanged): " + adaptee.getAdapteeValue());
    adaptee.setAdapteeValue("ADAPTEE_VALUE_CHANGED_DIRECTLY");

    System.out.println("\n=== Third call (adaptee changed directly) ===");
    client.Operation();
    System.out.println("Adaptee internal value (now changed): " + adaptee.getAdapteeValue());
}
