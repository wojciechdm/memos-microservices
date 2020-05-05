package com.wojciechdm.memos.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
class MemosGatewayApplication

fun main(args: Array<String>) {
	runApplication<MemosGatewayApplication>(*args)
}
