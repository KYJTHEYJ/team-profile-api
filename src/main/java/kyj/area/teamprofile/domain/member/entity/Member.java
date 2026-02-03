package kyj.area.teamprofile.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String name;

    @Column(length = 3, nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private Mbti mbti;

    private Member(String name, Integer age, Mbti mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

    public static Member register(String name, Integer age, Mbti mbti) {
        return new Member(name, age, mbti);
    }
}
