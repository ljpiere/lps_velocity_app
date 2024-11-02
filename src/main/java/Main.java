import com.velocity.config.ConfigManager;
import com.velocity.factory.ProductFactory;

public class Main {
    public static void main(String[] args) {
        ConfigManager configManager = new ConfigManager();
        ProductFactory productFactory = new ProductFactory(configManager);

        // Crear una instancia de VamosEnBici
        var vamosEnBici = productFactory.createVamosEnBici();
        vamosEnBici.startFeature();
    }
}
