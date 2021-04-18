package com.rabiu.carbonchallenge.api


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rabiu.carbonchallenge.data.entities.User
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class DummyServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: DummyApiService

    private lateinit var mockWebServer : MockWebServer

    private val limit = 50

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DummyApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }


    @Test
    fun requestUsers() {
        runBlocking {
            enqueueResponse("users.json")
            val resultResponse = service.fetchUsersList(limit).body()
            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request.path,`is`("/user?limit=50"))
        }
    }

    @Test
    fun getUserListResponse() {
        runBlocking {
            enqueueResponse("users.json")
            val resultResponse = service.fetchUsersList(limit).body()
            val usersList = resultResponse!!.data

            Assert.assertThat(resultResponse.total, `is`(100))
            Assert.assertThat(usersList.size, `is`(50))
        }
    }

    @Test
    fun getUserItem() {
        runBlocking {
            enqueueResponse("users.json")
            val resultResponse = service.fetchUsersList(limit).body()
            val usersList = resultResponse!!.data

            val user = usersList[0]
            Assert.assertThat(user.id, `is`("0F8JIqi4zwvb77FGz6Wt"))
            Assert.assertThat(user.lastName, `is`("Fiedler"))
            Assert.assertThat(user.firstName, `is`("Heinz-Georg"))
            Assert.assertThat(user.email, `is`("heinz-georg.fiedler@example.com"))
            Assert.assertThat(user.title, `is`("mr"))
            Assert.assertThat(
                user.picture,
                `is`("https://randomuser.me/api/portraits/men/81.jpg")
            )
        }
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val source = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName").source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }


}