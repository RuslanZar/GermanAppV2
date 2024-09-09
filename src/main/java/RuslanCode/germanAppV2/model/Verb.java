package RuslanCode.germanAppV2.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Entity
@Table(name = "verbs")
@Data
@RequiredArgsConstructor
@SequenceGenerator(name = "verbsSeq", sequenceName = "verbs_SEQ", allocationSize = 1)
public class Verb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "verbsSeq")
    @Column(name = "id")
    private Long id;
    @Column(name = "russian")
    private String russian;
    @Column(name = "infinitiv/Wir/Sie/sie")
    private String infinitiv;
    @Column(name = "prateritum")
    private String prateritum;
    @Column(name = "partizip2")
    private String partizip2;
    @Column(name = "ich")
    private String ich;
    @Column(name = "du")
    private String du;
    @Column(name = "er/sie/es")
    private String er_sie_es;
    @Column(name = "ihr")
    private String ihr;
//    @Column(name = "Sie/sie")
//    private String sie_Sie;
    @Column(name = "score")
    private int score;

    public Verb(@Nullable Long id,
                String russian,
                String infinitiv,
                @Nullable String prateritum,
                @Nullable String partizip2,
                @Nullable String ich,
                @Nullable String du,
                @Nullable String er_sie_es,
                @Nullable String ihr,
//                @Nullable String sie_Sie,
                int score) {
        this.id = id;
        this.russian = russian;
        this.infinitiv = infinitiv;
        this.prateritum = prateritum;
        this.partizip2 = partizip2;
        this.ich = ich;
        this.du = du;
        this.er_sie_es = er_sie_es;
        this.ihr = ihr;
//        this.sie_Sie = sie_Sie;
        this.score = score;
    }
}
