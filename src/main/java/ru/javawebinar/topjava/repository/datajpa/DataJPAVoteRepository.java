package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataJPAVoteRepository {

    @Autowired
    CrudVoteRepository voteRepository;
}
