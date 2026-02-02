package kyj.area.teamprofile.domain.member.repository;

import kyj.area.teamprofile.domain.member.entity.Image;
import kyj.area.teamprofile.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByMember(Member member);

    long countByMember(Member member);
}
