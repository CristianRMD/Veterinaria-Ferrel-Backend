package com.example.Veterinaria.Ferrel_Back.Controller;

import com.example.Veterinaria.Ferrel_Back.Domain.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/register")
    public ResponseEntity <DatosRespuestaCliente> RegistrarCliente(@RequestBody @Valid DatosRegistroCliente datosRegistroCliente
    , UriComponentsBuilder uriComponentsBuilder){
        System.out.println(datosRegistroCliente.dni());
    Cliente cliente = clienteRepository.save(new Cliente(datosRegistroCliente));
        System.out.println(cliente);
    DatosRespuestaCliente datosRespuestaCliente = new DatosRespuestaCliente(cliente.getId(),cliente.getNombre(),cliente.getApellido(),cliente.getDni());
        URI url =uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

    return ResponseEntity.created(url).body(datosRespuestaCliente) ;
    }


    @GetMapping("/details")
    public ResponseEntity<Page<DatosListadoCliente>> listaClientes(Pageable paginacion){

       return ResponseEntity.ok(clienteRepository.findAll(paginacion).map(DatosListadoCliente::new));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaCliente> eliminarCliente (@PathVariable Long id){
    Cliente cliente = clienteRepository.getReferenceById(id);
    cliente.desacticarCliente();
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/edit/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaCliente> actualizarCliente (@PathVariable Long id ,@RequestBody @Valid DatosActualizarCliente datosActualizarCliente){
    var cliente = clienteRepository.getReferenceById(id);
       cliente.actualizarDatos(datosActualizarCliente);
       var datosRespuestaCliente = new DatosRespuestaCliente(cliente);
    return ResponseEntity.ok(datosRespuestaCliente);
    }

@GetMapping ("/{id}")
    public ResponseEntity<DatosRespuestaCliente> retornarDatosCliente(@PathVariable Long id){

        Cliente cliente =    clienteRepository.getReferenceById(id);
        var datosRespuestaCliente = new DatosRespuestaCliente(cliente);
        return ResponseEntity.ok(datosRespuestaCliente);
    }

@GetMapping("/mascotas/{dni}")
    public ResponseEntity retornarListaMascotas(){
        //paginado de mascotas del respectivo usuario

        return null;
}


}
