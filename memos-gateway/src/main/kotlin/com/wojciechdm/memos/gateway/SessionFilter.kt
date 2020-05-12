package com.wojciechdm.memos.gateway

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.session.SessionRepository
import org.springframework.stereotype.Component

@Component
internal class SessionFilter : ZuulFilter() {

    @Autowired
    private lateinit var repository: SessionRepository<*>

    override fun run(): Any? {
        val context = RequestContext.getCurrentContext()
        val httpSession = context.request.session
        val session = repository.findById(httpSession.id)
        context.addZuulRequestHeader("Cookie", "SESSION=" + httpSession.id)
        return null
    }

    override fun shouldFilter(): Boolean = true

    override fun filterType(): String = "pre"

    override fun filterOrder(): Int = 0

}