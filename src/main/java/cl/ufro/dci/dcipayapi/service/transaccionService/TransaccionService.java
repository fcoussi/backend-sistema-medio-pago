package cl.ufro.dci.dcipayapi.service.transaccionService;

import cl.ufro.dci.dcipayapi.domain.EstadoTransaccion;
import cl.ufro.dci.dcipayapi.domain.Transaccion;
import cl.ufro.dci.dcipayapi.repository.EstadoTransaccionRepository;
import cl.ufro.dci.dcipayapi.repository.TransaccionRepository;
import cl.ufro.dci.dcipayapi.service.utils.TransaccionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransaccionService {

    @Autowired
    TransaccionRepository transaccionRepository;

    @Autowired
     EstadoTransaccionRepository estadoTransaccionRepository;

    public Transaccion crearTransaccion(Transaccion transaccion){
        double nuevoSaldoEmisor = transaccion.getCuentaEmisora().getCueSaldo()-transaccion.getTraMonto();
        double nuevoSaldoReceptor = transaccion.getCuentaReceptora().getCueSaldo() +transaccion.getTraMonto();
        transaccion.getCuentaReceptora().setCueSaldo(nuevoSaldoReceptor);
        transaccion.getCuentaEmisora().setCueSaldo(nuevoSaldoEmisor);
        return transaccionRepository.save(transaccion);
    }

    public Transaccion crearRecarga(Transaccion transaccion){
        double nuevoSaldoReceptor=transaccion.getCuentaReceptora().getCueSaldo() + 10000;
        transaccion.getCuentaReceptora().setCueSaldo(nuevoSaldoReceptor);
        return transaccionRepository.save(transaccion);

    }

    public Transaccion obtenerTransaccion(long transaccionId) {
        if (transaccionRepository.findById(transaccionId).isPresent()){
            return transaccionRepository.findById(transaccionId).get();
        }else{
            return null;
        }
    }

    public List<Transaccion> listarTransacciones(){
        return (List<Transaccion>) transaccionRepository.findAll();
    }

    public Transaccion actualizarTransaccionAprobada(Long id){
        Transaccion transaccion = transaccionRepository.findById(id).get();
        double nuevoSaldoEmisor = transaccion.getCuentaEmisora().getCueSaldo() - transaccion.getTraMonto();
        double nuevoSaldoReceptor = transaccion.getCuentaReceptora().getCueSaldo() + transaccion.getTraMonto();
        transaccion.getCuentaEmisora().setCueSaldo(nuevoSaldoEmisor);
        transaccion.getCuentaReceptora().setCueSaldo(nuevoSaldoReceptor);
        EstadoTransaccion nuevoEstado = estadoTransaccionRepository.findById(1).get();
        transaccion.setEstadoTransaccion(nuevoEstado);

        return transaccion;
    }

    public Transaccion actualizarTransaccionRechazada(Long id){
        Transaccion miTransaccion = transaccionRepository.findById(id).get();
        EstadoTransaccion nuevoEstado = estadoTransaccionRepository.findById(3).get();
        miTransaccion.setEstadoTransaccion(nuevoEstado);

        return miTransaccion;
    }

    public List<Transaccion> listarTransaccionesPendiente(){
        List<Transaccion> listTransacPend;
        List<Transaccion> transacciones = (List<Transaccion>) transaccionRepository.findAll();
        listTransacPend=transacciones.stream().filter(TransaccionUtils.filtroEstadoPend("Pendiente")).collect(Collectors.toList());
        return listTransacPend;
    }

    public List<Transaccion> listarTransaccionesAprobados(){
        List<Transaccion> listTransacAprob;
        List<Transaccion> transacciones = (List<Transaccion>) transaccionRepository.findAll();
        //SE HACE USO DE UNA CLASE UTIL PARA REALIZAR FILTRADO
        listTransacAprob = transacciones.stream().filter(TransaccionUtils::filtroTransaccion).collect(Collectors.toList());
        return listTransacAprob;
    }


    /* SERVICIOS ESTADO TRANSACCION */
    public EstadoTransaccion crearEstadoTransaccion(EstadoTransaccion estadoTransaccion){
        return estadoTransaccionRepository.save(estadoTransaccion);
    }

    public List<EstadoTransaccion> listarEstadoTransaccion(){
        return (List<EstadoTransaccion>) estadoTransaccionRepository.findAll();
    }

}
