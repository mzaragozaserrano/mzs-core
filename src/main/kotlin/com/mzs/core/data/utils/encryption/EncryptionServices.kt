package com.mzs.core.data.utils.encryption

import javax.inject.Inject

class EncryptionServices @Inject constructor(
    private val keyStoreWrapper: KeyStoreWrapper,
) {

    companion object {
        const val MASTER_KEY = "MASTER_KEY"
        const val PROVIDER = "AndroidKeyStore"
    }

    fun createMasterKey() {
        if (!isSigningKey()) {
            createAndroidSymmetricKey()
        }
    }

    private fun isSigningKey(): Boolean {
        return keyStoreWrapper.getAndroidKeyStoreSymmetricKey(MASTER_KEY) != null
    }

    fun encrypt(data: String): String {
        return encryptWithAndroidSymmetricKey(data)
    }

    fun decrypt(data: String): String {
        return decryptWithAndroidSymmetricKey(data)
    }

    private fun createAndroidSymmetricKey() {
        keyStoreWrapper.createAndroidKeyStoreSymmetricKey(MASTER_KEY)
    }

    private fun encryptWithAndroidSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreSymmetricKey(MASTER_KEY)
        return CipherWrapper(CipherWrapper.TRANSFORMATION_SYMMETRIC).encrypt(data, masterKey, true)
    }

    private fun decryptWithAndroidSymmetricKey(data: String): String {
        val masterKey = keyStoreWrapper.getAndroidKeyStoreSymmetricKey(MASTER_KEY)
        return CipherWrapper(CipherWrapper.TRANSFORMATION_SYMMETRIC).decrypt(data, masterKey, true)
    }

}