package application;

import jakarta.persistence.EntityManagerFactory;
import persistence.config.HibernateConfig;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    }
}
