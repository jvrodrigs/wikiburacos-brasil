package com.wiki.backend.Exceptions.Model;

import java.util.Arrays;
import java.util.List;

public class ApiErros {
    private List<String> errors;

    public ApiErros(String msgError) {
        this.errors = Arrays.asList(msgError);
    }
    public ApiErros(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
