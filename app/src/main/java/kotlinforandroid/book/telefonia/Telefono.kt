package kotlinforandroid.book.telefonia

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Telefono")
class Telefono(
    val nombre:String,
    val imagen: String,
    val pantalla:String,
    val procesador:String,
    val almacenamiento:String,
    val bateria:String,
    val comentario:String,
    @PrimaryKey(autoGenerate = true)
    var idTelefono:Int =0,):Serializable