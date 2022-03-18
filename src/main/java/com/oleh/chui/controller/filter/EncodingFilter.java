package com.oleh.chui.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Sets headers for responses and request
 * related with coding and content-type
 *
 * @author Oleh Chui
 */
public class EncodingFilter implements Filter {

    private static final String HTML_CONTENT_TYPE = "text/html";
    private static final String UTF_8 = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        servletResponse.setContentType(HTML_CONTENT_TYPE);
        servletResponse.setCharacterEncoding(UTF_8);
        servletRequest.setCharacterEncoding(UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
