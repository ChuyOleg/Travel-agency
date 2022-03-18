package com.oleh.chui.controller.command.impl.guest;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;

import javax.servlet.http.HttpServletRequest;

/**
 * Returns Registration page.
 *
 * @author Oleh Chui
 */
public class GetRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JspFilePath.REGISTRATION;
    }
}
