package org.acme;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/kafka")
public class KafkaProducerResource {

    @Inject
    @Channel("generated-data")
    Emitter<String> emitter;

    @POST
    public Response sendMessage(String message) {
        emitter.send(message);
        return Response.ok().build();
    }
}
