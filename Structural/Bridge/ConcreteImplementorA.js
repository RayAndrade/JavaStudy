import { Implementor } from './Implementor.js';

class ConcreteImplementorA extends Implementor {
    operationImpl() {
        console.log("ConcreteImplementorA: Doing work A.");
    }
}
export { ConcreteImplementorA };