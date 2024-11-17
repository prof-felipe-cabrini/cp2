package com.example.projeto.service;
import com.example.projeto.dto.ServicoDTO;
import com.example.projeto.model.Servico;
import com.example.projeto.repository.PedidoServicoRepository;
import com.example.projeto.repository.ServicoRepository;
import com.example.projeto.service.ServiceInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private PedidoServicoRepository pedidoServicoRepository;

    @Transactional
    public ServicoDTO createServico(ServicoDTO servicoDTO) {
        Servico servico = new Servico(servicoDTO.getDescricao(), servicoDTO.getPreco());
        return convertToDTO(servicoRepository.save(servico));
    }

    public Page<ServicoDTO> getAllServicos(Pageable pageable) {
        return servicoRepository.findAll(pageable).map(this::convertToDTO);
    }

    @Transactional
    public void deleteServico(Long servicoId) throws ServiceInUseException {
        // Verifica se o serviço está associado a algum pedido de serviço
        if (pedidoServicoRepository.existsByServicos_Id(servicoId)) {
            throw new ServiceInUseException("Cannot delete: Service is linked to an order.");
        }
        servicoRepository.deleteById(servicoId);
    }

    private ServicoDTO convertToDTO(Servico servico) {
        return new ServicoDTO(servico.getId(), servico.getDescricao(), servico.getPreco());
    }
}