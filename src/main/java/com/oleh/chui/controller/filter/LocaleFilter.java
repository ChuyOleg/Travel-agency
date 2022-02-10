package com.oleh.chui.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        final String LANG = "lang";

        if (req.getParameter(LANG) != null) {
            req.getSession().setAttribute(LANG, req.getParameter(LANG));
        } else if (req.getSession().getAttribute(LANG) == null) {
            req.getSession().setAttribute(LANG, Locale.ENGLISH);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
