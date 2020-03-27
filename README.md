## Open API endpoints:

- [swagger](http://localhost:8080/swagger/newtrion-0.0.yml)
- [swagger-ui](http://localhost:8080/swagger/views/swagger-ui/index.html)
- [rapidoc](http://localhost:8080/swagger/views/rapidoc/index.html)
- [redoc](http://localhost:8080/swagger/views/redoc/index.html)

Currently, to regenerate the api docs you must run:

```
./gradlew --no-daemon -Dkotlin.daemon.jvm.options="JAVA_TOOL_OPTIONS-D=mic=-Dmicroaut.openapi.views.spec=redoc.enabled=true,rapidoc.enabled=true,swagger-ui.enabled=true,swagger-ui.theme=flattop" clean compileKotlin
```


