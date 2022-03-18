package com.oleh.chui.controller.command.impl.common;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.util.JspFilePath;
import com.oleh.chui.model.entity.Role;
import com.oleh.chui.model.entity.Tour;
import com.oleh.chui.model.service.OrderService;
import com.oleh.chui.model.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Returns Tour_Detail page.
 *
 * @author Oleh Chui
 */
public class GetTourDetailsCommand implements Command {

    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String TOUR = "tour";
    private static final String USER_ID = "userId";
    private static final String TOUR_IS_BOUGHT = "tourIsBought";
    private static final String FINAL_PRICE = "finalPrice";

    private final OrderService orderService;
    private final TourService tourService;

    public GetTourDetailsCommand(OrderService orderService, TourService tourService) {
        this.orderService = orderService;
        this.tourService = tourService;
    }

    /**
     * Process getting Tour by id
     * and calculating final price is needed.
     *
     * @param request An instance of HttpServletRequest class.
     * @return String representing Path to JSP file.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long tourId = Long.valueOf(request.getParameter(ID));
        Role.RoleEnum role = Role.RoleEnum.valueOf(((String) session.getAttribute(ROLE)));

        Optional<Tour> tourOptional = tourService.findById(tourId);

        tourOptional.ifPresent(tour -> {
            request.setAttribute(TOUR, tour);
            if (role.equals(Role.RoleEnum.USER)) {
                Long activeUserId = (Long) session.getAttribute(USER_ID);

                if (orderService.isExistedByUserIdAndTourId(activeUserId, tourId)) {
                    request.setAttribute(TOUR_IS_BOUGHT, true);
                } else {
                    BigDecimal finalPrice = orderService.calculateFinalPrice(activeUserId, tour);
                    request.setAttribute(FINAL_PRICE, finalPrice);
                }
            }
        });

        return JspFilePath.TOUR_DETAILS;
    }

}
