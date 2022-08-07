package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.model.entity_model.announcement.Announcement;
import com.ammdhillon.autocatch.repo.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repo;

    public void saveAnnouncement(Long id, String text) {
        Announcement announcement = new Announcement();

        announcement.setMessageID(id);
        announcement.setMessageText(text);
        announcement.setPostDate(System.currentTimeMillis());

        repo.save(announcement);
    }
}