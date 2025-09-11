package repository.impl;

import model.Post;
import model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.PostRepository;

import java.util.List;

public class HiberPostRepositoryImpl implements PostRepository {

    private final SessionFactory sessionFactory;

    public HiberPostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Post getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Post.class, id);
        }
    }

    @Override
    public List<Post> getAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Post", Post.class).list();
        }
    }

    @Override
    public Post save(Post post) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(post);
            transaction.commit();
            return post;
        }
    }

    @Override
    public Post update(Post post) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(post);
            transaction.commit();
            return post;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Post post = session.get(Post.class, id);
            post.setStatus(Status.DELETED);
            session.update(post);
            transaction.commit();
        }
    }
}
