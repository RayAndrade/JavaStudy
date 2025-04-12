Here's a complete Java example of the **Abstract Factory** design pattern, a **Creational** pattern, using the same names and structure as described in the Gang of Four book *"Design Patterns: Elements of Reusable Object-Oriented Software."* This is formatted as if for a `README.md` file. Each class/interface is explained and commented in detail, and the order of creation avoids dependency issues.

---

# Abstract Factory Pattern in Java  
**Pattern Category**: Creational  
**Pattern Name**: Abstract Factory  
**Programming Language**: Java  
**Target Audience**: First-year college students  

---

## Purpose
The **Abstract Factory** pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes.

---

## Class/File Creation Order (to avoid dependency errors):

1. `AbstractProductA.java`
2. `AbstractProductB.java`
3. `ProductA1.java`
4. `ProductA2.java`
5. `ProductB1.java`
6. `ProductB2.java`
7. `AbstractFactory.java`
8. `ConcreteFactory1.java`
9. `ConcreteFactory2.java`
10. `Client.java`
11. `Main.java`

---

## Class Descriptions and Code

### 1. `AbstractProductA.java`
```java
// Abstract product A declares an interface for a type of product object
public interface AbstractProductA {
    void interact();
}
```
- **Interface**: `AbstractProductA` — base for all `ProductA` types.
- **Method**: `interact()` — interaction behavior; defined later in concrete classes.

---

### 2. `AbstractProductB.java`
```java
// Abstract product B declares an interface for a type of product object
public interface AbstractProductB {
    void interactWith(AbstractProductA a);
}
```
- **Interface**: `AbstractProductB` — base for all `ProductB` types.
- **Method**: `interactWith(AbstractProductA a)` — defines dependency on `ProductA`.

---

### 3. `ProductA1.java`
```java
public class ProductA1 implements AbstractProductA {
    @Override
    public void interact() {
        System.out.println("ProductA1 interacting");
    }
}
```
- **Concrete Product**: Implements `AbstractProductA`.
- **Method**: Implements interaction logic for variant A1.

---

### 4. `ProductA2.java`
```java
public class ProductA2 implements AbstractProductA {
    @Override
    public void interact() {
        System.out.println("ProductA2 interacting");
    }
}
```
- **Concrete Product**: Implements `AbstractProductA`.
- **Method**: Implements interaction logic for variant A2.

---

### 5. `ProductB1.java`
```java
public class ProductB1 implements AbstractProductB {
    @Override
    public void interactWith(AbstractProductA a) {
        System.out.print("ProductB1 interacting with ");
        a.interact();
    }
}
```
- **Concrete Product**: Implements `AbstractProductB`.
- **Method**: Calls `a.interact()` showing inter-product behavior.

---

### 6. `ProductB2.java`
```java
public class ProductB2 implements AbstractProductB {
    @Override
    public void interactWith(AbstractProductA a) {
        System.out.print("ProductB2 interacting with ");
        a.interact();
    }
}
```
- **Concrete Product**: Implements `AbstractProductB`.
- **Method**: Similar to `ProductB1` but represents a different product variant.

---

### 7. `AbstractFactory.java`
```java
// Abstract Factory declares an interface for creating abstract products A and B
public interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}
```
- **Interface**: Factory for creating families of products A and B.
- **Methods**:
  - `createProductA()` — produces `AbstractProductA`
  - `createProductB()` — produces `AbstractProductB`

---

### 8. `ConcreteFactory1.java`
```java
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
```
- **Concrete Factory**: Implements creation of `ProductA1` and `ProductB1`.

---

### 9. `ConcreteFactory2.java`
```java
public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
```
- **Concrete Factory**: Implements creation of `ProductA2` and `ProductB2`.

---

### 10. `Client.java`
```java
// Client uses only interfaces declared by AbstractFactory and products
public class Client {
    private AbstractProductA productA;
    private AbstractProductB productB;

    public Client(AbstractFactory factory) {
        // The factory creates the concrete products
        productA = factory.createProductA();
        productB = factory.createProductB();
    }

    public void run() {
        productB.interactWith(productA);
    }
}
```
- **Class**: Depends only on abstract interfaces.
- **Constructor**: Receives a factory to instantiate related products.
- **Method**: `run()` demonstrates interaction between products.

---

### 11. `Main.java`
```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Using ConcreteFactory1:");
        AbstractFactory factory1 = new ConcreteFactory1();
        Client client1 = new Client(factory1);
        client1.run();

        System.out.println("\nUsing ConcreteFactory2:");
        AbstractFactory factory2 = new ConcreteFactory2();
        Client client2 = new Client(factory2);
        client2.run();
    }
}
```
- **Entry Point**: Demonstrates how the Abstract Factory is used at runtime.
- **Switching Factories**: Shows that client code remains unchanged when using different product families.

---

## Summary

The **Abstract Factory** pattern allows you to create families of related objects without binding your code to their concrete classes. This example uses the exact naming and structure from the *Gang of Four* book, making it ideal for educational purposes.

**Benefits:**
- Promotes consistency among products.
- Isolates concrete classes.
- Simplifies code updates for new families.

---

Would you like this zipped up into a downloadable project or need UML diagrams to go with it?
