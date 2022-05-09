package com.weather_alligator.forecast_service.persistence.entity;

import com.weather_alligator.forecast_service.enums.Duration;
import com.weather_alligator.forecast_service.enums.Source;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "forecast")
public class ForecastEntity {

    @Id
    private ObjectId id;

    @Indexed
    private Long userId;
    private Source source;
    private Duration duration;
    private Instant created;
    private String forecast; // TODO may be should be converted to JSON or BSON or smth like this

    public ForecastEntity(final Long userId, final Source source, final Duration duration,
                          final Instant created, final String forecast) {
        this.userId = userId;
        this.source = source;
        this.duration = duration;
        this.created = created;
        this.forecast = forecast;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(final Source source) {
        this.source = source;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(final Duration duration) {
        this.duration = duration;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(final Instant created) {
        this.created = created;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(final String forecast) {
        this.forecast = forecast;
    }
}
