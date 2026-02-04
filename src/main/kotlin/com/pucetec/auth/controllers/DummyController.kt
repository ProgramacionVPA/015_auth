package com.pucetec.auth.controllers


import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DummyController {
    // a este endpoint. cualquiera puede acceder
    // Ejemplos: health, mostrar catalogos que no sean llenos de datos sensibles, etc.
    @GetMapping("/public")
    fun public(): Response {
        return Response("Todo ok. Este es un endpoint publico")
    }

    // a este endpoint. solo usuarios autenticados pueden acceder
    // ejemplos interaccion con datos propios del usuario
    @GetMapping("/restricted")
    fun restricted(
        @AuthenticationPrincipal jwt: Jwt,
    ): Response {
        val user: String = jwt.getClaim<String>("username")
        return Response("Todo ok. Este es un endpoint restringido. Hola ${user}")
    }

    // a este endpoint. solo usuarios autenticados con rol admin pueden acceder
    // ejemplos: administracion de usuarios, reportes, etc.
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/restricted/admin")
    fun admin(): Response {
        return Response("Todo ok. Este es un endpoint solo para admins")
    }

    @PreAuthorize("hasAnyRole('ROLE_admin','ROLE_manager')")
    @GetMapping("/restricted/sensitive")
    fun sensitiveData(): Response {
        return Response("Todo ok. Este es un endpoint solo para admins y managers")
    }
}

data class Response(val message: String)