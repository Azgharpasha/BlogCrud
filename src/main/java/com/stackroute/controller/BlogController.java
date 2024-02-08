package com.stackroute.controller;
 
import com.stackroute.domain.Blog;
import com.stackroute.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
/**
* RestController annotation is used to create Restful web services using Spring MVC
*/
 
/**
* RequestMapping annotation maps HTTP requests to handler methods
*/
@RestController
@RequestMapping("/api/blogs")
public class BlogController {
 
    private BlogService blogService;
 
    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
 
    /**
     * save a new blog
     */
    @PostMapping("/blog")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
    	  Blog savedBlog = blogService.saveBlog(blog);
          return new ResponseEntity<>(savedBlog, HttpStatus.CREATED); 
    }
 
    /**
     * retrieve all blogs
     */
    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs() {
    	List<Blog> blogs = blogService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
 
    /**
     * retrieve blog by id
     */
    @GetMapping("blog/{blogId}")
    public ResponseEntity<Blog> getBlogById(@PathVariable("blogId") int blogId) {
    	Blog blog = blogService.getBlogById(blogId);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }
 
    /**
     * delete blog by id
     */
    @DeleteMapping("blog/{blogId}")
    public ResponseEntity<Blog> getBlogAfterDeleting(@PathVariable("blogId") int blogId) {
    	blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
 
    /**
     * update blog
     */
    @PutMapping("blog")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) {
    	 Blog updatedBlog = blogService.updateBlog(blog);
         return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
    }
}