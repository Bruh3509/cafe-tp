package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    long messageId;

    @Column(name = "sent_at", nullable = false, columnDefinition = "timestamp default current_timestamp")
    Timestamp sentAt;

    @Column(name = "text", length = 1000, nullable = false)
    String text;

    @Lob
    @Column(name = "file")
    byte[] fileData;

    @Column(name = "file_name", length = 100)
    String fileName;

    @Column(name = "file_type", length = 100)
    String fileType;

    public Message(User sender, User recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }

    public Message(User sender, User recipient, String text, byte[] file, String fileType, String fileName) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.fileData = file;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    User recipient;
}
