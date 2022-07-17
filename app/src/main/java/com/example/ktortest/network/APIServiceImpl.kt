package com.example.ktortest.network

import android.util.Log
import com.example.ktortest.Employee
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import kotlin.text.get

class APIServiceImpl(private val client: HttpClient): APIService {

    override suspend fun getEmployees(): List<Employee> {
        return try {
//            client.get { url(APIRoutes.EMPLOYEES) }.body()
            client.get { url("/statuscode/422") }.body()
        } catch (e: RedirectResponseException) {
            Log.d("3xx Error", e.response.status.description)
            emptyList()
        } catch (e: ClientRequestException) {
            Log.d("4xx Error", e.response.toString())
            emptyList()
        }catch (e: ServerResponseException) {
            Log.d("5xx Error", e.response.status.description)
            emptyList()
        }
    }

    override suspend fun newEmployee(employee: Employee): Employee? {
        TODO("Not yet implemented")
    }
}