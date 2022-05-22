package com.rashel.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.rashel.myapplication.data.remote.TmdbApiService
import com.rashel.myapplication.data.remote.dto.movie_detail.MovieDetailDto
import com.rashel.myapplication.data.repository.MovieRepositoryImpl
import com.rashel.myapplication.domain.repository.MovieRepository
import com.rashel.myapplication.util.MockResponseFileReader
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GetMovieApiTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val server = MockWebServer()

    private lateinit var repository: MovieRepository

    private lateinit var mockResponse: String

    private val gson = GsonBuilder()
        .setLenient()
        .create()


    @Before
    fun init() {


       // server.start(8000)

        val BASE_URL = server.url("/").toString()

        val okHttpClient = OkHttpClient
            .Builder().build()

        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(TmdbApiService::class.java)

        repository = MovieRepositoryImpl(service)
    }

    @Test
    fun test_movie_list_api_response() {

        mockResponse =
            MockResponseFileReader("movieList/success.json")
                .content

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        val response = runBlocking { repository.getMovie("", 10) }


        val mockjson = gson.fromJson(mockResponse, MovieDetailDto::class.java)


        Assert.assertNotNull(response)
        Assert.assertEquals(response.id, mockjson.id)

    }

    @After
    fun tearDown(){
        server.shutdown()
    }

}