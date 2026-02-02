Below is a **README.md-style** document with a **Java Adapter (GoF) code sample** using the **exact GoF UML participant names**:

* `Target`
* `Client`
* `Adaptee`
* `Adapter`
👍
…and the GoF-style operation names:

* `Request()` (on `Target`)
* `SpecificRequest()` (on `Adaptee`)

One important note (so your demo matches what the pattern really proves):

* **“Changing a variable on the clone while the original is preserved” is Prototype behavior**, not Adapter.
* For Adapter, the “preserved original value” idea maps best to: **changing adapter-side configuration does NOT change the underlying adaptee state**.

  * In the demo, we’ll change a variable on the `Adapter`, and show the `Adaptee`’s internal value is unchanged.

Everything is explained with **heavy inline comments** (line-by-line style) and “why” sections.

---

# README.md — Adapter Pattern (GoF) in Java

## Intent

The **Adapter** pattern converts the interface of a class into another interface clients expect.
Adapter lets classes work together that couldn’t otherwise because of incompatible interfaces.

---

## GoF UML Participants (Names Preserved)

### `Target`

**Why it exists:** This is the interface the `Client` expects.

### `Client`

**Why it exists:** Talks only to `Target`. The client doesn’t change when we introduce new/legacy classes.

### `Adaptee`

**Why it exists:** Existing (often legacy) class with an incompatible interface (`SpecificRequest`).

### `Adapter`

**Why it exists:** Wraps an `Adaptee` and translates `Request()` into `SpecificRequest()`.

---

## Project Layout

```
/adapter-gof-demo
  Target.java
  Client.java
  Adaptee.java
  Adapter.java
  Main.java
```

---

## Target.java

```java
// Target.java
// ----------
// GoF "Target": the interface that the Client expects.
public interface Target {

    // Request()
    // ---------
    // GoF operation name. Client will call this.
    // WHY: Client code stays stable by depending on this interface.
    String Request();
}
```

---

## Adaptee.java

```java
// Adaptee.java
// -----------
// GoF "Adaptee": an existing class with an incompatible interface.
public class Adaptee {

    // adapteeValue
    // ------------
    // This is internal state of the Adaptee.
    // WHY: We'll prove Adapter changes don't mutate Adaptee's own stored value.
    private String adapteeValue;

    // Constructor
    // -----------
    // WHY: lets us create an Adaptee with a known initial value.
    public Adaptee(String adapteeValue) {
        // Store the constructor parameter into the private field.
        // WHY: demonstrates that Adaptee has its own internal state.
        this.adapteeValue = adapteeValue;
    }

    // SpecificRequest()
    // -----------------
    // GoF operation name. It's "incompatible" with Target.Request().
    // WHY: Client cannot call this directly because it expects Request().
    public String SpecificRequest() {
        // Return something in Adaptee's "native" format.
        // WHY: Adapter will translate this to the Target format.
        return "Adaptee.SpecificRequest(): adapteeValue=" + adapteeValue;
    }

    // getAdapteeValue()
    // -----------------
    // Helper getter to show Adaptee's internal state in the demo.
    // WHY: we want to prove it's preserved even when adapter settings change.
    public String getAdapteeValue() {
        return adapteeValue;
    }

    // setAdapteeValue()
    // -----------------
    // Helper setter to show that only direct changes to Adaptee mutate its state.
    // WHY: demo clarity (what changes what).
    public void setAdapteeValue(String adapteeValue) {
        this.adapteeValue = adapteeValue;
    }
}
```

---

## Adapter.java

```java
// Adapter.java
// ------------
// GoF "Adapter": implements Target and wraps an Adaptee.
// Translates Target.Request() into Adaptee.SpecificRequest().
public class Adapter implements Target {

    // adaptee
    // -------
    // GoF UML shows Adapter has a reference to Adaptee.
    // WHY: Adapter delegates the real work to the Adaptee.
    private Adaptee adaptee;

    // prefix
    // ------
    // Adapter-side configuration/state.
    // WHY: We'll change THIS in Main.java and prove Adaptee's value is preserved.
    private String prefix;

    // Constructor
    // -----------
    // WHY: Adapter must be given an Adaptee to adapt.
    public Adapter(Adaptee adaptee) {
        // Store the reference so Request() can delegate to SpecificRequest().
        this.adaptee = adaptee;

        // Default adapter-side state.
        // WHY: Something we can change without altering Adaptee.
        this.prefix = "[Adapter default prefix] ";
    }

    // setPrefix()
    // -----------
    // Changes only Adapter configuration.
    // WHY: Demonstrates that adapter changes don't mutate adaptee state.
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    // Request()
    // ---------
    // GoF Target operation, implemented by Adapter.
    // WHY: Client calls this method, not SpecificRequest().
    @Override
    public String Request() {

        // Call the adaptee's incompatible method.
        // WHY: This is the "translation" point in the Adapter pattern.
        String adapteeResult = adaptee.SpecificRequest();

        // Return in the format the Client expects.
        // WHY: Adapter modifies/reshapes the result without changing Adaptee itself.
        return prefix + "Translated -> " + adapteeResult;
    }
}
```

---

## Client.java

```java
// Client.java
// -----------
// GoF "Client": depends on Target, not on Adaptee.
// WHY: keeps client code stable and open to new adapters.
public class Client {

    // target
    // ------
    // GoF UML: Client holds a reference to Target.
    // WHY: Client can work with real Targets or Adapters interchangeably.
    private Target target;

    // Constructor
    // -----------
    // WHY: dependency injection; allows swapping implementations at runtime.
    public Client(Target target) {
        this.target = target;
    }

    // Operation()
    // ----------
    // In GoF diagrams, Client calls Request() on Target.
    // WHY: demonstrates client use without knowing about Adaptee.
    public void Operation() {
        // Print the result of calling the Target interface.
        // WHY: proves Adapter makes Adaptee usable through Target.
        System.out.println(target.Request());
    }
}
```

---

## Main.java (Demo runs here)

This demo shows two things:

1. **Adapter makes Adaptee usable via Target.Request()**
2. Changing an **Adapter variable** (`prefix`) does **NOT** change the **Adaptee’s internal value** (`adapteeValue`)
   → That’s the Adapter-appropriate version of “original value preserved”.

```java
// Main.java
// ---------
// Demo entry point, as requested.
public class Main {

    public static void main(String[] args) {

        // 1) Create the Adaptee with an initial internal value.
        // WHY: this represents a legacy/existing component with its own interface.
        Adaptee adaptee = new Adaptee("ORIGINAL_ADAPTEE_VALUE");

        // 2) Wrap the Adaptee with an Adapter.
        // WHY: Adapter implements Target so the Client can use it.
        Adapter adapter = new Adapter(adaptee);

        // 3) Create the Client, depending ONLY on Target.
        // WHY: Client should not know about Adaptee or SpecificRequest().
        Client client = new Client(adapter);

        // --- First run: default adapter prefix ---
        System.out.println("=== First call (default adapter prefix) ===");
        client.Operation();

        // Show the Adaptee's internal value directly.
        // WHY: baseline proof of Adaptee state before adapter changes.
        System.out.println("Adaptee internal value (baseline): " + adaptee.getAdapteeValue());

        // 4) Change a variable on the Adapter (prefix).
        // WHY: This is the key "preserve original" demonstration for Adapter.
        adapter.setPrefix("[Adapter UPDATED prefix] ");

        // --- Second run: adapter changed, adaptee should be unchanged ---
        System.out.println("\n=== Second call (adapter prefix changed) ===");
        client.Operation();

        // Prove Adaptee's internal value is preserved.
        // WHY: Changing adapter config should not mutate adaptee state.
        System.out.println("Adaptee internal value (should be unchanged): " + adaptee.getAdapteeValue());

        // 5) OPTIONAL: show what DOES change Adaptee internal value.
        // WHY: clarity—only direct calls to Adaptee mutators change it.
        adaptee.setAdapteeValue("ADAPTEE_VALUE_CHANGED_DIRECTLY");

        System.out.println("\n=== Third call (adaptee changed directly) ===");
        client.Operation();
        System.out.println("Adaptee internal value (now changed): " + adaptee.getAdapteeValue());
    }
}
```

---

## What This Demo Proves

* The `Client` calls `Target.Request()` and never touches `SpecificRequest()`.
* The `Adapter` translates `Request()` → `SpecificRequest()`.
* Changing `adapter.prefix` changes **only the adapter’s output formatting**.
* The `Adaptee` internal state (`adapteeValue`) remains **preserved** unless **directly** modified.

---

## One small correction (so your teaching stays tight)

If you *literally* want:

> “change a variable on the **clone** and prove the **original** is preserved”

…that’s the **Prototype** pattern. Adapter doesn’t create clones; it **wraps** and **translates**.

