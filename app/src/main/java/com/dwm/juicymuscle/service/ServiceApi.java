package com.dwm.juicymuscle.service;

import android.util.JsonReader;

import com.dwm.juicymuscle.model.Exercice;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class ServiceApi {

    public ArrayList<Exercice> readJsonExercice(String in) throws IOException {
        JsonReader reader = new JsonReader(new StringReader(in));
        try {
            return readExercicesArray(reader);
        } finally {
            reader.close();
        }
    }

    public ArrayList<Exercice> readExercicesArray(JsonReader reader) throws IOException {
        ArrayList<Exercice> exercices = new ArrayList<Exercice>();

        reader.beginArray();
        while (reader.hasNext()) {
            exercices.add(readExercice(reader));
        }
        reader.endArray();
        return exercices;
    }

    public Exercice readExercice(JsonReader reader) throws IOException {
        String id = null;
        String nom = null;
        String muscle = null;
        String description = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "id":
                    id = reader.nextString();
                    break;
                case "nom":
                    nom = reader.nextString();
                    break;
                case "muscle":
                    muscle = reader.nextString();
                    break;
                case "description":
                    description = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new Exercice(id, nom, muscle, description);
    }
}
