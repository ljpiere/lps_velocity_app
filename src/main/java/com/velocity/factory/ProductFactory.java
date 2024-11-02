package com.velocity.factory;
// config
import com.velocity.config.ConfigManager;
// linea
import com.velocity.linea.VamosEnBici;
import com.velocity.linea.PuntoBike;
import com.velocity.linea.TrainingHub;
// gestionUsuarios
import com.velocity.features.gestionUsuarios.RegistroCiclistas;
import com.velocity.features.gestionUsuarios.PerfilesUsuario;
import com.velocity.features.gestionUsuarios.GestionRoles;
import com.velocity.features.gestionUsuarios.GestionClubes;
import com.velocity.features.gestionUsuarios.GestionMembresia;
import com.velocity.features.gestionUsuarios.GestionPlanes;
// serviciosTerceros
import com.velocity.features.serviciosTerceros.ServiciosTerceros;
// gestionEventos
import com.velocity.features.gestionEventos.CreacionEventos;
import com.velocity.features.gestionEventos.GestionCompetidores;
import com.velocity.features.gestionEventos.PlanificacionRutas;
// gestionBicicletas
import com.velocity.features.gestionBicicletas.RegistroBicicletas;
import com.velocity.features.gestionBicicletas.MantenimientoReparacion;
import com.velocity.features.gestionBicicletas.SistemaAlquiler;
// caracteristicas sociales
import com.velocity.features.caracteristicasSociales.Compartir;
import com.velocity.features.caracteristicasSociales.RedSocial;
import com.velocity.features.caracteristicasSociales.Foros;
// analisisReportes
import com.velocity.features.analisisReportes.AnalisisRendimiento;
import com.velocity.features.analisisReportes.RegistroEntrena;
import com.velocity.features.analisisReportes.ReportesGobierno;
import com.velocity.features.analisisReportes.EstadisticasUso;
// Done: TO DO - Importar otras características necesario

public class ProductFactory {
    private ConfigManager configManager;

    public ProductFactory(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public VamosEnBici createVamosEnBici() {
        return new VamosEnBici(configManager); // Aquí pasamos configManager
    }

    public PuntoBike createPuntoBike() {
        return new PuntoBike(configManager); // Asegúrar de que todas las clases tengan el constructor que recibe ConfigManager
    }

    public TrainingHub createTrainingHub() {
        return new TrainingHub(configManager);
    }
}
