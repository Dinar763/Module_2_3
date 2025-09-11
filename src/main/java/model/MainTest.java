package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
//        Label label = Label.builder()
//                           .name("First")
//                           .build();
//        Post post = Post.builder()
//                .content("First")
//                .status(Status.ACTIVE)
//                         .build();
//        Writer writer = Writer.builder()
//                .firstname("Nfrjq")
//                .lastname("sdjsjsj")
//                             .build();
        try(SessionFactory factory = HibernateUtil.getSessionFactory()) {
            Session session = factory.openSession();
            try (session){
                Transaction transaction = session.beginTransaction();

                List labels = session.createQuery("FROM Label").list();
                System.out.println(labels.size());
                System.out.println();

                session.getTransaction().commit();
            }
        }
    }
}
