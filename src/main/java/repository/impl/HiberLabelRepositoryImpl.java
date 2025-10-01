package repository.impl;

import lombok.AllArgsConstructor;
import model.Label;
import model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.LabelRepository;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class HiberLabelRepositoryImpl implements LabelRepository {

    private final Session session;

    @Override
    public Label getById(Long id) {
        return session.get(Label.class, id);
    }

    @Override
    public List<Label> getAll() {
        return session.createQuery("FROM Label").list();
    }

    @Override
    public Label save(Label label) {
        session.persist(label);
        return label;
    }

    @Override
    public Label update(Label label) {
        return session.merge(label);
    }

    @Override
    public void deleteById(Long id) {
        Label label = session.get(Label.class, id);
        if (label != null) {
            session.remove(label);
        }
    }
}
