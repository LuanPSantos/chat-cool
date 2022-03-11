package codeplays.trainee.chatcool

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller


@Controller
class ChatController(
    private val template: SimpMessagingTemplate
) {

    @MessageMapping("/send")
    fun send(@Payload message: Any) {
        println(ObjectMapper().writeValueAsString(message))
        template.convertAndSend("/topic/messages", ObjectMapper().writeValueAsString(message),
            mutableMapOf("content-type" to "application/json" as Any)
        )
    }
}