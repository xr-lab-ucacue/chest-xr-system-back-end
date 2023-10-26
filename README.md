# Backend para el Proyecto de Interfaz Gráfica de Detección de Enfermedades Pulmonares

¡Bienvenido al repositorio de nuestro emocionante backend para el proyecto de Interfaz Gráfica de Detección de Enfermedades Pulmonares! En este repositorio, presentamos el código backend desarrollado en Java que respalda la funcionalidad de la interfaz gráfica. Nuestro backend está diseñado para gestionar la seguridad, autenticación y autorización utilizando OAuth 2.0, así como para proporcionar una interfaz de programación de aplicaciones (API) para los microservicios CRUD y el consumo del servidor de detección de enfermedades pulmonares.

## Características Destacadas del Backend

- **Seguridad OAuth 2.0:** Hemos incorporado un sólido sistema de seguridad basado en OAuth 2.0 para garantizar la autenticación y autorización seguras de los usuarios. Esto protege la información médica sensible y asegura el acceso controlado a las funciones de la interfaz.

- **Microservicios CRUD:** Hemos implementado microservicios que permiten la gestión completa de usuarios y otras entidades dentro de la interfaz. Estos microservicios proporcionan operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para garantizar un flujo de trabajo eficiente en la plataforma.

- **Consumo de Servidor de Detección:** Mediante la integración con el servidor de detección de enfermedades pulmonares, que utiliza el proyecto de Detección de Enfermedades Pulmonares y Conversión de Imágenes en Flask, nuestra API permite a los usuarios cargar imágenes médicas, obtener resultados de detección y visualizarlos en la interfaz gráfica.

## Instrucciones de Uso del Backend

1. **Clonar el Repositorio:** Comienza clonando este repositorio del backend en tu máquina local:

   ```
   git clone https://github.com/PePeWee07/chest-xr-system-back-end
   ```

2. **Configurar Propiedades:** Accede al archivo de configuración y ajusta las propiedades relacionadas con la base de datos, el servidor de detección y cualquier otra configuración necesaria.

3. **Compilar y Ejecutar:** Utiliza tu entorno de desarrollo Java para compilar y ejecutar el backend. Asegúrate de que todas las dependencias se encuentren instaladas correctamente.

4. **Autenticación y Autorización:** Navega a través de los endpoints de autenticación y autorización proporcionados por OAuth 2.0 para obtener tokens de acceso válidos. Utiliza estos tokens para acceder a los microservicios CRUD y a las funciones de consumo del servidor de detección.

5. **Consumo de Microservicios:** Utiliza las rutas de API proporcionadas para realizar operaciones CRUD en usuarios y otras entidades.

## Contribución

Si deseas contribuir al desarrollo y mejora del backend, ¡serás más que bienvenido! Si tienes ideas para fortalecer la seguridad, optimizar los microservicios o cualquier otra mejora, no dudes en hacer un fork del repositorio y enviar un pull request.

## Licencia

Este backend se encuentra bajo la licencia MIT. Puedes obtener más detalles en el archivo `LICENSE` del repositorio.

Esperamos que este backend sea una parte sólida y segura de tu proyecto de Interfaz Gráfica de Detección de Enfermedades Pulmonares. Si tienes preguntas o comentarios, no dudes en abrir un issue en el repositorio.

**El Equipo de Desarrollo** 
Jose Roman, Sebastian Quevedo
---

[Enlace al Proyecto de Detección de Enfermedades Pulmonares y Conversión de Imágenes en Flask](https://github.com/PePeWee07/Servidor-flask)
