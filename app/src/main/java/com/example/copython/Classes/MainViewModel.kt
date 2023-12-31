package com.example.copython.Classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.copython.screens.MessageItem
import com.google.ai.generativelanguage.v1beta3.GenerateTextRequest
import com.google.ai.generativelanguage.v1beta3.TextPrompt
import com.google.ai.generativelanguage.v1beta3.TextServiceClient
import com.google.ai.generativelanguage.v1beta3.TextServiceSettings
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider
import com.google.api.gax.rpc.FixedHeaderProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _output = MutableStateFlow(value = "")
    val output: StateFlow<String>
        get() = _output


    private var client: TextServiceClient

    init {
        // Initialize the Text Service Client
        client = initializeTextServiceClient(
            apiKey = "AIzaSyATgp0MHCRr57wDRaZZFa8QLfJsAJnQ0c8"
        )

    }
    fun sendPrompt(prompt: String, messages: List<MessageItem>) {
        // Reuse the logic from init to send a prompt
        val textPrompt = createPrompt(prompt)
        val request = createTextRequest(textPrompt)
        generateText(request, messages)
    }
    private fun initializeTextServiceClient(
        apiKey: String
    ): TextServiceClient {
        // (This is a workaround because GAPIC java libraries don't yet support API key auth)
        val transportChannelProvider = InstantiatingGrpcChannelProvider.newBuilder()
            .setHeaderProvider(FixedHeaderProvider.create(hashMapOf("x-goog-api-key" to apiKey)))
            .build()

        // Create TextServiceSettings
        val settings = TextServiceSettings.newBuilder()
            .setTransportChannelProvider(transportChannelProvider)
            .setCredentialsProvider(FixedCredentialsProvider.create(null))
            .build()

        // Initialize a TextServiceClient
        val textServiceClient = TextServiceClient.create(settings)

        return textServiceClient
    }

    private fun createPrompt(
        textContent: String
    ): TextPrompt {
        val textPrompt = TextPrompt.newBuilder()
            .setText(textContent)
            .build()

        return textPrompt
    }

    private fun createTextRequest(prompt: TextPrompt): GenerateTextRequest {
        return GenerateTextRequest.newBuilder()
            .setModel("models/text-bison-001") // Required, which model to use to generate the result
            .setPrompt(prompt) // Required
            .setTemperature(0.5f) // Optional, controls the randomness of the output
            .setCandidateCount(1) // Optional, the number of generated texts to return
            .build()
    }

    private fun generateText(
        request: GenerateTextRequest,
        messages: List<MessageItem>
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = client.generateText(request)
                val returnedText = response.candidatesList.last()
                // display the returned text in the UI
                _output.update { returnedText.output }
            } catch (e: Exception) {
                // There was an error, let's add a new text with the details
                _output.update { "- Ha ocurrido un error, intenta reescribir tu pregunta y enviarla de nuevo \n " +
                                 "- Recuerda hacer preguntas solo en ingles \n " +
                                 "- Si deseas recibir una respuesta en español, puedes especificarlo al final de la pregunta. Por el ejemplo de la siguiente manera: \n" +
                                 "- Tell me a joke, in spanish"}
            }
        }
    }
}