package br.com.codenation.centralerrosapi.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Error implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String detail;
    private Integer level;
    private Integer events;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean archived;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return level == error.level &&
                events == error.events &&
                Objects.equals(id, error.id) &&
                Objects.equals(title, error.title) &&
                Objects.equals(detail, error.detail) &&
                Objects.equals(createdAt, error.createdAt) &&
                Objects.equals(updatedAt, error.updatedAt) &&
                Objects.equals(archived, error.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, detail, level, events, createdAt, updatedAt, archived);
    }

    @Override
    public String toString() {
        return "Error{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", level=" + level +
                ", events=" + events +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", archived=" + archived +
                '}';
    }
}
