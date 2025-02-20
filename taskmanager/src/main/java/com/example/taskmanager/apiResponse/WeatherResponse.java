package com.example.taskmanager.apiResponse;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

//Diserialization json to pojo
//Serialization pojo to json
@Getter
@Setter
public class WeatherResponse {

        public Current current;


@Getter
@Setter
        public class Current {
            @JsonProperty("temp_c")
            private double tempC;
            @JsonProperty("is_day")
            private int isDay;



            private int humidity;
            private int cloud;
            @JsonProperty("feelslike_c")
            private double feelsLikeC;

        }

    }



