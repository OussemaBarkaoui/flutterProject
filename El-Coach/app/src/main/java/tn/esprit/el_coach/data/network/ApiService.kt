package tn.esprit.el_coach.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.Call
import retrofit2.http.PUT

// Request model for login
data class LoginRequest(
    val email: String,
    val password: String
)

// Response model for login (adjust to match your backend response)
data class LoginResponse(
    val accessToken: String,  // JWT token returned from the server
    val refreshToken: String,  // Refresh token returned from the server
    val userId: String         // User ID returned from the server
)

data class SignUpRequest(
    val name : String,
    val email: String,
    val password: String,
    val image:String,
    val phoneNumber:Int
)

data class SignUpResponse(
    val success: Boolean,
    val message: String
)

data class ForgotPasswordRequest(val email: String)
data class ForgotPasswordResponse(val message: String, val resetToken: Int)

data class ResetPasswordRequest(
    val resetToken: Int,
    val newPassword: String
)
data class GoogleSignInRequest(
    val idToken: String
)


// Si vous avez besoin d'une réponse spécifique pour Google Sign-In
data class GoogleSignInResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
    val email: String?,
    val displayName: String?
)


interface ApiService {
    @POST("auth/signup") // Ensure this matches your backend route
    suspend fun signUp(@Body signupRequest: SignUpRequest): Response<SignUpResponse>

    // Login API
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/forgot-password")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @PUT("auth/reset-password")
    fun resetPassword(@Body request: ResetPasswordRequest): Call<Void>

    @POST("auth/google")
    suspend fun googleSignIn(@Body request: GoogleSignInRequest): Response<GoogleSignInResponse>

}