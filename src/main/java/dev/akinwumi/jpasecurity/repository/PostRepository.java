package dev.akinwumi.jpasecurity.repository;

import dev.akinwumi.jpasecurity.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {
}
