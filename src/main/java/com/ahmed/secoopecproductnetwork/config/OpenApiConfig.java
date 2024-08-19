package com.ahmed.secoopecproductnetwork.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info=@Info(description = "openapi documentation",title = "specification"),
servers = {

        @Server(description = "local ENV",
                url = "http://localhost:8088/api/v1"

        ),
        @Server(description = "prod ENV",
                url = "http://xnxx.xom"

        )

},
        security = {
        @SecurityRequirement(name="bearerAuth")
        }





)
//header is using Bearer Token
@SecurityScheme(name="bearerAuth",description = "JWT auth description" ,scheme = "bearer",type = SecuritySchemeType.HTTP,in= SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}
