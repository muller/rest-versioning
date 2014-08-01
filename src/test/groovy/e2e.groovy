package test

import api.*

import org.junit.*
import org.junit.Assert.*

import groovyx.net.http.*

class EndToEndTest {

    def client = new RESTClient( 'http://localhost:3000' )

    @BeforeClass
    static void startServer() {
        def deployment = new org.jboss.resteasy.spi.ResteasyDeployment(resources: [new RestApi()])
        def server = new org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer(port: 3000, deployment: deployment)
        server.start()
    }

    @Test
    @Ignore
    void testHello() {

        def response = client.get path: '/rest-api'

        assert response.status == 200
        assert response.data.text == 'Hello!!!\n'
        assert response.contentType == 'text/plain'
    }

    @Test
    void testHelloJson() {

        def response = client.get path: '/rest-api/json'

        assert response.status == 200
        assert response.data.message == 'Hello!!!'
        assert response.contentType == 'application/json'
    }

    @Test
    void testHelloJsonHeader() {

        def response = client.get(
            path: '/rest-api',
            headers: [ 'Accept': 'application/json' ]
        )

        assert response.status == 200
        assert response.data.message == 'Hello!!!'
        assert response.contentType == 'application/json'
    }

    @Test
    void testHelloJsonI18n() {

        def get = { language ->

            def response = client.get(
                path: '/rest-api',
                headers: [ 'Accept': 'application/json' ],
                query: ['language': language]
            )

            assert response.status == 200
            assert response.contentType == 'application/json'

            return response.data.message
        }

        assert get('en')  == 'Hello!!!'
        assert get('pt')  == 'Oi!!!'
    }

    @Test
    void testNewFormat() {

        def get = { language ->

            def response = client.get(
                path: '/rest-api',
                headers: [ 'Accept': 'application/x.testapp.20140801+json' ],
                query: ['language': language],
            )

            assert response.status == 200
            assert response.contentType == 'application/json'

            return response.data.msg
        }

        assert get('en')  == 'Hello!!!'
        assert get('pt')  == 'Oi!!!'
    }

    @Test
    void testHelloDeprecated() {

        def response = client.get(
            path: '/rest-api',
            headers: [ 'Accept': 'text/plain' ],
        )

        assert response.status == 200
        assert response.data.text == 'Hello!!!\n'
        assert response.contentType == 'application/x.testapp.deprecated+text'
    }
}
