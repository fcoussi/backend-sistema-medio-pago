package cl.ufro.dci.dcipayapi.service.utils;

import cl.ufro.dci.dcipayapi.domain.Transaccion;

import java.util.function.Predicate;

/**
 * CLASE QUE PERMITE REUTILIZAR LOS FILTROS LAMBDA
 */

public class TransaccionUtils {

    /**
     * MÉTODO ENCARGADO DE HACER FILTRO SEGÚN EL ESTADO PASADO POR PARÁMETRO
     * @param estado
     * @return
     */
    public static Predicate <Transaccion> filtroEstadoPend(String estado){
        return (Transaccion t) -> t.getEstadoTransaccion().getEttEstado().equals(estado);
    }

    /**
     * MÉTODO ENCARGADO DE FILTRAR TRANSACCIONES APROBADAS Y RECHAZADAS
     * @param transaccion
     * @return
     */
    public static boolean filtroTransaccion(Transaccion transaccion){
        Predicate <Transaccion> t1 = (Transaccion t) -> t.getEstadoTransaccion().getEttEstado().equals("Aprobado");
        Predicate <Transaccion> t2 = (Transaccion t) -> t.getEstadoTransaccion().getEttEstado().equals("Rechazado");
        Predicate <Transaccion> tTotal = t1.or(t2);
        return tTotal.test(transaccion);
    }
}
