package com.devsuperior.dscatalog.services.exceptions;

import java.awt.event.FocusEvent;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException (String message) {
        super(message);
    }
}
