package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findByReceiverOrderByCreatedDesc(User receiver);

	List<Message> findBySenderOrderByCreatedDesc(User sender);

	List<Message> findByReceiverAndCheckedLikeOrderByCreatedDesc(User receiver, int checked);

}
