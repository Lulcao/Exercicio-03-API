package Repository;

import Model.Respostas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostasRepository extends JpaRepository<Respostas,String> {
    List<Respostas> findByQuestionarioId(Long questionarioId);
}
