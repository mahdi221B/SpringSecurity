package tn.esprit.spring.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adresse {
    private String adresse;
    private String ville;
    private String codepostal;
    private String pays;
}
