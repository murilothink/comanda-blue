package br.com.nextgen2020.comandablue.form;

public class ValidatePinForm {
    private String pin;

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return String.format("PIN=%s", this.pin);
    }
}
