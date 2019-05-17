package pl.coderslab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import pl.coderslab.entity.Photo;


public interface PhotoRepository extends JpaRepository<Photo, Long> {

	List<Photo> findByFlatId(Long id);
	
}
