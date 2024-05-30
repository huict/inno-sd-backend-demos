package nl.hu.inno.sqlexercises.orm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {

    List<Ranking> findById_Rankingdate(String rankingDate);
}
