package com.app.data.utlis

import com.app.domain.exception.WisalException
import com.app.domain.exception.WisalException.BadRequestException
import com.app.domain.exception.WisalException.ForbiddenRequestException
import com.app.domain.exception.WisalException.NoInternetException
import com.app.domain.exception.WisalException.NotFoundException
import com.app.domain.exception.WisalException.ServerErrorException
import com.app.domain.exception.WisalException.ServerNotFoundException
import com.app.domain.exception.WisalException.ServiceUnavailableException
import com.app.domain.exception.WisalException.TooManyRequestsException
import com.app.domain.exception.WisalException.TooMuchTimeException
import com.app.domain.exception.WisalException.UnauthorizedRequestException
import com.app.domain.exception.WisalException.UnknownException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend inline fun <T> handleApi(
    crossinline execute: suspend () -> Response<T>
): T {
    try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            return body
        } else {
            when (response.code()) {
                400 -> throw BadRequestException
                401 -> throw UnauthorizedRequestException
                403 -> throw ForbiddenRequestException
                404 -> throw NotFoundException
                409 -> throw WisalException.ConflictException
                429 -> throw TooManyRequestsException
                500 -> throw ServerErrorException
                503 -> throw ServiceUnavailableException
                else -> throw UnknownException
            }
        }
    } catch (_: ConnectException) {
        throw NoInternetException
    } catch (e: HttpException) {
        when (e.code()) {
            500 -> throw ServerErrorException
            404 -> throw NotFoundException
            else -> throw UnknownException
        }
    } catch (_: SocketTimeoutException) {
        throw TooMuchTimeException
    } catch (e: UnknownHostException) {
        if (e.message?.contains("Unable to resolve host") == true)
            throw NoInternetException
        else
            throw ServerNotFoundException
    } catch (_: Throwable) {
        throw UnknownException
    }
}