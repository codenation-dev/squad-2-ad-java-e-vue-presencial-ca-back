package br.com.codenation.centralerrosapi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Error implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String detail;
    private Integer level;
    private Integer events;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean arquived;

    public Error() {
    }

    public Error(Long id, String title, String detail, Integer level, Integer events, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean arquived) {
        super();
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.level = level;
        this.events = events;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.arquived = arquived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getEvents() {
        return events;
    }

    public void setEvents(Integer events) {
        this.events = events;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getArquived() {
        return arquived;
    }

    public void setArquived(Boolean arquived) {
        this.arquived = arquived;
    }

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
                Objects.equals(arquived, error.arquived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, detail, level, events, createdAt, updatedAt, arquived);
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
                ", arquived=" + arquived +
                '}';
    }
}
