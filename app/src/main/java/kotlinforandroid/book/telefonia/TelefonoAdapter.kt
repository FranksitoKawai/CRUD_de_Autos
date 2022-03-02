package kotlinforandroid.book.telefonia

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.item_telefono.view.*

class TelefonoAdapter(private val mContext: Context, private val listaTelefono:List<Telefono>): ArrayAdapter<Telefono>(mContext,0,listaTelefono){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.item_telefono, parent, false)
        val tel=listaTelefono[position]
        layout.nombre.text = tel.nombre
        layout.pantalla.text = tel.pantalla
        layout.procesador.text = tel.procesador
        layout.almacenamiento.text = tel.almacenamiento

        if (tel.imagen!=""){
            layout.imagen.setImageURI(tel.imagen.toUri())
        }

        return layout
    }
}