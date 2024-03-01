import com.lucreziacarena.cryptoinsight.network.results.CryptoError

sealed class ApiResult<T>(val data:T?=null, val error:CryptoError?=null){
    class Success<T>(data: T):ApiResult<T>(data = data)
    class Error<T>(error: CryptoError):ApiResult<T>(error = error)
    class Loading<T>:ApiResult<T>()
}
