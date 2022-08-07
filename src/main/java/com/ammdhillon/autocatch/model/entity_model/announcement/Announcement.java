package com.ammdhillon.autocatch.model.entity_model.announcement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "announcement")
@IdClass(AnnouncementID.class)
public class Announcement {

    @Id
    @SequenceGenerator(sequenceName = "announcement_sequence", allocationSize = 1, name = "announcement_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "announcement_sequence")
    private Long id;
    @Id
    @Column(unique = true)
    private Long messageID;
    private String messageText;
    private Long postDate;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
}
