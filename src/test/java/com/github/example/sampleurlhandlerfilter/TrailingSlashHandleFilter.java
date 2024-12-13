package com.github.example.sampleurlhandlerfilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.filter.UrlHandlerFilter;

import java.io.IOException;

@TestConfiguration
public class TrailingSlashHandleFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var filter = UrlHandlerFilter
                .trailingSlashHandler("/**")
                .wrapRequest()
                .build();
        filter.doFilter(request, response, filterChain);
    }
}
