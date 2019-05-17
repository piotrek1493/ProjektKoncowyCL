package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Flat;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByFlatIdOrderByCreatedAsc(Long id);

}
