package ru.itmo.se.bl.lab3.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@Data
public class EmailNotification implements Serializable {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Nullable
    private String middleName;

    @NotNull
    private String email;

    @NotNull
    private Date bookingDate;
}
