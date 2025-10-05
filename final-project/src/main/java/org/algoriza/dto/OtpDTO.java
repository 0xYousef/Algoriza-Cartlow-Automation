package org.algoriza.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OtpDTO {
    private final String phone;
    private final String email;
    private final String otp;
}
