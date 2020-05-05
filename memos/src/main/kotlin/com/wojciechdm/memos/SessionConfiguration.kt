package com.wojciechdm.memos

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer

@EnableRedisHttpSession
internal class SessionConfiguration : AbstractHttpSessionApplicationInitializer() {
}