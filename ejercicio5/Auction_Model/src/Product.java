import java.io.Serializable;

public class Product implements Serializable {
    public String nombreProducto;
    public String nombreComprador;
    public double precio;

    public Product(String nombreProducto, double precio) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", nombreComprador='" + nombreComprador + '\'' +
                ", precio=" + precio +
                '}';
    }
}
