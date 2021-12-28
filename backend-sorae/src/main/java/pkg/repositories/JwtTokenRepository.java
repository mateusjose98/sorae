package pkg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pkg.entities.JwtToken;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, String> {

}
