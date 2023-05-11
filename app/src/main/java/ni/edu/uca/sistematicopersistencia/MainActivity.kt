package ni.edu.uca.sistematicopersistencia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ni.edu.uca.sistematicopersistencia.data.database.BaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto
import ni.edu.uca.sistematicopersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var producto: EntityProducto? = null
    lateinit var db: BaseDatos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            BaseDatos::class.java, "basedatos"
        ).build()

        binding.btnAdd.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            val nombre = binding.edNombre.text.toString()
            val precio = binding.edPrecio.text.toString().toDouble()
            val existencia = binding.edExistencia.text.toString().toInt()

            val producto = EntityProducto(null, nombre, precio, existencia)

            GlobalScope.launch {
                db.getProducto().insertarReg(producto)
            }
        }

    }
}
