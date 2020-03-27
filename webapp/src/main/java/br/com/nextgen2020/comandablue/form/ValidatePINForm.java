package br.com.nextgen2020.comandablue.form;

public class ValidatePINForm {
    private String PIN;

    public String getPIN() {
        return PIN;
    }

    @Override
    public String toString() {
        return String.format("PIN=%s", this.PIN);
    }
}
