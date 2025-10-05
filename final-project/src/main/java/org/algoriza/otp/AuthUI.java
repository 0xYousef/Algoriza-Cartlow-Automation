package org.algoriza.otp;

public class AuthUI {
    public static void main(String[] args) {
        PhoneDialog phoneDialog = new PhoneDialog("Phone Verification", "Enter your phone number");
        String phone = phoneDialog.showDialog();
        System.out.println("Phone: " + phone);

        OtpDialog otpDialog = new OtpDialog("OTP Verification", "Enter the OTP");
        String otp = otpDialog.showDialog();
        System.out.println("OTP: " + otp);
    }
}
