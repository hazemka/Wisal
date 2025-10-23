package com.app.domain.exception

sealed class WisalException() : Exception() {
    object BadRequestException : WisalException()
    object UnauthorizedRequestException : WisalException()
    object ForbiddenRequestException : WisalException()
    object NotFoundException : WisalException()
    object ServerErrorException : WisalException()
    object ConflictException: WisalException()
    object UnknownException : WisalException()
    object NoInternetException : WisalException()
    object TooMuchTimeException : WisalException()
    object ServerNotFoundException : WisalException()
    object ServiceUnavailableException : WisalException()
    object TooManyRequestsException : WisalException()
    object NullException : WisalException()
    object AddMediaItemToCollectionException : WisalException()
}