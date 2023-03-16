## Test project for SENLA

### Run: 
  - java -jar weather_analyzer-1.0.0.jar or docker compose up

## Endpoints:
### localhost:8080/api/v1/weather
  - GET
    - status: 200
    - response
      <pre>{
           "temperature_c": 1.0,
           "temperature_f": 33.8,
           "wind_speed_mph": 11.9,
           "pressure_mb": 1016.0,
           "air_humidity": 80,
           "condition": "Sunny",
           "location": "Minsk"
      }</pre>
  - POST 
    - status: 200
    - request 
      <pre> {    
        "from_date":"10-03-2023",
        "to_date":"12-03-2023"
      }</pre>
    - response:
      <pre> {
          "average_temperature": 0.23
      }</pre>
