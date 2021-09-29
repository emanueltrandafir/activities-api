import org.springframework.cloud.contract.spec.Contract


Contract.make {
    description "should return activities"
    request{
        method GET()
        url("api/activities") {

        }
    }
    response {
        body("[{\"id\":11,\"name\":\"aaa\",\"type\":\"CYCLING\",\"durationInMinutes\":180},{\"id\":22,\"name\":\"bbb\",\"type\":\"SPIKEBALL\",\"durationInMinutes\":180},{\"id\":33,\"name\":\"ccc\",\"type\":\"BOULDERING\",\"durationInMinutes\":120}]")
        status 200
    }
}