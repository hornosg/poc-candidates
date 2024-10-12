# Aplicación de Gestión de Candidatos

Este README proporciona instrucciones detalladas sobre cómo iniciar la aplicación, incluyendo cómo ejecutar MySQL en Docker, cómo asegurarse de que los datos de prueba de Flyway se hayan aplicado correctamente y cómo acceder al Swagger de la aplicación.

## Requisitos previos

- Docker y Docker Compose instalados en su sistema
- Java Development Kit (JDK) 11 o superior
- Gradle (el proyecto incluye el Gradle Wrapper, por lo que no es necesario instalarlo por separado)

## Pasos para iniciar la aplicación

### 1. Iniciar MySQL en Docker

1. Abra una terminal y navegue hasta el directorio raíz del proyecto.
2. Ejecute el siguiente comando para iniciar el contenedor de MySQL:

```bash
docker compose up -d --build
```

Este comando iniciará un contenedor de MySQL con las siguientes características:
- Nombre del contenedor: candidates_mysql
- Puerto: 3306 (mapeado al puerto 3306 del host)
- Contraseña de root: root
- Base de datos: candidates_db

### 2. Verificar que MySQL está en funcionamiento

Puede verificar que el contenedor de MySQL está en funcionamiento con el siguiente comando:

```bash
docker ps
```

Debería ver un contenedor llamado "candidates_mysql" en la lista.

### 3. Iniciar la aplicación Spring Boot

1. En la terminal, desde el directorio raíz del proyecto, ejecute el siguiente comando:

```bash
./gradlew bootRun
```

Este comando compilará la aplicación y la iniciará. La aplicación se conectará automáticamente a la base de datos MySQL en Docker.

### 4. Verificar la aplicación de migraciones de Flyway

Flyway está configurado para ejecutar automáticamente las migraciones al iniciar la aplicación. Puede verificar que las migraciones se han aplicado correctamente de las siguientes maneras:

1. Revise los logs de la aplicación durante el inicio. Debería ver mensajes relacionados con la ejecución de Flyway, como:

```
Flyway is applying migrations...
Successfully applied X migrations
```

2. Conéctese a la base de datos y verifique la tabla `flyway_schema_history`:

```bash
docker exec -it candidates_mysql mysql -uroot -proot candidates_db -e "SELECT * FROM flyway_schema_history;"
```

Esto mostrará un registro de todas las migraciones aplicadas.

### 5. Acceder al Swagger de la aplicación

Una vez que la aplicación esté en funcionamiento, puede acceder a la documentación de Swagger en:

```
http://localhost:8080/swagger-ui.html
```

Aquí encontrará una interfaz interactiva que le permitirá explorar y probar todos los endpoints de la API.

## Notas adicionales
- Los datos de prueba se insertan automáticamente a través de los scripts de migración de Flyway ubicados en `src/main/resources/db/migration`.
- Para detener y eliminar el contenedor de MySQL, ejecute:

```bash
docker-compose down
```

### Información de inicio de sesión

Para acceder a la aplicación, utilice las siguientes credenciales:
- **Email:** test@example.com
- **Contraseña:** password

## Descarga de la Colección de Postman

Para facilitar las pruebas de la API, hemos incluido una colección de Postman que puede descargar y utilizar. La colección se encuentra en el siguiente archivo:

```
src/main/resources/API de Candidatos.postman_collection.json
```

