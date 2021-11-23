package com.example.genericdatastore.data_store_logic

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface IDataStorePreferenceAPI {
    suspend fun<T> getPreference(key: Preferences.Key<T>, defaultValue : T): Flow<T>
    suspend fun<T> putPreference(key: Preferences.Key<T>, value: T)
    suspend fun<T> removePreference(key: Preferences.Key<T>)
    suspend fun clearAllPreference()
}