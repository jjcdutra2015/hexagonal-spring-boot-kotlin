package com.github.jjcdutra.avengers.avengers.application.web.request

import com.github.jjcdutra.avengers.avengers.domain.avenger.Avenger
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class AvengerRequest(

    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val nick: String,

    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val person: String,

    val description: String? = "",
    val history: String? = "",
) {
    fun toAvenger() = Avenger(nick = nick, person = person, description = description, history = history)

    companion object {
        fun to(id: Long, request: AvengerRequest) =
            Avenger(
                id = id,
                nick = request.nick,
                person = request.person,
                description = request.description,
                history = request.history
            )
    }
}
