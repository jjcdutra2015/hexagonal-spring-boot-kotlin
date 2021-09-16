package com.github.jjcdutra.avengers.avengers.application.web.resource

import com.github.jjcdutra.avengers.avengers.application.web.request.AvengerRequest
import com.github.jjcdutra.avengers.avengers.application.web.response.AvengerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/avenger")
class AvengerResource {

    @GetMapping
    fun getAvengers() = ResponseEntity.ok().body(emptyList<AvengerResponse>())

    @GetMapping("{id}")
    fun getAvengerDetails(@PathVariable("id") id: Long) = ResponseEntity.ok().build<AvengerResponse>()

    @PostMapping
    fun create(@RequestBody request: AvengerRequest) {}
}