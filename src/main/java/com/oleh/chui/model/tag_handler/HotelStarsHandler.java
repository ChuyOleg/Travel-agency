package com.oleh.chui.model.tag_handler;

import com.oleh.chui.model.entity.HotelType;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.stream.IntStream;

public class HotelStarsHandler extends TagSupport {

    private final String STAR_UNICODE = "&#11088;";
    private String hotelType;

    @Override
    public int doStartTag() {
        StringBuilder stars = new StringBuilder();
        JspWriter out = pageContext.getOut();

        HotelType.HotelTypeEnum hotelTypeEnum = HotelType.HotelTypeEnum.valueOf(hotelType);
        int starNumber = hotelTypeEnum.getStarNumber();
        IntStream.range(0, starNumber).forEach(val -> stars.append(STAR_UNICODE));

        try {
            out.print(stars);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return SKIP_BODY;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }
}
