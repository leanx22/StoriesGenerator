package com.RandomStories.Leandro.utils;

import com.RandomStories.Leandro.model.classes.*;
import com.RandomStories.Leandro.model.classes.Character;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public final class FileUtils {

    private FileUtils(){};

    public static Character[] getCharacters() throws IOException{
        try(Reader reader = new FileReader(Constants.CHARACTERS_FILE_PATH)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Character>>(){}.getType();

            List<Character> characters = gson.fromJson(reader, listType);
            return characters.toArray(new Character[0]);
        }catch (IOException e){
            throw new IOException("No se pudo cargar los personajes!");
        }
    }

    public static Place[] getPlaces() throws IOException{
        try(Reader reader = new FileReader(Constants.PLACES_FILE_PATH)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Place>>(){}.getType();

            List<Place> places = gson.fromJson(reader, listType);
            return places.toArray(new Place[0]);
        }catch (IOException e){
            throw new IOException("No se pudo cargar los lugares!");
        }
    }

    public static StoryObject[] getObjects() throws IOException{
        try(Reader reader = new FileReader(Constants.OBJECTS_FILE_PATH)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<StoryObject>>(){}.getType();

            List<StoryObject> objects = gson.fromJson(reader, listType);
            return objects.toArray(new StoryObject[0]);
        }catch (IOException e){
            throw new IOException("No se pudo cargar los objetos!");
        }
    }

    public static Adjective[] getAdjectives() throws IOException{
        try(Reader reader = new FileReader(Constants.ADJECTIVES_FILE_PATH)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Adjective>>(){}.getType();

            List<Adjective> adjectives = gson.fromJson(reader, listType);
            return adjectives.toArray(new Adjective[0]);
        }catch (IOException e){
            throw new IOException("No se pudo cargar los objetos!");
        }
    }

    public static Verb[] getVerbs() throws IOException{
        try(Reader reader = new FileReader(Constants.VERBS_FILE_PATH)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Verb>>(){}.getType();

            List<Verb> verbs = gson.fromJson(reader, listType);
            return verbs.toArray(new Verb[0]);
        }catch (IOException e){
            throw new IOException("No se pudo cargar los verbos!");
        }
    }

    public static String[] getStringListFromJSON(String path) throws IOException{
        try(Reader reader = new FileReader(path)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<String>>(){}.getType();

            List<String> elements = gson.fromJson(reader, listType);
            return elements.toArray(new String[0]);
        }catch (IOException e){
            throw new IOException("No se pudo transformar el contenido del JSON a un listado de Strings!");
        }
    }
}
