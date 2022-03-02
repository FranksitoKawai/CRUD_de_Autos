package kotlinforandroid.book.telefonia

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TelefonoDao {
    @Query(value = "Select * from telefono")
    fun getAll(): LiveData<List<Telefono>>
    @Query(value = "Select * from telefono where idTelefono=:id")
    fun get(id:Int):LiveData<Telefono>
    @Insert
    fun insertAll(vararg telefono:Telefono)
    @Update
    fun update(telefono: Telefono)
    @Delete
    fun delete(telefono: Telefono)
}