package com.oleh.chui.controller.command.impl;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;

import javax.servlet.http.HttpServletRequest;

public class GetCatalogCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return JspFilePath.CATALOG;
    }
}