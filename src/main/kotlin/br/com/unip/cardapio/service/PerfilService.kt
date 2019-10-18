package br.com.unip.cardapio.service

import br.com.unip.cardapio.repository.IPerfilRepository
import br.com.unip.cardapio.repository.entity.Perfil
import org.springframework.stereotype.Service

@Service
class PerfilService(val perfilRepository: IPerfilRepository) : IPerfilService {

    override fun buscarPorNome(nome: String): Perfil? {
        return perfilRepository.buscarPorNome(nome)
    }
}