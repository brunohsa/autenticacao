package br.com.unip.autenticacao.service

import br.com.unip.autenticacao.repository.IPerfilRepository
import br.com.unip.autenticacao.repository.entity.Perfil
import org.springframework.stereotype.Service

@Service
class PerfilService(val perfilRepository: IPerfilRepository) : IPerfilService {

    override fun buscarPorNome(nome: String): Perfil? {
        return perfilRepository.buscarPorNome(nome)
    }
}