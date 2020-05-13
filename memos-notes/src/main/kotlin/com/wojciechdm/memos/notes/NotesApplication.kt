package com.wojciechdm.memos.notes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class NotesApplication

fun main(args: Array<String>) {
	runApplication<NotesApplication>(*args)
}
