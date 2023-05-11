package ni.edu.uca.sistematicopersistencia.evaluacionAsh

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UsuarioEntidad::class], version = 3)
abstract class UsuarioDatabase: RoomDatabase() {


    abstract fun UsuarioDao(): UsuarioDao?

    companion object {
        private var INSTANCE: UsuarioDatabase?= null

        val migration_1_2: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE tbl_usuario ADD COLUMN phone TEXT DEFAULT ''")
            }
        }
        fun getAppDatabase(context: Context): UsuarioDatabase? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<UsuarioDatabase>(
                    context.applicationContext, UsuarioDatabase::class.java, "AppDBB"
                )
                    .addMigrations(migration_1_2)
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE

        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}