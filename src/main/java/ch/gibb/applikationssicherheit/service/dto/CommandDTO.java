package ch.gibb.applikationssicherheit.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommandDTO implements Serializable {

    private String result;
}

