package kotlinforandroid.book.telefonia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaTelefonos = emptyList<Telefono>()

        val dataBase = AppDataBase.getDatabase(this)

        dataBase.telefonos().getAll().observe(this, Observer {
            listaTelefonos = it
            val adapter = TelefonoAdapter(mContext = this,listaTelefonos)
            lista.adapter = adapter
        })

        floatingActionButton.setOnClickListener{
            val intent = Intent(this,NuevoTelefonoActivity::class.java)
            startActivity(intent)
        }

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,TelefonoActivity::class.java)
            intent.putExtra("id",listaTelefonos[position].idTelefono)
            startActivity(intent)
        }
    }
}