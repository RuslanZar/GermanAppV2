package RuslanCode.germanAppV2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "phrases")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "phrasesSeq", sequenceName = "phrases_SEQ", allocationSize = 1)
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "phrasesSeq")
    @Column(name = "id")
    private Long id;
    @Column(name = "russian")
    private String russian;
    @Column(name = "german")
    private String german;
    @Column(name = "score")
    private int score;

    public Phrase(String russian, String german, int score) {
        this.russian = russian;
        this.german = german;
        this.score = score;
    }
}
