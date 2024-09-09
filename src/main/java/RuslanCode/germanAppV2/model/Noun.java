package RuslanCode.germanAppV2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "nouns")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(name = "nounsSeq", sequenceName = "nouns_SEQ", allocationSize = 1)
public class Noun {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "nounsSeq")
    @Column(name = "id")
    private Long id;
    @Column(name = "russian")
    private String russian;
    @Column(name = "german")
    private String german;
    @Column(name = "plural")
    private String plural;

    public Noun(String russian, String german, String plural) {
        this.russian = russian;
        this.german = german;
        this.plural = plural;
    }
}
