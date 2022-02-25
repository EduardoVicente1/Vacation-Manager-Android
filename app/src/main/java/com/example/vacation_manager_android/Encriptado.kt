package com.example.vacation_manager_android

import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class Encriptado {

    fun encriptar(datos: String, clave: String): String {

        var secretKey: SecretKeySpec = generateKey(clave)
        // Algoritmo de encriptación AES
        var cipher: Cipher = Cipher.getInstance("AES")
        // Modo encriptación
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        // Array de datos en bytes
        var datosEncriptadosBytes: ByteArray = cipher.doFinal(datos.toByteArray())
        // Devolverlo en Base64 y se convierte a String
        var datosEncriptadosString: String = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT)

        return datosEncriptadosString
    }
    fun desencriptar(datos: String, clave: String): String{

        var secretKey: SecretKeySpec = generateKey(clave)
        var cipher: Cipher = Cipher.getInstance("AES")
        // Modo desencriptación
        cipher.init(Cipher.DECRYPT_MODE, secretKey)

        // Array de bytes con datos descodificados
        var datosDescodificados: ByteArray = Base64.decode(datos, Base64.DEFAULT)
        // Array de bytes con datos desencriptados
        var datosDesenciptadosByte: ByteArray = cipher.doFinal(datosDescodificados)

        var datosDesencriptadosString = String(datosDesenciptadosByte)

        return datosDesencriptadosString
    }

    private fun generateKey(clave: String): SecretKeySpec {
        // Genera el algoritmo
        val sha: MessageDigest = MessageDigest.getInstance("SHA-256")
        // Pasar la clave a byte con el estándar UTF-8 en un array de bytes
        var key: ByteArray = clave.toByteArray(Charsets.UTF_8)
        //cálculo del hash
        key = sha.digest(key)
        //estandar AES
        val secretKey = SecretKeySpec(key, "AES")

        return secretKey
    }
}