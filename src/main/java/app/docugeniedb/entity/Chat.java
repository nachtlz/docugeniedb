package app.docugeniedb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "mode")
    private String mode;

    @Column(name = "header")
    private String header;

    @Column(name = "color")
    private String color;

    @Column(name = "sender_color")
    private String senderColor;

    @Column(name = "receiver_color")
    private String receiverColor;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "external_color")
    private String externalColor;

    @Column(name = "orientation")
    private String orientation;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private List<Message> messages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToMany
    @JoinTable(
            name = "File_Chat",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "file_id")
    )
    private Set<File> files;
}
