package org.fredohm.springbootintranet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.fredohm.springbootintranet.exceptions.AlreadyBookedException;
import org.fredohm.springbootintranet.exceptions.ExistingMeetingsException;
import org.fredohm.springbootintranet.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Model model, Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        model.addAttribute("exceptionType","404 - Not Found");
        model.addAttribute("exception", exception);

        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String handleBadRequest(Model model, Exception exception) {
        log.error("Handling bad request exception");
        log.error(exception.getMessage());

        model.addAttribute("exceptionType","400 - Bad Request");
        model.addAttribute("exception", exception);

        return "error";
    }

    @ExceptionHandler(AlreadyBookedException.class)
    public String handleAlreadyBooked(Model model, Exception exception) {
        log.error(exception.getMessage());

        model.addAttribute("exceptionType", "Salle déjà réservée");
        model.addAttribute("exception", exception);

        return "error";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExistingMeetingsException.class)
    public String forbiddenDeletingMeetingRoom(Model model, Exception exception) {
        log.error("Interdit de supprimer une salle si des réunions sont présentes!");
        log.error(exception.getMessage());

        model.addAttribute("exceptionType","401 - Unauthorized");
        model.addAttribute("exception", exception);

        return "error";
    }
}
