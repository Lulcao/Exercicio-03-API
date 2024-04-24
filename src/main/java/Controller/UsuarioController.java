package Controller;

import Model.Usuario;
import Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    // GET para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // POST para criar um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    // PUT para atualizar um usuário inteiro pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuario.setLogin(usuarioDetails.getLogin());
        usuario.setSenha(usuarioDetails.getSenha());
        final Usuario updatedUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
    }

    // PATCH para atualizar parcialmente um usuário pelo ID
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> partialUpdateUsuario(@PathVariable Long id, @RequestBody Map<String, String> changes) {
        Usuario usuario = usuarioRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        if(changes.containsKey("login")) {
            usuario.setLogin(changes.get("login"));
        }
        if(changes.containsKey("senha")) {
            usuario.setSenha(changes.get("senha"));
        }
        final Usuario updatedUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
    }

    // DELETE para deletar um usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(String.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

