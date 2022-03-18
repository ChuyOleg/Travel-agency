package com.oleh.chui.controller.filter;

import com.oleh.chui.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.oleh.chui.controller.util.UriPath.*;

/**
 * Does authorization checking.
 *
 * @author Oleh Chui
 */
public class AuthFilter implements Filter {

    private static final String ROLE_ATTRIBUTE = "role";
    private static final String USER_ID_ATTRIBUTE = "userId";

    private final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Does nothing if user has access.
     * Redirects to Login page if user is guest and access is denied.
     * Send 403 Error if user is [USER, MANAGER, ADMIN] and access is denied.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        final String URI = request.getRequestURI();

        if (session.getAttribute(ROLE_ATTRIBUTE) == null) {
            session.setAttribute(ROLE_ATTRIBUTE, Role.RoleEnum.UNKNOWN.toString());
        }

        Role.RoleEnum role = Role.RoleEnum.valueOf(session.getAttribute(ROLE_ATTRIBUTE).toString());

        if (!checkResources(URI) && !checkAccess(URI, role)) {
            if (role.equals(Role.RoleEnum.UNKNOWN)) {
                logger.info("Redirect guest to Login page from URI ({})", URI);
                response.sendRedirect(LOGIN);
                return;
            }
            logger.warn("User (id = {}) Forbidden uri: {}", session.getAttribute(USER_ID_ATTRIBUTE), URI);
            response.sendError(403);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkAccess(String uri, Role.RoleEnum roleEnum) {
        switch (roleEnum) {
            case USER:
                return checkUserAccess(uri);
            case MANAGER:
                return checkManagerAccess(uri);
            case ADMIN:
                return checkAdminAccess(uri);
            default:
                return checkGuestAccess(uri);
        }
    }

    private boolean checkUserAccess(String uri) {
        return checkCommonAccess(uri) || uri.startsWith(USER_PREFIX) || uri.equals(LOGOUT);
    }

    private boolean checkManagerAccess(String uri) {
        return checkCommonAccess(uri) || uri.startsWith(MANAGER_PREFIX) || uri.equals(LOGOUT);
    }

    private boolean checkAdminAccess(String uri) {
        return checkManagerAccess(uri) || uri.startsWith(ADMIN_PREFIX);
    }

    private boolean checkGuestAccess(String uri) {
        return checkCommonAccess(uri) || uri.equals(LOGIN) || uri.equals(REGISTRATION);
    }

    private boolean checkCommonAccess(String uri) {
        return uri.equals(CATALOG) || uri.equals(TOUR_DETAILS);
    }

    private boolean checkResources(String uri) {
        return uri.startsWith(STATIC_RESOURCES_PREFIX);
    }

    @Override
    public void destroy() {
    }
}
