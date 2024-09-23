package gr.pwc.assignment.repositories;

import gr.pwc.assignment.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Short> {
}
