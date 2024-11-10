package com.pet.cafe.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "socket_session")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocketSession {
    @Id
    @Column(name = "session_id")
    String sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;
}
