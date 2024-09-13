package RuslanCode.germanAppV2.model.phrase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user_phrase_links")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "userPhraseSeq", sequenceName = "user_phrase_SEQ", allocationSize = 1)
public class UserPhraseLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userPhraseSeq")
    @Column(name = "id")
    Long id;
    @Column(name = "userID")
    private Long userID;
    @Column(name = "phraseID")
    private Long phraseID;
}
