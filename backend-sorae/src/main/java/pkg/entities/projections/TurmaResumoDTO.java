package pkg.entities.projections;

import java.time.LocalDateTime;

public interface TurmaResumoDTO {
	
	String getCodigo();
	String getNome();
	String getSala();
	LocalDateTime getHorario();

}
