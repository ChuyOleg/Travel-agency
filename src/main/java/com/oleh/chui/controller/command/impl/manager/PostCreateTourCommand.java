package com.oleh.chui.controller.command.impl.manager;

import com.oleh.chui.controller.command.Command;
import com.oleh.chui.controller.exception.tour.*;
import com.oleh.chui.controller.validator.TourValidator;
import com.oleh.chui.model.dto.TourDto;

import javax.servlet.http.HttpServletRequest;

public class PostCreateTourCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // TODO: get tourDTO
        TourDto tourDto = fetchTourDtoFromRequest(request);;

        // TODO: validate tourDTO
        boolean tourDtoIsValid = validateTourDto(tourDto, request);

        // TODO: check if tour with the same name already exists

        // TODO: create tour and redirect

        return null;
    }

    private TourDto fetchTourDtoFromRequest(HttpServletRequest req) {
        return new TourDto(
                req.getParameter("name"),
                req.getParameter("price"),
                req.getParameter("country"),
                req.getParameter("city"),
                req.getParameter("description"),
                req.getParameter("maxDiscount"),
                req.getParameter("discountStep"),
                req.getParameter("tourType"),
                req.getParameter("hotelType"),
                req.getParameter("personNumber"),
                req.getParameter("startDate"),
                req.getParameter("endDate"),
                req.getParameter("burning")
        );
    }

    private boolean validateTourDto(TourDto tourDto, HttpServletRequest req) {
        try {
            TourValidator.validate(tourDto);
            return true;
        } catch (NameIsEmptyException e) {
            e.printStackTrace();
        } catch (PriceIsNotValidException e) {
            e.printStackTrace();
        } catch (CountryIsEmptyException e) {
            e.printStackTrace();
        } catch (CityIsEmptyException e) {
            e.printStackTrace();
        } catch (DescriptionIsEmptyException e) {
            e.printStackTrace();
        } catch (MaxDiscountIsNotValidException e) {
            e.printStackTrace();
        } catch (DiscountStepIsNotValidException e) {
            e.printStackTrace();
        } catch (TourTypeIsEmptyException e) {
            e.printStackTrace();
        } catch (HotelTypeIsEmptyException e) {
            e.printStackTrace();
        } catch (PersonNumberIsNotValidException e) {
            e.printStackTrace();
        } catch (StartDateIsNotValidException e) {
            e.printStackTrace();
        } catch (EndDateIsNotValidException e) {
            e.printStackTrace();
        }

        return false;
    }

}
