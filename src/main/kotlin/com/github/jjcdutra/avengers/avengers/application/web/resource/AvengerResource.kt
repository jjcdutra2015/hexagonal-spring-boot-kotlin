package com.github.jjcdutra.avengers.avengers.application.web.resource

import com.github.jjcdutra.avengers.avengers.application.web.request.AvengerRequest
import com.github.jjcdutra.avengers.avengers.application.web.response.AvengerResponse
import com.github.jjcdutra.avengers.avengers.domain.avenger.AvengerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

private const val API_PATH = "/v1/api/avenger"

@RestController
@RequestMapping(API_PATH)
class AvengerResource(
    @Autowired private val repository: AvengerRepository
) {

    @GetMapping
    fun getAvengers() = repository.getAvengers()
        .map { AvengerResponse.from(it) }
        .let {
            ResponseEntity.ok().body(it)
        }

    @GetMapping("{id}/detail")
    fun getAvengerDetails(@PathVariable("id") id: Long) =
        repository.getDetail(id)?.let {
            ResponseEntity.ok().body(it)
        } ?: ResponseEntity.notFound().build<Void>()

    @PostMapping
    fun create(@RequestBody request: AvengerRequest) =
        request.toAvenger().run {
            repository.create(this)
        }.let {
            ResponseEntity.created(URI("$API_PATH/${it.id}"))
        }

    @PutMapping("{id}")
    fun update(@RequestBody request: AvengerRequest, @PathVariable("id") id: Long) =
        repository.getDetail(id)?.let {
            AvengerRequest.to(it.id!!, request).apply {
                repository.update(this).let { avenger ->
                    ResponseEntity.accepted().body(AvengerResponse.from(avenger))
                }
            }
        } ?: ResponseEntity.notFound().build<Void>()

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Long) =
        repository.delete(id).let {
            ResponseEntity.accepted().build<Void>()
        }
}