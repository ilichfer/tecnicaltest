# tecnicaltest
tecnical test capitole
This project is a practical example of developing a REST API following a Hexagonal Architecture.

### Required Technologies
`Java` `Maven`

### :wrench: Configuration
1. Persistence bean & context
   1.JPA & H2
   ```java
   package com.capitole.api.application;
   ```
2. Application.properties
   1. JPA & H2
   ```properties
   spring.h2.console.enabled=true
   spring.datasource.url=jdbc:h2:mem:pricing-db
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.h2.console.path=/h2-console
   ```

### : Execution
1. Deploy the project locally:: `> mvn clean install`

### : Testing
1. Run unit tests: `> mvn test``
