package com.basic.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDaoService {
  private static final List<User> users = new ArrayList<>();
  private static int userCount = 4;
  int counter = 0;

  static {
    users.add(new User(1, "ad"));
    users.add(new User(2, "gs"));
    users.add(new User(3, "rg"));
    users.add(new User(4, "qw"));
  }

  @Retryable(value = {Exception.class}, maxAttempts = 3)
  public List<User> findAll() {
    return users;
  }

  public User save(User user) {
    if (0 == user.getId()) {
      user.setId(++userCount);
    }
    users.add(user);
    return user;
  }

  public Optional<User> findOne(int id) throws UserNotFoundException {
    Optional<User> user = users.stream().filter(i -> i.getId() == id).findFirst();
    return user;
  }

  public User deleteById(int id) {
    Iterator<User> itr = users.iterator();
    while (itr.hasNext()) {
      User us = itr.next();
      if (us.getId() == id) {
        itr.remove();
        return us;
      }
    }
    return null;
  }
}
