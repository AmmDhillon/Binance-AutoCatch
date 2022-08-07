package com.ammdhillon.autocatch.repo;

import com.ammdhillon.autocatch.model.entity_model.announcement.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    Optional<Announcement> findByMessageID(Long messageID);
}