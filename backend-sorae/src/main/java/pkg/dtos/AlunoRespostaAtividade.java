package pkg.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AlunoRespostaAtividade {
	
	 private Long idAtividade;
     private Long idAluno;
     private String resposta;
     
     

}
