package interviewluty2020.listaprzebojow.repository;

import interviewluty2020.listaprzebojow.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>
{
}
