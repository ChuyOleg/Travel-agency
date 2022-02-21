package com.oleh.chui.controller;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.admin.*;
import com.oleh.chui.controller.command.impl.common.GetCatalogCommand;
import com.oleh.chui.controller.command.impl.common.GetLogOutCommand;
import com.oleh.chui.controller.command.impl.common.GetTourDetailsCommand;
import com.oleh.chui.controller.command.impl.guest.GetLogInCommand;
import com.oleh.chui.controller.command.impl.guest.GetRegistrationCommand;
import com.oleh.chui.controller.command.impl.guest.PostLogInCommand;
import com.oleh.chui.controller.command.impl.guest.PostRegistrationCommand;
import com.oleh.chui.controller.command.impl.manager.*;
import com.oleh.chui.controller.command.impl.user.GetAccountPageCommand;
import com.oleh.chui.controller.command.impl.user.PostBuyTourCommand;
import com.oleh.chui.controller.util.UriPath;
import com.oleh.chui.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DispatcherServlet extends HttpServlet {

    // TODO: add tests

    // TODO: documentation

    private final Map<String, Command> getCommands = new ConcurrentHashMap<>();
    private final Map<String, Command> postCommands = new ConcurrentHashMap<>();
    private static final String COMMAND_NOT_FOUND = "Command not found";
    Logger logger = LogManager.getLogger(DispatcherServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        putGetCommands(serviceFactory);
        putPostCommands(serviceFactory);
        logger.info("Dispatcher servlet has been initialized");
    }

    private void putGetCommands(ServiceFactory serviceFactory) {
        getCommands.put(UriPath.LOGIN, new GetLogInCommand());
        getCommands.put(UriPath.REGISTRATION, new GetRegistrationCommand());
        getCommands.put(UriPath.LOGOUT, new GetLogOutCommand());
        getCommands.put(UriPath.CATALOG, new GetCatalogCommand(serviceFactory.createTourService()));
        getCommands.put(UriPath.ADMIN_CREATE_TOUR, new GetCreateTourCommand());
        getCommands.put(UriPath.MANAGER_USERS, new GetUsersCommand(serviceFactory.createUserService()));
        getCommands.put(UriPath.TOUR_DETAILS, new GetTourDetailsCommand(serviceFactory.createOrderService(), serviceFactory.createTourService()));
        getCommands.put(UriPath.ADMIN_UPDATE_TOUR, new GetUpdateTourCommand(serviceFactory.createTourService()));
        getCommands.put(UriPath.USER_ACCOUNT, new GetAccountPageCommand(serviceFactory.createUserService(), serviceFactory.createOrderService()));
        getCommands.put(UriPath.MANAGER_USER_PAGE, new GetUserPageCommand(serviceFactory.createUserService(), serviceFactory.createOrderService()));
        getCommands.put(UriPath.MANAGER_CHANGE_DISCOUNT, new GetChangeDiscountCommand(serviceFactory.createTourService()));
    }

    private void putPostCommands(ServiceFactory serviceFactory) {
        postCommands.put(UriPath.LOGIN, new PostLogInCommand(serviceFactory.createUserService()));
        postCommands.put(UriPath.REGISTRATION, new PostRegistrationCommand(serviceFactory.createUserService()));
        postCommands.put(UriPath.ADMIN_CREATE_TOUR, new PostCreateTourCommand(serviceFactory.createTourService()));
        postCommands.put(UriPath.MANAGER_USERS, new PostBlockUnblockUserCommand(serviceFactory.createUserService()));
        postCommands.put(UriPath.USER_BUY_TOUR, new PostBuyTourCommand(serviceFactory.createOrderService()));
        postCommands.put(UriPath.MANAGER_CHANGE_TOUR_BURNING_STATE, new PostChangeBurningStateCommand(serviceFactory.createTourService()));
        postCommands.put(UriPath.ADMIN_DELETE_TOUR, new PostDeleteTourCommand(serviceFactory.createTourService(), serviceFactory.createOrderService()));
        postCommands.put(UriPath.ADMIN_UPDATE_TOUR, new PostUpdateTourCommand(serviceFactory.createTourService()));
        postCommands.put(UriPath.MANAGER_CHANGE_ORDER_STATUS, new PostChangeOrderStatusCommand(serviceFactory.createOrderService()));
        postCommands.put(UriPath.MANAGER_CHANGE_DISCOUNT, new PostChangeDiscountCommand(serviceFactory.createTourService()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, getCommands);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, postCommands);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp, Map<String, Command> commands) throws IOException, ServletException {
        final String URI = req.getRequestURI();

        String commandKey = commands.keySet().stream()
                .filter(key -> key.equals(URI))
                .findFirst()
                .orElse(COMMAND_NOT_FOUND);

        if (commandKey.equals(COMMAND_NOT_FOUND)) {
            logger.warn("page: {} not found", URI);
            resp.sendError(404);
            return;
        }

        Command command = commands.get(commandKey);
        String result = command.execute(req);

        renderPage(req, resp, result);
    }

    private void renderPage(HttpServletRequest req, HttpServletResponse resp, String pagePath) throws ServletException, IOException {
        if (pagePath.startsWith(UriPath.REDIRECT)) {
            resp.sendRedirect(pagePath.replace(UriPath.REDIRECT, ""));
        } else {
            req.getRequestDispatcher(pagePath).forward(req, resp);
        }
    }

}
