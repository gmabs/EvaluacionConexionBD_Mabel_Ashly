package ni.edu.uca.sistematicopersistencia

import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto

class ProductoAdapter {
    private var list = mutableListOf<EntityProducto>()

    override fun getProducto() = list.size

    fun setData(data: List<EntityProducto>) {
        list.apply {
            clear()
            addAll(data)
        }

    }
}