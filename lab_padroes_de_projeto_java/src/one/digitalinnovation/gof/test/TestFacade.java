package one.digitalinnovation.gof.test;

import one.digitalinnovation.gof.facade.Facade;

public class TestFacade {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.migrarCliente("Antonio","234592885");
    }
}
