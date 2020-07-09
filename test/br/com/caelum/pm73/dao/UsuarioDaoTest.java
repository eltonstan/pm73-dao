package br.com.caelum.pm73.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.junit.Test;

import br.com.caelum.pm73.dominio.Usuario;

public class UsuarioDaoTest {
	
    @Test
    public void deveEncontrarPeloNomeEEmail() {
        Session session = new CriadorDeSessao().getSession();
        UsuarioDao usuarioDao = new UsuarioDao(session);

        // criando um usuario e salvando antes
        // de invocar o porNomeEEmail
        Usuario novoUsuario = new Usuario
                ("Jo�o da Silva", "joao@dasilva.com.br");
        usuarioDao.salvar(novoUsuario);

        // agora buscamos no banco
        Usuario usuarioDoBanco = usuarioDao
                .porNomeEEmail("Jo�o da Silva", "joao@dasilva.com.br");

        assertEquals("Jo�o da Silva", usuarioDoBanco.getNome());
        assertEquals("joao@dasilva.com.br", usuarioDoBanco.getEmail());

        session.close();
    }
    
    @Test
    public void deveRetornarNuloSeNaoEncontrarUsuario() {
        Session session = new CriadorDeSessao().getSession();
        UsuarioDao usuarioDao = new UsuarioDao(session);

        Usuario usuarioDoBanco = usuarioDao
                .porNomeEEmail("Jo�o Joaquim", "joao@joaquim.com.br");

        assertNull(usuarioDoBanco);

        session.close();
    }

}
