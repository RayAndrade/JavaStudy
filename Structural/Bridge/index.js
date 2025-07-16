
import { ConcreteImplementorA } from './ConcreteImplementorA.js';
import { ConcreteImplementorB } from './ConcreteImplementorB.js';

import { Abstraction } from './Abstraction.js';
import { RefinedAbstraction } from './RefinedAbstraction.js';

const implementorA = new ConcreteImplementorA();

const abstraction1 = new Abstraction(implementorA);
abstraction1.operation();

const implementorB = new ConcreteImplementorB();

const abstraction2 = new RefinedAbstraction(implementorB);
abstraction2.operation();