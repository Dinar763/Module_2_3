package repository.impl;

import lombok.AllArgsConstructor;
import model.Writer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.WriterRepository;
import util.HibernateUtil;

import java.util.List;

@AllArgsConstructor
public class HiberWriterRepositoryImpl implements WriterRepository {

    private final Session session;

    @Override
    public Writer getById(Long id) {
        return session.get(Writer.class, id);
    }

    @Override
    public List<Writer> getAll() {
        return session.createQuery("FROM Writer").list();
    }

    @Override
    public Writer save(Writer writer) {
        session.persist(writer);
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        return session.merge(writer);
    }

    @Override
    public void deleteById(Long id) {
        Writer writer = session.get(Writer.class, id);
        if (writer != null) {
            session.remove(writer);
        }
    }
}
