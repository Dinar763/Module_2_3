package service.impl;

import exception.NotFoundException;
import exception.ServiceException;
import exception.ValidationException;
import lombok.AllArgsConstructor;
import model.Post;
import repository.PostRepository;
import service.PostService;

import java.util.List;

@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Override
    public Post getByID(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        return repository.getById(id);
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = repository.getAll();
        if (posts == null || posts.isEmpty()) {
            throw  new NotFoundException("No posts found");
        }
        return posts;
    }

    @Override
    public Post save(Post entity) {
        if (entity == null) {
            throw new ServiceException("Post must be not null");
        }
        return repository.save(entity);
    }

    @Override
    public Post update(Post entity) {
        if (entity == null) {
            throw new ServiceException("Post must be not null");
        }
        if (entity.getId() == null) {
            throw new ValidationException("Post ID must be not null for update");
        }
        return repository.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        repository.deleteById(id);
    }
}
