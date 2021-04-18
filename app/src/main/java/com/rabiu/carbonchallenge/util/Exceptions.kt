package com.rabiu.carbonchallenge.util

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class RunTimeException(message: String): RuntimeException(message)