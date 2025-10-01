package repository.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import model.Post;
import model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.PostRepository;
import util.HibernateUtil;

import java.util.List;

@AllArgsConstructor
public class HiberPostRepositoryImpl implements PostRepository {

    private final Session session;

    @Override
    public Post getById(Long id) {
        String hql = "SELECT p FROM Post p " +
                "LEFT JOIN FETCH p.labels " +
                "LEFT JOIN FETCH p.writer " +
                "WHERE p.id = :id AND p.status = 'ACTIVE'";
        return session.createQuery(hql, Post.class)
                      .setParameter("id", id)
                      .uniqueResult();
    }

    @Override
    public List<Post> getAll() {
        String hql = "SELECT p FROM Post p " +
                "LEFT JOIN FETCH p.labels " +
                "LEFT JOIN FETCH p.writer " +
                "WHERE p.status = 'ACTIVE'";
        return session.createQuery(hql, Post.class).list();
    }

    @Override
    public Post save(Post post) {
        session.persist(post);
        return post;
    }

    @Override
    public Post update(Post post) {
        session.merge(post);
        return post;
    }

    @Override
    public void deleteById(Long id) {
        String hql = "UPDATE Post p " +
                "SET p.status = 'DELETED' " +
                "WHERE p.id = :id ";
        int updatedCount = session.createMutationQuery(hql)
                                  .setParameter("id", id)
                                  .executeUpdate();

        if (updatedCount == 0) {
            throw new EntityNotFoundException("Post with id " + id + " not found");
        }
    }
}
