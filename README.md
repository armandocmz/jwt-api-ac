Test Project 

## `Spring Boot`
https://start.spring.io/

## `Spring Initializr `
https://start.spring.io/

# DB en memoria H2 con Spring Boot

## 1) Configurar application.properties

#arrancar con datos SQL precargados -> agregar **data.sql** en */resources*

```
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

#configurar consola h2
En la parta del properties (application.properties)
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
```


## 2) Crear archivo vacio:
**test.mv.db**

• Windows: ```C:/Users/User```

• Linux: ```/home/user/```


## 3) Entrar a la consola
server.servlet.context-path=/food
http://localhost:8080/contexto/h2-console/
contexto se reemplaza por el que se pone en path
<img width="527" height="461" alt="image" src="https://github.com/user-attachments/assets/1b3128eb-24a4-4c00-9088-bb6db755517c" />
 
## **IMPORTANTE: El JDBC URL debe ser el mismo que el del properties, o sino, NO carga las tablas!**

------
