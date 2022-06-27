package br.edu.infnet.my_thoughts_list.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [ListEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listDao(): ListDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(ThoughtDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class ThoughtDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.listDao())
                    }
                }
            }

        }

        suspend fun populateDatabase(listDao: ListDao) {
            listDao.deleteAll()
            var listEntity = ListEntity("oi")
            listDao.insert(listEntity)
            listEntity = ListEntity("olá")
            listDao.insert(listEntity)
            listEntity = ListEntity("tudo bem?")
            listDao.insert(listEntity)


        }


    }
}
