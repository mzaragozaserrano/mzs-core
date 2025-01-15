package com.mzs.core.data.utils.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class KeyStoreWrapper {

    private val keyStore: KeyStore = createAndroidKeyStore()

    fun getAndroidKeyStoreSymmetricKey(alias: String): SecretKey? =
        keyStore.getKey(alias, null) as? SecretKey

    fun createAndroidKeyStoreSymmetricKey(
        alias: String,
        invalidatedByBiometricEnrollment: Boolean = true,
        userAuthenticationRequired: Boolean = false,
        userAuthenticationValidWhileOnBody: Boolean = true,
    ): SecretKey {
        val keyGenerator =
            KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, EncryptionServices.PROVIDER)
        val builder = KeyGenParameterSpec.Builder(
            alias,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setUserAuthenticationRequired(userAuthenticationRequired)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
        builder.setInvalidatedByBiometricEnrollment(invalidatedByBiometricEnrollment)
        builder.setUserAuthenticationValidWhileOnBody(userAuthenticationValidWhileOnBody)
        keyGenerator.init(builder.build())
        return keyGenerator.generateKey()
    }

    private fun createAndroidKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance(EncryptionServices.PROVIDER)
        keyStore.load(null)
        return keyStore
    }

}