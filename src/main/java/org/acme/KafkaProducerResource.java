package org.acme;

// import org.eclipse.microprofile.reactive.messaging.Channel;
// import org.eclipse.microprofile.reactive.messaging.Emitter;

// import jakarta.inject.Inject;
// import jakarta.ws.rs.POST;
// import jakarta.ws.rs.Path;
// import jakarta.ws.rs.core.Response;

// @Path("/kafka")
// public class KafkaProducerResource {

//     @Inject
//     @Channel("generated-data")
//     Emitter<String> emitter;

//     @POST
//     public Response sendMessage(String message) {
//         emitter.send(message);
//         return Response.ok().build();
//     }
// }

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
// import jakarta.ws.rs.core.MediaType;
// import jakarta.ws.rs.core.Response;

@Path("/")
public class KafkaProducerResource {

    @Inject
    @Channel("my-topic")
    Emitter<String> emitter;

    @GET
    public Uni<String> index() {
        return Uni.createFrom().item("index.html");
    }

    @POST
    @Path("/send")
    public Uni<String> sendMessage(@FormParam("message") String message) {
        emitter.send(message);
        return Uni.createFrom().item("Message sent: " + message);
    }
}
