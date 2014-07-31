package test

import org.junit.*
import org.junit.Assert.*

import groovyx.net.http.*

class EndToEndTest {

    def client = new RESTClient( 'http://localhost:3000' )

    @Test
    void testHello() {

        def response = client.get path: '/rest-api'

        assert response.status == 200
        assert response.data.text == 'Hello!!!\n'
        assert response.contentType == 'text/plain'
    }
}
