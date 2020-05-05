package com.wojciechdm.memos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class MemosApplication

fun main(args: Array<String>) {
	runApplication<MemosApplication>(*args)
}
