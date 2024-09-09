package RuslanCode.germanAppV2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user_noun_links")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "userNounSeq", sequenceName = "user_noun_SEQ", allocationSize = 1)
public class UserNounLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userNounSeq")
    @Column(name = "id")
    Long id;
    @Column(name = "userID")
    private Long userID;
    @Column(name = "nounID")
    private Long nounID;
    @Column(name = "score")
    private double score;
}
