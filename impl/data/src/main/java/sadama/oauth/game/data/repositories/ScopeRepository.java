package sadama.oauth.game.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sadama.oauth.game.data.Scope;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Long> {
}
