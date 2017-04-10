package kr.ac.hansung.maldives.web.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@MappedSuperclass
public abstract class BaseEntity<ID> {

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "modification_time", nullable = false)
    private LocalDateTime modificationTime;

    @Setter(value=AccessLevel.NONE)
    @Version
    private long version;

    public abstract ID getId();

    @PrePersist
    public void prePersist() {
    	LocalDateTime now = LocalDateTime.now();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = LocalDateTime.now();
    }
}