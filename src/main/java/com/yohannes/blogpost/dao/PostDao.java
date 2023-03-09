package com.yohannes.blogpost.dao;


import com.yohannes.blogpost.models.Post;
import java.util.List;

/**
 *
 * @author Yohannes
 */
public interface PostDao {
    
    Post getPostById(int id);
    List<Post> getAllPosts();
    Post addPost(Post hero);
    void updatePost(Post hero);
    void deletePostById(int id);
}
