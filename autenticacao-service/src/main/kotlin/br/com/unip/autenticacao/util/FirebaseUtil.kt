package br.com.unip.autenticacao.util

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class FirebaseUtil() {

    @Value("\${service.account-id}")
    private val serviceAccountId: String? = null

    fun inicializarApp() {
        val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .setServiceAccountId(serviceAccountId)
                .build()

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options)
        }
    }
}