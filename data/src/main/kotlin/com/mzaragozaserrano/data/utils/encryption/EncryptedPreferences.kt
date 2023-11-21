package com.mzaragozaserrano.data.utils.encryption

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import javax.inject.Inject

class EncryptedPreferences @Inject constructor(
    @ApplicationContext private val context: Context,
    private val encryptionServices: EncryptionServices,
) {

    fun deleteAllButResources() {
        deleteAll()
    }

    companion object {
        private const val APPLICATION_PREFERENCE_TAG = "ApplicationPreferences"
        private const val LOG_TAG = "PreferenceManager"
    }

    init {
        try {
            encryptionServices.createMasterKey()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Synchronized
    fun saveBoolean(tag: String, booleanValue: Boolean) {
        val booleanString: String = booleanValue.toString()
        saveForm(tag, booleanString)
    }

    @Synchronized
    fun getBoolean(tag: String, default: Boolean): Boolean {
        val settings =
            context.getSharedPreferences(APPLICATION_PREFERENCE_TAG, Context.MODE_PRIVATE)
        val value = settings.getString(tag, null)
        var finalValue: Boolean = default
        if (value != null) {
            finalValue = try {
                val decryptedValue = encryptionServices.decrypt(value)
                decryptedValue.toBoolean()
            } catch (e: Exception) {
                default
            }
        }
        return finalValue
    }

    @Synchronized
    fun saveForm(tag: String?, stringValue: String?) {
        stringValue?.let {
            val settings =
                context.getSharedPreferences(APPLICATION_PREFERENCE_TAG, Context.MODE_PRIVATE)
            val value = encryptionServices.encrypt(stringValue)
            val editor = settings.edit()
            editor.putString(tag, value)
            editor.apply()
        }
    }

    @Synchronized
    fun getForm(tag: String?): String? {
        val settings =
            context.getSharedPreferences(APPLICATION_PREFERENCE_TAG, Context.MODE_PRIVATE)
        val value = settings.getString(tag, null)
        var finalValue: String? = null
        if (value != null) {
            finalValue = try {
                val decryptedValue = encryptionServices.decrypt(value)
                decryptedValue
            } catch (e: Exception) {
                null
            }
        }
        return finalValue
    }

    @Synchronized
    fun getForm(tag: String, default: String): String {
        val settings =
            context.getSharedPreferences(APPLICATION_PREFERENCE_TAG, Context.MODE_PRIVATE)
        val value = settings.getString(tag, default)
        requireNotNull(value)
        val finalValue = try {
            val decryptedValue = encryptionServices.decrypt(value)
            decryptedValue
        } catch (e: Exception) {
            default
        }
        return finalValue
    }

    @Synchronized
    fun saveBytes(formName: String, b: ByteArray) {
        try {
            val file = context.openFileOutput(formName, Context.MODE_PRIVATE)
            file.write(b)
            file.close()
        } catch (e: FileNotFoundException) {
            Log.w(LOG_TAG, "Exception saveBytes FileNotFoundException" + e.message, e)
        } catch (e: IOException) {
            Log.w(LOG_TAG, "Exception saveBytes IOException" + e.message, e)
        }

    }

    @Synchronized
    fun getBytes(formName: String): ByteArray? {
        var result: ByteArray? = null
        try {
            val file = context.openFileInput(formName)
            result = ByteArray(file.available())
            file.read(result)
            file.close()
        } catch (e: FileNotFoundException) {
            Log.w(LOG_TAG, "Exception getBytes FileNotFoundException" + e.message, e)
        } catch (e: IOException) {
            Log.w(LOG_TAG, "Exception getBytes IOException" + e.message, e)
        }

        return result
    }

    @Synchronized
    fun saveObject(formName: String, obj: Any) {
        try {
            val file = context.openFileOutput(formName, Context.MODE_PRIVATE)
            ObjectOutputStream(file).writeObject(obj)
        } catch (e: FileNotFoundException) {
            Log.w(LOG_TAG, "Exception saveObject FileNotFoundException" + e.message, e)
        } catch (e: IOException) {
            Log.w(LOG_TAG, "Exception saveObject IOException" + e.message, e)
        }

    }

    @Synchronized
    fun getObject(formName: String): Any? {

        var result: Any? = null

        try {
            val file = context.openFileInput(formName)
            val obj = ObjectInputStream(file)
            result = obj.readObject()
        } catch (e: FileNotFoundException) {
            Log.w(LOG_TAG, "Exception getObject FileNotFoundException" + e.message, e)
        } catch (e: IOException) {
            Log.w(LOG_TAG, "Exception getObject IOException" + e.message, e)
        } catch (e: ClassNotFoundException) {
            Log.w(LOG_TAG, "Exception getObject ClassNotFoundException" + e.message, e)
        }

        return result
    }

    @Synchronized
    fun saveInt(tag: String, intValue: Int) {
        val integerString: String = intValue.toString()
        saveForm(tag, integerString)
    }

    @Synchronized
    fun getInt(tag: String, default: Int): Int {
        val settings =
            context.getSharedPreferences(APPLICATION_PREFERENCE_TAG, Context.MODE_PRIVATE)
        val value = settings.getString(tag, null)
        var finalValue: Int = default
        if (value != null) {
            finalValue = try {
                val decryptedValue = encryptionServices.decrypt(value)
                decryptedValue.toInt()
            } catch (e: Exception) {
                default
            }
        }
        return finalValue
    }

    fun delete(preferenceName: String?) {
        preferenceName?.let {
            getPreferenceEditor().remove(it).commit()
        }
    }

    @Synchronized
    fun deleteObject(formName: String): Boolean {
        return context.deleteFile(formName)
    }

    private fun deleteAll() {
        getPreferenceEditor().clear().commit()
        val fileList = context.fileList()
        for (formName in fileList) {
            context.deleteFile(formName)
        }
    }

    private fun getPreferenceEditor(): SharedPreferences.Editor {
        val preferences =
            context.getSharedPreferences(APPLICATION_PREFERENCE_TAG, Context.MODE_PRIVATE)
        return preferences.edit()
    }

}