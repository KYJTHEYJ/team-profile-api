package kyj.area.teamprofile.domain.member.repository;

import kyj.area.teamprofile.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findById(Long memberId);
}
