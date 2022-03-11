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
    fun send(@Payload message: String) { // PRECISA SER STRING
        val payload = ObjectMapper().writeValueAsString(message)

        println(payload)

        template.convertAndSend("/topic/messages", payload)
    }
}