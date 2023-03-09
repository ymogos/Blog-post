package com.yohannes.blogpost.controller;
import com.yohannes.blogpost.models.Post;
import com.yohannes.blogpost.service.PostsService;
import java.beans.Transient;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Yohannes
 */
@Controller
public class PostController {
    
    private final PostsService postService;
   
    public PostController(PostsService postService){
        this.postService = postService;
    }
    
    Set<ConstraintViolation<Post>> violations = new HashSet<>();
    @GetMapping("posts")
    public String displayPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }
    
    
    @GetMapping("/posts/addPost")
    public String diplayAddPost(Model model) {
    	   model.addAttribute("errors", violations);
        return "posts/addPost";
    }
   
    
    @PostMapping("/posts/addPost")
    public String addPost(HttpServletRequest request, Model model){
        violations.clear();
        
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String added_by = request.getParameter("added_by");
      
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());           
     
        Post post = postService.createPost(title,date,content,added_by);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(post);
        if(violations.isEmpty()) {
        	postService.addPost(post);
            return "redirect:/posts";
        }
        
       
        model.addAttribute("errors", violations);
        
        return "redirect:/posts/addPost";
    }
    
    @GetMapping("/posts/deletePost")
    public String deleteHero(Integer id) {
    	postService.deletePostById(id);
        return "redirect:/posts";
    }
    
    @GetMapping("/posts/editPost")
    public String displayEditPost(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("errors", violations);
        
        return "posts/editPost";
    }
    
    @PostMapping("/posts/editPost")
    public String editHero(HttpServletRequest request, Model model){
        violations.clear();
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String added_by = request.getParameter("added_by");
        
        Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());           
 
        Post post = postService.createPost(title,date,content,added_by);
        post.setId(id );
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(post);
        
      
        if(violations.isEmpty()) {
        	postService.updatePost(post);
            return "redirect:/posts";
        } else {
            post = postService.getPostById(post.getId());
            model.addAttribute("post", post);
            model.addAttribute("errors", violations);
            return "posts/editPost";
        }
    }
    
    @GetMapping("posts/postDetails")
    public String displayDetailsPost(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "posts/postDetails";
    }
}
