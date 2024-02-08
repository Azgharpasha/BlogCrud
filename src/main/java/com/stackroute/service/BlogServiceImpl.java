package com.stackroute.service;
 
import com.stackroute.domain.Blog;
 
import com.stackroute.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
 
/**
* @Service indicates annotated class is a service which hold business logic in the Service layer
*/
@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;
 
    /**
     * Constructor based Dependency injection to inject BlogRepository here
     */
    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    /**
     * save a new blog
     */
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }
 
    /**
     * retrieve all blogs
     */
    @Override
    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }
 
    /**
     * retrieve blog by id
     */
    @Override
    public Blog getBlogById(int blogId) {
        Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        return optionalBlog.orElse(null);
    }
 
    /**
     * delete blog by id
     * 
     */
    @Override
    public Blog deleteBlog(int blogId) {
    	Optional<Blog> optionalBlog = blogRepository.findById(blogId);
        if (optionalBlog.isPresent()) {
            Blog blogToDelete = optionalBlog.get();
            blogRepository.delete(blogToDelete);
            return blogToDelete;
        } else {
            return null; // Blog with the given ID not found
        }
    }
 
    /**
     * update blog
     * 
     */
    @Override
    public Blog updateBlog(Blog blog) {
        // Check if the blog exists before updating
        Optional<Blog> optionalBlog = blogRepository.findById(blog.getBlogId());
        if (optionalBlog.isPresent()) {
            // Update the existing blog
            Blog existingBlog = optionalBlog.get();
            existingBlog.setBlogTitle(blog.getBlogTitle());
            existingBlog.setBlogContent(blog.getBlogContent());
            // Set any other fields you want to update
            return blogRepository.save(existingBlog);
        } else {
            // Blog with the given ID not found
            return null;
        }
    }
}