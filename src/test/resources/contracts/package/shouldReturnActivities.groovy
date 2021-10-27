import org.springframework.cloud.contract.spec.Contract


Contract.make {
    description "should return activities"
    request{
        method GET()
        url("/api/activities") {
        }
    }
    response {
        body("[{\"id\":4,\"name\":null,\"type\":\"CYCLING\",\"durationInMinutes\":30},{\"id\":5,\"name\":null,\"type\":\"SPIKEBALL\",\"durationInMinutes\":60},{\"id\":6,\"name\":null,\"type\":\"SPIKEBALL\",\"durationInMinutes\":90}]")
        status 200
        headers {
            header 'Content-Type': 'application/json'
        }
    }
}