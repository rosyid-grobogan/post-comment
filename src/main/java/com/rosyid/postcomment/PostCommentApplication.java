package com.rosyid.postcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PostCommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostCommentApplication.class, args);
	}

}
