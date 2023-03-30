package br.com.southsystem.exception.handler.response;

import br.com.southsystem.exception.serialization.HttpStatusCodeDeserializer;
import br.com.southsystem.exception.serialization.HttpStatusCodeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorSpec {

    private String namespace;

    private String name;

    private String message;

    @JsonSerialize(contentUsing = HttpStatusCodeSerializer.class)
    @JsonDeserialize(contentUsing = HttpStatusCodeDeserializer.class)
    private List<HttpStatus> httpStatusCodes;

    private List<Issue> issues;

    private List<String> suggestedApplicationActions;

    private List<String> suggestedUserActions;
}