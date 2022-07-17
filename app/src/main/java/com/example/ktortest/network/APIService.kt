package com.example.ktortest.network

import com.example.ktortest.Employee
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

interface APIService {

    suspend fun getEmployees(): List<Employee>

    suspend fun newEmployee(employee: Employee): Employee?

    companion object {
        fun create(): APIService {
            return APIServiceImpl(
                client = HttpClient(Android) {
                    expectSuccess = true

                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    install(ContentNegotiation) {
                        json(Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                            encodeDefaults = false
                        })
                    }

                    install(HttpTimeout) {
                        requestTimeoutMillis = 15000L
                        connectTimeoutMillis = 15000L
                        socketTimeoutMillis = 15000L
                    }

                    defaultRequest {
                        url("https://hub.dummyapis.com")
                        contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                    }
                }
            )
        }
    }
}