package com.yohannes.blogpost.service;

import com.yohannes.blogpost.dao.PostDao;
import com.yohannes.blogpost.models.Post;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yohannes
 */
@Service
public class PostsService {
    
    @Autowired
    PostDao postDao;
    
    public Post createPost(String title, Date created_date, String content,String added_by){
    	Post post = new Post();
    	post.setTitle(title);
        post.setContent(content);
        post.setCreated_date(created_date);
        post.setAdded_by(added_by);
        return post;
    }
    
 
    public Post getPostById(int id){
        return postDao.getPostById(id);      
    }
    public List<Post> getAllPosts(){
        return postDao.getAllPosts();
    }
    public Post addPost(Post post){
        return postDao.addPost(post);
    }
    public void updatePost(Post post){
    	postDao.updatePost(post);
    }
    public void deletePostById(int id){
    	postDao.deletePostById(id);
    }
    
    public boolean isValidDate(String date){
        try{
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
}
