package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.HotelType;
import com.oleh.chui.model.entity.TourType;

import javax.servlet.http.HttpServletRequest;

public class GetCreateTourCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        TourType.TourTypeEnum[] tourTypeEnums = TourType.TourTypeEnum.values();
        HotelType.HotelTypeEnum[] hotelTypeEnums = HotelType.HotelTypeEnum.values();

        request.setAttribute("tourTypeList", tourTypeEnums);
        request.setAttribute("hotelTypeList", hotelTypeEnums);

        return JspFilePath.MANAGER_CREATE_TOUR;
    }
}
