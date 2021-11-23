package com.example.genericdatastore.data_store_logic

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class DataStoreSetting
@Inject
constructor(private val context: Context) : IDataStorePreferenceAPI {

    companion object{
        private const val DATA_STORE_NAME = "DataStore"
        val BOOLEAN_KEY = booleanPreferencesKey("BOOLEAN_KEY")
        val INT_KEY = intPreferencesKey("INT_KEY")
        val STRING_KEY = stringPreferencesKey("STRING_KEY")
        val LONG_KEY = longPreferencesKey("LONG_KEY")
    }
    private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)
    private val dataSource = context.dataStore

    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataSource.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw  exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataSource.edit {
            it.remove(key)
        }
    }

    override suspend fun clearAllPreference() {
        dataSource.edit { preferences ->
            preferences.clear()
        }
    }

}