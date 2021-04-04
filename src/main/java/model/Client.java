package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Clientes")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Client {
    @Id
    private String id;

    @NotBlank(message = "nombre no puede estar en blanco")
    @JsonProperty("nombre")
    private String name;

    @NotBlank(message = "apellido no puede estar en blanco")
    @JsonProperty("apellido")
    private String lastname;

    @JsonProperty("edad")
    @Min(value = 0, message = "La edad no puede ser menor que 0")
    @Max(value = 100, message = "La edad no puede ser mayor 100")
    @NotNull
    private int age;

    @JsonProperty("fechaNacimiento")
    @NotNull(message="fecha de nacimiento no puede ser nula")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dob;

    @JsonProperty("fechaProbableMuerte")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dod;
}
