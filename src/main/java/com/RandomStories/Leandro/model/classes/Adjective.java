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

    ///Transform the current Adjective to its plural form.
    ///THIS CANNOT BE UNDONE.
    public void toPlural(){
        System.out.println("Se llamó a toPlural(): Adjetivo: "+name+" Regla: "+pluralRule);
        switch (pluralRule){
            case S -> { name = name+"s"; }
            case ES -> { name = name +"es"; }
            case IRREGULAR -> { name = this.irregularPluralForm; }
        }
    }
}
