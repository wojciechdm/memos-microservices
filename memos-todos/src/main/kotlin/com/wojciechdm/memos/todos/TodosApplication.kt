package com.wojciechdm.memos.todos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class TodosApplication

fun main(args: Array<String>) {
	runApplication<TodosApplication>(*args)
}
