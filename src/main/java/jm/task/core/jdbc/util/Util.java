package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static Util instance = null;
    private static SessionFactory sessionFactory;
    private static Metadata metadata;

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }

    private Util() {
        if (null == sessionFactory) {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySetting(Environment.HBM2DDL_AUTO, "update").build();
            sessionFactory = makeSessionFactory(serviceRegistry);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory makeSessionFactory(ServiceRegistry serviceRegistry) {
        metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();
        return metadata.buildSessionFactory();
    }
}