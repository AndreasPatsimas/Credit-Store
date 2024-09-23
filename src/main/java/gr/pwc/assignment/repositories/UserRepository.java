package gr.pwc.assignment.repositories;

import gr.pwc.assignment.domain.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserBalance, UUID> {
}
