package com.oleh.chui.controller.command.impl.admin;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.command.impl.mapper.TourInfoMapper;
import com.oleh.chui.controller.util.JspFilePath;

import javax.servlet.http.HttpServletRequest;

/**
 * Returns Create_Tour page.
 *
 * @author Oleh Chui
 */
public class GetCreateTourCommand implements Command {

    private final TourInfoMapper tourInfoMapper = new TourInfoMapper();

    @Override
    public String execute(HttpServletRequest request) {
        tourInfoMapper.insertTourAndHotelTypesIntoRequest(request);

        return JspFilePath.ADMIN_CREATE_TOUR;
    }
}
