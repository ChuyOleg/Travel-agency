package com.oleh.chui.controller;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.*;
import com.oleh.chui.controller.command.impl.manager.GetCreateTourCommand;
import com.oleh.chui.controller.command.impl.manager.PostCreateTourCommand;
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

    private final Map<String, Command> getCommands = new ConcurrentHashMap<>();
    private final Map<String, Command> postCommands = new ConcurrentHashMap<>();
    private final String COMMAND_NOT_FOUND = "Command not found";
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
        getCommands.put(UriPath.CATALOG, new GetCatalogCommand(serviceFactory.createTourService()));
        getCommands.put(UriPath.MANAGER_CREATE_TOUR, new GetCreateTourCommand());
    }

    private void putPostCommands(ServiceFactory serviceFactory) {
        postCommands.put(UriPath.LOGIN, new PostLogInCommand(serviceFactory.createUserService()));
        postCommands.put(UriPath.REGISTRATION, new PostRegistrationCommand(serviceFactory.createUserService()));
        postCommands.put(UriPath.MANAGER_CREATE_TOUR, new PostCreateTourCommand(serviceFactory.createTourService(), serviceFactory.createCountryService()));
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
