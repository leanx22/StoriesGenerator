package com.RandomStories.Leandro.model.classes;

public class Verb {
    private final String infinitivo;
    private final String simple;
    private final String simplePlural;
    private final String imperfecto;

    public Verb(String imperfecto, String simplePlural, String simple, String infinitivo) {
        this.imperfecto = imperfecto;
        this.simplePlural = simplePlural;
        this.simple = simple;
        this.infinitivo = infinitivo;
    }

    ///AR
    public String getInfinitivo(){
        return this.infinitivo;
    }

    ///ó
    public String getSimple(){
        return this.simple;
    }

    ///aron
    public String getSimplePlural(){
        return this.simplePlural;
    }

    ///aba
    public String getImperfecto(){
        return this.imperfecto;
    }

    ///abaN
    public String getImperfectoPlural(){
        return this.imperfecto+"n";
    }

}
