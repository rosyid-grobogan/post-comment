package com.rosyid.postcomment.controller;

import com.rosyid.postcomment.exception.ResourceNotFoundException;
import com.rosyid.postcomment.model.Post;
import com.rosyid.postcomment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostRepository postRepo;

    @GetMapping
    public Page<Post> getAlPosts(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @PostMapping
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepo.save(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post request) {
        return postRepo.findById(postId)
                .map(post -> {
                    post.setTitle(request.getTitle());
                    post.setDescription(request.getDescription());
                    post.setContent(request.getContent());

                    return  postRepo.save(post);
                }).orElseThrow(() -> new ResourceNotFoundException("PostId" + postId + " not found.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepo.findById(postId)
                .map(post -> {
                    postRepo.delete(post);

                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found.");
    }
}
