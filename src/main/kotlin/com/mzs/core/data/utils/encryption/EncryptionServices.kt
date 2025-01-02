package com.mzs.core.data.utils.encryption

import javax.inject.Inject

class EncryptionServices @Inject constructor(private val keyStoreWrapper: KeyStoreWrapper) {

    companion object {
        const val MASTER_KEY = "MASTER_KEY"
        const val PROVIDER = "AndroidKeyStore"
    }

    fun createMasterKey() {
        if (isSigningKey().not()) {
            createAndroidSymmetricKey()
        }
    }

    private fun isSigningKey(): Boolean {
        return keyStoreWrapper.getAndroidKeyStoreSymmetricKey(MASTER_KEY) != null
    }

    fun encrypt(data: String): String {
        return encryptWithAndroidSymmetricKey(data = data)
    }

    fun decrypt(data: String): String {
        return decryptWithAndroidSymmetricKey(data = data)
    }

    private fun createAndroidSymmetricKey() {
        keyStoreWrapper.createAndroidKeyStoreSymmetricKey(alias = MASTER_KEY)
    }

    private fun encryptWithAndroidSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreSymmetricKey(alias = MASTER_KEY)
        return CipherWrapper(transformation = CipherWrapper.TRANSFORMATION_SYMMETRIC).encrypt(
            data = data,
            key = masterKey,
            useInitializationVector = true
        )
    }

    private fun decryptWithAndroidSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreSymmetricKey(alias = MASTER_KEY)
        return CipherWrapper(transformation = CipherWrapper.TRANSFORMATION_SYMMETRIC).decrypt(
            data = data,
            key = masterKey,
            useInitializationVector = true
        )
    }

}