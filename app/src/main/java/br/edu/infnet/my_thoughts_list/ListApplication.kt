package br.edu.infnet.my_thoughts_list

import android.app.Application
import br.edu.infnet.my_thoughts_list.model.AppDatabase
import br.edu.infnet.my_thoughts_list.model.ListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ListApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ListRepository(database.listDao()) }
}