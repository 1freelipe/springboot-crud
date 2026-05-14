package br.com.fatecararas.dicasdevspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaDTO {
    private Integer id;
    @NotBlank
    @NotNull
    @Length(max = 50)
    private String descricao;
}
