package kyj.area.teamprofile.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "images")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;

    @Column(length = 4096, nullable = false)
    private String imageKey;

    private Image(Member member, String imageKey) {
        this.member = member;
        this.imageKey = imageKey;
    }

    public static Image register(Member member, String imageKey) {
        return new Image(member, imageKey);
    }
}
