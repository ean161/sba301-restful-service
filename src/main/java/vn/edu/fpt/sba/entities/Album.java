package vn.edu.fpt.sba.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumId")
    private Integer id;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ArtistId")
    private Artist artist;
}
