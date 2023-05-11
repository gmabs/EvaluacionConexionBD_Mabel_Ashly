package ni.edu.uca.sistematicopersistencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ni.edu.uca.sistematicopersistencia.data.database.BaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto
import ni.edu.uca.sistematicopersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var producto: EntityProducto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    private fun addProducto(){
        val nombre = binding.edNombre.text.toString()
        val precio = binding.edPrecio.text.toString()
        val existencia = binding.edExistencia.text.toString()

        lifecycleScope.launch {
            if (producto == null) {
                val producto = EntityProducto(nombre = nombre, precio = precio, existencia = existencia)
                BaseDatos(this@MainActivity).getproducto().insertarReg(producto)
                finish()
            } else {
                val prod = EntityProducto(nombre, precio, existencia)
                prod.id = producto?.id ?: 0
                BaseDatos(this@MainActivity).getproducto().insertarReg(producto)
                finish()
            }

        }
    }
    }
}
