package RuslanCode.germanAppV2.model.verb;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user_verb_links")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "userVerbSeq", sequenceName = "user_verb_SEQ", allocationSize = 1)
public class UserVerbLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userVerbSeq")
    @Column(name = "id")
    Long id;
    @Column(name = "userID")
    private Long userID;
    @Column(name = "verbID")
    private Long verbID;
}
