package ru.task.entities.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static java.lang.Math.toIntExact;

/**
 * Created by mirak on 11.03.17.
 */

/**
 * Базовая сущность. Содержит в себе идентификатор и задаёт последовательность для
 * генерации значения идентификатора
 */
@MappedSuperclass
public abstract class BaseEntity implements Persistable<Long> {

    @Id
    @SequenceGenerator(name = "testtask_seq", sequenceName = "testtask_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testtask_seq")
    @Access(value = AccessType.PROPERTY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    @JsonIgnore
    public boolean isNew(){
        return getId()==null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;

        return null != getId() && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : toIntExact(getId());
    }
}
