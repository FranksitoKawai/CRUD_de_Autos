package kotlinforandroid.book.telefonia
import android.net.Uri
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_telefono.*
import kotlinx.android.synthetic.main.item_telefono.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelefonoActivity : AppCompatActivity() {
    val SEARCH_PREFIX = "https://www.google.com/search?q="
    private lateinit var dataBase: AppDataBase

    private lateinit var telefono: Telefono
    private lateinit var telefonoLiveData: LiveData<Telefono>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono)

        dataBase = AppDataBase.getDatabase(this)

        val idAuto = intent.getIntExtra("id",0)

        telefonoLiveData = dataBase.telefonos().get(idAuto)
        telefonoLiveData.observe(this, Observer{
            telefono = it
            _nombre.text = telefono.nombre
            _pantalla.text = telefono.pantalla
            _procesador.text = telefono.procesador
            _Almacenamiento.text = telefono.almacenamiento
            _bateria.text = telefono.bateria
            _comentario.text = telefono.comentario
            button.setOnClickListener{
                val queryUrl: Uri = Uri.parse("${SEARCH_PREFIX}${telefono.nombre}")
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                this.startActivity(intent)
            }

            if (telefono.imagen!=""){
                imagen_auto.setImageURI(telefono.imagen.toUri())
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.auto_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.edit_item->{
                val intent = Intent(this, NuevoTelefonoActivity::class.java)
                intent.putExtra("Telefono",telefono)
                startActivity(intent)
            }

            R.id.delete_item->{
                telefonoLiveData.removeObservers(this)
                CoroutineScope(Dispatchers.IO).launch {
                    dataBase.telefonos().delete(telefono)
                    this@TelefonoActivity.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}