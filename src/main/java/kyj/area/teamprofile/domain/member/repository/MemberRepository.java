package kyj.area.teamprofile.domain.member.repository;

import kyj.area.teamprofile.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
