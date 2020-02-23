package com.godel.olizarovich.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.godel.olizarovich.models.Film;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class FilmSerializer extends StdSerializer<Film> {

    public FilmSerializer() {
        this(null);
    }

    public FilmSerializer(Class<Film> t) {
        super(t);
    }

    @Override
    public void serialize(Film film, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("first_name", film.getDirector().getFirstName());
        jsonGenerator.writeStringField("last_name", film.getDirector().getLastName());
        jsonGenerator.writeStringField("birth_date", film.getDirector().getBirthDate().toString());
        jsonGenerator.writeStringField("name", film.getName());
        jsonGenerator.writeStringField("release_date", film.getReleaseDate().toString());
        jsonGenerator.writeStringField("genre", film.getGenre());
        jsonGenerator.writeEndObject();
    }
}
