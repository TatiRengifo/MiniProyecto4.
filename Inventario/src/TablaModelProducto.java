import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TablaModelProducto extends AbstractTableModel {
    private String[] columnas = { "Codigo", "Nombre", "Precio", "Inventario" };
    List<Producto> producto = new ArrayList<>();

    public TablaModelProducto(List<Producto> prod) {
        this.producto = prod;
    }

    @Override
    public int getRowCount() {
        return this.producto.size();

    }

    @Override
    public int getColumnCount() {
        return this.columnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Object resp;

        switch (columna) {
            case 0:
                resp = this.producto.get(fila).getCodigo();
                break;

            case 1:
                resp = this.producto.get(fila).getNombre();
                break;
            case 2:
                resp = this.producto.get(fila).getPrecio();
                break;
            default:
                resp = this.producto.get(fila).getInventario();
                break;

        }
        return resp;
    }

    public String getColumnName(int colum) {
        return this.columnas[colum];
    }

    public void actualizarTabla() {
        fireTableDataChanged();
    }

    public Producto detalle(int fila) {
        return this.producto.get(fila);
    }

}
