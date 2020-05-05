package com.wojciechdm.memos.discovery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class MemosDiscoveryApplication

fun main(args: Array<String>) {
	runApplication<MemosDiscoveryApplication>(*args)
}
