import { Implementor } from './Implementor.js';

class ConcreteImplementorB extends Implementor {
    operationImpl() {
        console.log("ConcreteImplementorB: Doing work B.");
    }
}
export { ConcreteImplementorB };