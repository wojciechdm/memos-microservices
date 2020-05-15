# Memos - microservices

RESTful API webservice, based on microservices architecture, for simple text notes and todos. Created to get know a little Spring Cloud and Kotlin.

Used technologies: Kotlin 1.3, Gradle, MySQL, Spring Boot, Spring Cloud.

### Installing

1. Before build applications need to set envoirenment variables listed below:

   ```
   MEMOS_CONFIG_REPO_URI
   MEMOS_CONFIG_SERVER_PASSWORD
   MEMOS_CONFIG_SERVER_PORT
   MEMOS_CONFIG_SERVER_URI
   MEMOS_CONFIG_SERVER_USERNAME
   MEMOS_DISCOVERY_PORT
   MEMOS_EUREKA_HOST
   MEMOS_EUREKA_PASSWORD
   MEMOS_EUREKA_SERVER_URL
   MEMOS_EUREKA_USERNAME
   MEMOS_GATEWAY_DB_PASSWORD
   MEMOS_GATEWAY_DB_URL
   MEMOS_GATEWAY_DB_USERNAME
   MEMOS_GATEWAY_PORT
   MEMOS_NOTES_DB_PASSWORD
   MEMOS_NOTES_DB_URL
   MEMOS_NOTES_DB_USERNAME
   MEMOS_NOTES_PORT
   MEMOS_REDIS_HOST
   MEMOS_REDIS_PORT
   MEMOS_TODOS_DB_PASSWORD
   MEMOS_TODOS_DB_URL
   MEMOS_TODOS_DB_USERNAME
   MEMOS_TODOS_PORT
   ```

2. Order of launching applications:
   ```
   memos-config
   memos-discovery
   memos-gateway
   memos-notes
   memos-todos
   ```