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

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/kafka")
public class KafkaProducerResource {

    @Inject
    @Channel("my-kafka-topic")
    Emitter<String> emitter;

    @Inject
    Template index;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return index.instance();
    }

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String sendMessage(@FormParam("message") String message) {
        emitter.send(message);
        return "Message sent: " + message;
    }
}
