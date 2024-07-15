package com.ForoHub.ForoHubChallenge.model.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<TopicEntity, Long> {
    Page<TopicEntity> findAllByStatusTrueOrderByCreationDateAsc(Pageable pageable);
    @Query("SELECT t FROM TopicEntity t WHERE LOWER(REPLACE(t.course.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:courseName, ' ', ''), '%'))")
    Page<TopicEntity> findAllByCourseNameIgnoreCase(@Param("courseName") String courseName, Pageable pageable);

    boolean existsByTitleAndStatus(String title, Boolean status);
}
