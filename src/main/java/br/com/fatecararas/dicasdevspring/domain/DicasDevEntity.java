package br.com.fatecararas.dicasdevspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "dicasdev")
@Data
public class DicasDevEntity {
    @Id // Identificando que esse atributo vai ser um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera um valor aleatório para o ID cadastrado no banco
    private Integer id;
    @Column(unique = true, nullable = false, length = 100)
    private String titulo;
    @Column(nullable = false, length = 500)
    private String descricao;
}
