package mate.academy

class HttpRequestProcessor(private val client: HttpClient) {

    companion object {
        const val OK_STATUS_CODE = 200
    }

    fun processRequest(url: String): ResponseData {
        return client.sendRequest(url)
            .also {
                println("Response Status: ${it.statusCode} - ${it.statusText}")
                println("Response Content: ${it.content}")
            }
            .let { response ->
                if (response.statusCode == OK_STATUS_CODE) {
                    response.also {
                        println("Processing content: ${it.content}")
                    }
                    ResponseData(
                        status = "Success",
                        contentSummary = response.content
                    )
                } else {
                    ResponseData(
                        status = "Failure",
                        contentSummary = "Request failed with status: ${response.statusCode}"
                    )
                }
            }
    }
}
