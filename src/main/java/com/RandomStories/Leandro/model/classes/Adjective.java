package com.RandomStories.Leandro.model.classes;

import com.RandomStories.Leandro.model.enumerators.Gender;
import com.RandomStories.Leandro.model.enumerators.PluralRule;

public class Adjective {
    private String name;
    private String irregularPluralForm;
    private final Gender gender;
    private final PluralRule pluralRule;

    public Adjective(String adj, Gender adjGender, PluralRule rule){
        this.name = adj;
        this.gender = adjGender;
        this.pluralRule = rule;
    }

    public Adjective(String adj, Gender adjGender, PluralRule rule, String irregularPluralForm){
        this.name = adj;
        this.gender = adjGender;
        this.pluralRule = rule;
        this.irregularPluralForm = irregularPluralForm;
    }

    public Gender getGender(){
        return gender;
    }

    public String toString(){
        return name;
    }

    public String getPlural(){
        switch (pluralRule){
            case S -> { return name+"s"; }
            case ES -> {  return name +"es"; }
            case IRREGULAR -> {  return this.irregularPluralForm; }
            default -> {return name;}
        }
    }

}
