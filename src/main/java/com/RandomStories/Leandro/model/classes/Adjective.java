package com.RandomStories.Leandro.model.classes;

import com.RandomStories.Leandro.model.enumerators.Gender;
import com.RandomStories.Leandro.model.enumerators.PluralRule;

public class Adjective {
    private String adjective;
    private String irregularPluralForm;
    private Gender gender;
    private PluralRule pRule;


    public Adjective(String adj, Gender adjGender, PluralRule rule){
        this.adjective = adj;
        this.gender = adjGender;
        this.pRule = rule;
    }

    public Adjective(String adj, Gender adjGender, PluralRule rule, String irregularPluralForm){
        this.adjective = adj;
        this.gender = adjGender;
        this.pRule = rule;
        this.irregularPluralForm = irregularPluralForm;
    }

    public Gender getGender(){
        return gender;
    }

    public String toString(){
        return adjective;
    }

    public String toPlural(){
        switch (pRule){
            case S -> { return adjective+"s"; }
            case ES -> { return adjective+"es"; }
            case IRREGULAR -> { return this.irregularPluralForm; }
            default -> {return this.adjective;}
        }
    }
}
