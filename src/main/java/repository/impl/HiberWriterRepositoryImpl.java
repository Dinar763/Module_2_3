package repository.impl;

import model.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.WriterRepository;

import java.util.List;

public class HiberWriterRepositoryImpl implements WriterRepository {

    private final SessionFactory sessionFactory;

    public HiberWriterRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Writer getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Writer.class, id);
        }
    }

    @Override
    public List<Writer> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Writer").list();
        }
    }

    @Override
    public Writer save(Writer writer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(writer);
            transaction.commit();
            return writer;
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Writer mergedWriter = session.merge(writer);
            transaction.commit();
            return mergedWriter;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Writer writer = session.get(Writer.class, id);
            session.remove(writer);
            transaction.commit();
        }
    }
}
