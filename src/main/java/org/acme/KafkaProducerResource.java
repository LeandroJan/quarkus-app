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

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/kafka")
public class KafkaProducerResource {

     @Inject
    @Channel("my-topic")
    Emitter<String> emitter;

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(MessagePayload payload) {
        emitter.send(payload.getMessage());
        return Response.ok().entity("{\"status\":\"Message sent\"}").build();
    }

    public static class MessagePayload {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
