# Práctica: Autenticación y Autorización con AWS Cognito

¡Hola! En este repositorio presento mi implementación de una API REST segura utilizando **Spring Boot (Kotlin)** y **AWS Cognito**.

El objetivo principal de este proyecto es demostrar cómo proteger endpoints (rutas) de una API, diferenciando entre acceso público, usuarios logueados y administradores.

---

## Autor
**Estudiante:** Victor Paul Alvarez Alvarez
**Materia:** Arquitectura de Software
**Tecnologías:** Kotlin, AWS Cognito, Gradle.

---

## ¿De qué trata el proyecto?

He configurado una aplicación que actúa como un **Resource Server**. Esto significa que la API no guarda contraseñas, sino que confía en los tokens (JWT) que emite AWS Cognito.

La seguridad se maneja en tres niveles:
1.  **Público:** Cualquiera puede entrar a saludar.
2.  **Restringido:** Necesitas un token de usuario válido.
3.  **Admin:** Necesitas un token Y pertenecer al grupo `admin` en Cognito.

> **Dato curioso:** Para que Spring entienda los grupos de Cognito, implementé un convertidor personalizado en `SecurityConfig.kt` que transforma el grupo `admin` en el rol `ROLE_admin`.

---

## Configuración Previa (Importante)

Para que el proyecto arranque en tu máquina, necesitas configurar el archivo `src/main/resources/application.yaml`.

Asegúrate de poner **tus propias credenciales** de AWS (User Pool ID y Región):
