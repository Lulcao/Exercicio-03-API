package Repository;

import Model.Perguntas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntasRepository extends JpaRepository <Perguntas, String> {
}
