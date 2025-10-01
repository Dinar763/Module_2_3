package service;

import model.Post;

import java.util.List;

public interface PostService extends GenericService <Post> {


    Post save(String content, Long writerID, List<Long> labelIds);
    Post update(Long postId, String newContent, Long newWriterID, List<Long> labelIds);
}
