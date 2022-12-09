package com.sprc_web_app.model.dto.response;

import java.io.Serializable;

public record ObjectResponse<T>(T id) implements Serializable {
}
