package ru.javawebinar.topjava.model.baseEntities;

;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

@Entity
public class User extends AbstractBaseEntity {

    private String email;
    private String password;
    private boolean enabled;
    private Date registered = new Date();
    private Set<Role> roles;
    private LocalDateTime votingTime;
    private Vote vote;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(),
                u.getRegistered(), u.getRoles(),
                u.votingTime, u.vote);
    }


    public User(int id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, new Date(), EnumSet.of(role, roles), null, null);
    }

    public User(int id, String name, String email, String password, boolean enabled, Date registered, Set<Role> roles, LocalDateTime votingTime, Vote vote) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
        this.votingTime = votingTime;
        this.vote = vote;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getTodaysVotingTime() {
        return votingTime;
    }

    public void setTodaysVotingTime(LocalDateTime votingTime) {
        this.votingTime = votingTime;
    }

    public Vote getTodaysVote() {
        return vote;
    }

    public void setTodaysVote(Vote vote) {
        this.vote = vote;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
        ;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
