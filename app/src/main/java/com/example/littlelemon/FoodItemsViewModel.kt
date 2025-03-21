package com.example.littlelemon

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodItemsViewModel(application: Application) : AndroidViewModel(application) {
    private val database by lazy {
        Room.databaseBuilder(application, MenuDatabase::class.java, "database").build()
    }

    fun getAllItems(): LiveData<List<MenuItem>> {
        return database.menuDao().getAllMenuItems()
    }

    fun getMenuData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                saveMenuData(
                    database,
                    fetchData("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                )
            }
        }
    }
}

suspend fun fetchData(url: String): List<MenuItemNetwork> {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    val response: MenuNetworkData = client.get(url).body()
    return response.items
}

fun saveMenuData(database: MenuDatabase, menuItemNetwork: List<MenuItemNetwork>) {
    val menuItems = menuItemNetwork.map { it.toMenuItem() }
    database.menuDao().insertAll(*menuItems.toTypedArray())
}