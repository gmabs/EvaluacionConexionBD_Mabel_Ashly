package ni.edu.uca.sistematicopersistencia.evaluacionAsh

import androidx.room.*

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM tbl_usuario ORDER BY id ASC")
    fun getAllUserInfo(): List<UsuarioEntidad>?

    @Insert
    fun insertUser(primer: UsuarioEntidad?)

    @Delete
    fun deleteUser(primer: UsuarioEntidad?)

    @Update
    fun updateUser(primer: UsuarioEntidad?)

}