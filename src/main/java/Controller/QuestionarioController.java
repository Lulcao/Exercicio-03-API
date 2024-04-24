package Controller;

import Model.Questionario;
import Model.Respostas;
import Repository.QuestionarioRepository;
import Repository.RespostasRepository;
import Service.QuestionarioService;
import Service.RespostasService;
import Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questionario")
public class QuestionarioController {

    private final QuestionarioService questionarioService;
    @Autowired
    private QuestionarioRepository questionarioRepository;
    @Autowired
    private RespostasRepository respostasRepository;
    private final RespostasService respostaService;
    private final UsuarioService usuarioService;

    // GET para listar todas as respostas de um questionário
    @GetMapping("/{id}/resposta")
    public ResponseEntity<List<Respostas>> getAllRespostasByQuestionarioId(@PathVariable Long id) {
        Questionario questionario = questionarioRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questionário não encontrado"));
        List<Respostas> respostas = respostasRepository.findByQuestionarioId(questionario.getId());
        return new ResponseEntity<>(respostas, HttpStatus.OK);
    }

    // POST para inserir as respostas a um questionário
    @PostMapping("/{id}/resposta")
    public ResponseEntity<Respostas> addRespostaToQuestionario(@PathVariable Long id, @RequestBody Respostas resposta) {
        Questionario questionario = questionarioRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questionário não encontrado"));
        resposta.setQuestionario(questionario);
        Respostas savedResposta = respostasRepository.save(resposta);
        return new ResponseEntity<>(savedResposta, HttpStatus.CREATED);
    }


    // GET para listar todos os questionários
    @GetMapping
    public ResponseEntity<List<Questionario>> getAllQuestionarios() {
        List<Questionario> questionarios = questionarioRepository.findAll();
        return new ResponseEntity<>(questionarios, HttpStatus.OK);
    }

    // POST para criar um novo questionário
    @PostMapping
    public ResponseEntity<Questionario> createQuestionario(@RequestBody Questionario questionario) {
        questionario.setDataCriacao(new Date());
        Questionario savedQuestionario = questionarioRepository.save(questionario);
        return new ResponseEntity<>(savedQuestionario, HttpStatus.CREATED);
    }

    // PUT para atualizar um questionário inteiro pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Questionario> updateQuestionario(@PathVariable Long id, @RequestBody Questionario questionarioDetails) {
        Questionario questionario = questionarioRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questionário não encontrado"));
        questionario.setNome(questionarioDetails.getNome());
        questionario.setDescricao(questionarioDetails.getDescricao());
        final Questionario updatedQuestionario = questionarioRepository.save(questionario);
        return new ResponseEntity<>(updatedQuestionario, HttpStatus.OK);
    }

    // PATCH para atualizar parcialmente um questionário pelo ID
    @PatchMapping("/{id}")
    public ResponseEntity<Questionario> partialUpdateQuestionario(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Questionario questionario = questionarioRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Questionário não encontrado"));

        if(updates.containsKey("nome")) {
            questionario.setNome((String) updates.get("nome"));
        }
        if(updates.containsKey("descricao")) {
            questionario.setDescricao((String) updates.get("descricao"));
        }
        final Questionario updatedQuestionario = questionarioRepository.save(questionario);
        return new ResponseEntity<>(updatedQuestionario, HttpStatus.OK);
    }

    // DELETE para deletar um questionário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteQuestionario(@PathVariable Long id) {
        questionarioRepository.deleteById(String.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


