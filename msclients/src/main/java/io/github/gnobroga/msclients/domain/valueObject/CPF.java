package io.github.gnobroga.msclients.domain.valueObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

@Getter
public class CPF {
    
    private String value;

    public CPF(String value) {
       this.value = value;
       this.validate();
    }

    public void validate() {
        Matcher matcher = Pattern.compile("\\d{11}").matcher(value);
        
        if (!matcher.matches()) {
            throw new RuntimeException("The CPF is not a valid.");
        }
    }

    public void setValue(String value) {
        this.value = value;
        this.validate();
    }
}
