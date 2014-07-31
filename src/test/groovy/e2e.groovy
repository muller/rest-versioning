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
    void testHello() {

        def response = client.get path: '/rest-api'

        assert response.status == 200
        assert response.data.text == 'Hello!!!\n'
        assert response.contentType == 'text/plain'
    }
}
