package com.example.Veterinaria.Ferrel_Back.Domain.historialMedico;
import com.example.Veterinaria.Ferrel_Back.Domain.mascota.Mascota;
import com.example.Veterinaria.Ferrel_Back.Domain.mascota.MascotaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialMedicoService {

    private final HistorialMedicoRepository historialMedicoRepository;
    private final MascotaRepository mascotaRepository;

    public HistorialMedicoService(HistorialMedicoRepository historialMedicoRepository, MascotaRepository mascotaRepository) {
        this.historialMedicoRepository = historialMedicoRepository;
        this.mascotaRepository = mascotaRepository;
    }

    public DatosHistorialesPorMascota obtenerHistorialesPorMascota(Long idMascota) {
        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada"));

        List<HistorialMedico> historiales = historialMedicoRepository.findByMascota_Id(idMascota);
        List<DatosListadoHistorial> listaHistoriales = historiales.stream()
                .map(DatosListadoHistorial::new)
                .toList();

        return new DatosHistorialesPorMascota(mascota.getId(), mascota.getNombre(), listaHistoriales);
    }

    public DatosListadoHistorial obtenerHistorialPorId(Integer idHistorial) {
        HistorialMedico historial = historialMedicoRepository.findById(idHistorial)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));
        return new DatosListadoHistorial(historial);
    }

    @Transactional
    public DatosListadoHistorial registrarHistorial(Long id, DatosRegistrarHistorial datos) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mascota no encontrada"));

        HistorialMedico historial = new HistorialMedico();
        historial.setMascota(mascota);
        historial.setTipoconsulta(datos.tipoconsulta());
        historial.setMotivo(datos.motivo());
        historial.setTemperatura(datos.temperatura());
        historial.setResumen(datos.resumen());
        historial.setReceta(datos.receta());
        historial.setFecha(datos.fecha());

        historialMedicoRepository.save(historial);
        return new DatosListadoHistorial(historial);
    }

    @Transactional
    public DatosListadoHistorial actualizarHistorial(Integer id, DatosActualizarHistorial datos) {
        HistorialMedico historial = historialMedicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado"));

        if (datos.tipoconsulta() != null) historial.setTipoconsulta(datos.tipoconsulta());
        if (datos.motivo() != null) historial.setMotivo(datos.motivo());
        if (datos.temperatura() != null) historial.setTemperatura(datos.temperatura());
        if (datos.resumen() != null) historial.setResumen(datos.resumen());
        if (datos.receta() != null) historial.setReceta(datos.receta());
        if (datos.fecha() != null) historial.setFecha(datos.fecha());
        historialMedicoRepository.save(historial);
        return new DatosListadoHistorial(historial);
    }

    @Transactional
    public void eliminarHistorial(Integer idHistorial) {
        if (!historialMedicoRepository.existsById(idHistorial)) {
            throw new EntityNotFoundException("Historial no encontrado");
        }
        historialMedicoRepository.deleteById(idHistorial);
    }
}
