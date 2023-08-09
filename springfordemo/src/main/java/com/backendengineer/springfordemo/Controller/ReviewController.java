package com.backendengineer.springfordemo.Controller;

import com.backendengineer.springfordemo.Entity.Review;
import com.backendengineer.springfordemo.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/post")
    public ResponseEntity<Review> postreview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.postreview(review));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Review>> getreviewbyid(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.getreviewbyid(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Review> updatebyid(@PathVariable("id") Long id, @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.updatebyid(id, review));
    }

}
