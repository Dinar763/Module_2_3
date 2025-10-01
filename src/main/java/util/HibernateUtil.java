package util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {

        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public static void beginTransaction() {
        getCurrentSession().beginTransaction();
    }

    public static void commitTransaction() {
        getCurrentSession().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        getCurrentSession().getTransaction().rollback();
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
