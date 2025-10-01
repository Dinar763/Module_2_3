package service.impl;

import exception.ServiceException;
import exception.ValidationException;
import lombok.AllArgsConstructor;
import model.Label;
import model.Post;
import model.Writer;
import repository.LabelRepository;
import repository.PostRepository;
import repository.WriterRepository;
import service.PostService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.HibernateUtil.*;

@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final WriterRepository writerRepository;
    private final LabelRepository labelRepository;



    @Override
    public Post getByID(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        beginTransaction();
        try {
            Post getedPost = postRepository.getById(id);
            commitTransaction();
            return getedPost;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to get post with id: " + id, e);
        }
    }

    @Override
    public List<Post> getAll() {
        beginTransaction();
        try {
            List<Post> posts = postRepository.getAll();
            commitTransaction();

            return posts != null ? posts : Collections.emptyList();
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to get all posts: " +  e);
        }
    }

    @Override
    public Post save(String content, Long writerID, List<Long> existingLabelsIds) {
        beginTransaction();
        try {
            Writer writer = writerRepository.getById(writerID);
            List<Label> existingLabels = new ArrayList<>();
            for (var labelId : existingLabelsIds) {
                Label existingLabel = labelRepository.getById(labelId);
                existingLabels.add(existingLabel);
            }

            Post post = Post.builder()
                            .content(content)
                            .writer(writer)
                            .labels(existingLabels)
                            .build();
            Post savedPost = postRepository.save(post);
            return savedPost;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to save post", e);
        }
    }

    @Override
    public Post update(Long postId, String newContent, Long newWriterID, List<Long> labelIds) {
        beginTransaction();
        try {
            Post post = postRepository.getById(postId);

            if (!post.getWriter().getId().equals(newWriterID)) {
                Writer newWriter = writerRepository.getById(newWriterID);
                post.setWriter(newWriter);
            }

            if (!post.getContent().equals(newContent)) {
                post.setContent(newContent);
            }

            List<Label> existingLabels = new ArrayList<>();
            if (labelIds != null) {
                for (var labelId : labelIds) {
                    Label existingLabel = labelRepository.getById(labelId);
                    existingLabels.add(existingLabel);
                }
            }
            post.setLabels(existingLabels);
            Post updatedPost = postRepository.update(post);

            commitTransaction();
            return updatedPost;
        } catch (Exception e) {
            rollbackTransaction();
            throw new ServiceException("Failed to update post", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new ServiceException("ID must be not null");
        }
        beginTransaction();
        try {
            postRepository.deleteById(id);
            commitTransaction();
        } catch (ServiceException e) {
            rollbackTransaction();
            throw e;
        }
    }
}
