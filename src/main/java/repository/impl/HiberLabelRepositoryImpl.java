package repository.impl;

import model.Label;
import model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.LabelRepository;

import java.util.ArrayList;
import java.util.List;


public class HiberLabelRepositoryImpl implements LabelRepository {

    private final SessionFactory sessionFactory;

    public HiberLabelRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Label getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Label.class, id);
        }
    }

    @Override
    public List<Label> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Label").list();
        }
    }

    @Override
    public Label save(Label label) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(label);
            transaction.commit();
            return label;
        }
    }

    @Override
    public Label update(Label label) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Label mergedLabel = session.merge(label);
            transaction.commit();
            return mergedLabel;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Label label = session.get(Label.class, id);

            if (label != null && !label.getPosts().isEmpty()) {
                for (Post post: new ArrayList<>(label.getPosts())) {
                    post.getLabels().remove(label);
                    session.merge(post);
                }
                label.getPosts().clear();
            }
            session.remove(label);
            transaction.commit();
        }
    }
}
