package com.wojciechdm.memos.gateway

import org.springframework.session.FlushMode
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer

@EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE)
internal class SessionConfiguration : AbstractHttpSessionApplicationInitializer() {
}