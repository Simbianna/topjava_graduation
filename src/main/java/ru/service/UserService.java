package ru.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.security.AuthorizedUser;
import ru.repository.UserRepository;
import ru.dto.UserTo;
import ru.util.UserUtil;
import ru.model.User;
import ru.util.ValidationUtil;

import javax.transaction.Transactional;
import java.util.List;

import static ru.util.UserUtil.prepareToSave;

@Service("userService")
@AllArgsConstructor
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationUtil validator;

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        validator.checkNew(user);
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        validator.checkNotFoundWithId(repository.delete(id), id);
    }

    @Cacheable("users")
    public User get(int id) {
        return validator.checkNotFoundWithId(repository.findById(id).orElseGet(null), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return validator.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user, int userId) {
        validator.assureIdConsistent(user,userId);
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo, int userId) {
        validator.assureIdConsistent(userTo, userId);
        User user = get(userTo.id());
        repository.save(prepareToSave(UserUtil.updateFromTo(user, userTo), passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

}
