package gr.pwc.assignment.repositories;

import gr.pwc.assignment.domain.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffMemberRepository extends JpaRepository<StaffMember, Integer> {

    Optional<StaffMember> findByUserName(String userName);
}
