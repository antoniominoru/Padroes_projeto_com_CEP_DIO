package one.digitalinnovation.gof.singleton;

/**
 * Singleton "apressado".
 *
 * @author antoniominoru
 */
public class SingletonLazyHolder {
    private static class InstanceHolder{
        private static SingletonLazyHolder instancia = new SingletonLazyHolder();
    }

    private SingletonLazyHolder(){
        super();
    }

    public static SingletonLazyHolder getInstance(){
        return InstanceHolder.instancia;
    }
}
